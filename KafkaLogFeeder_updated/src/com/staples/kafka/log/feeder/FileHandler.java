package com.staples.kafka.log.feeder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler implements Runnable
{

	private static final Logger LOG = LoggerFactory.getLogger(FileHandler.class);

	private final String filePath;

	private final long delayMs;

	private final boolean readWhole;

	private final boolean reopen;
	
	private final String kafkaTopic;
	
	private final boolean isAsync;
	
	private final KafkaProducer<String, String> producer;

	private final Map<File, Tailer> tailers = new HashMap<>();

	private FileAlterationMonitor monitor;

	private boolean running = false;

	private boolean tailersStarted = false;

	private boolean directoryNotFound = false;

	public FileHandler(
			final String filePath,
			final long delayMs,
			final boolean readWhole,
			final boolean reopen,
			final String kafkaTopic,
			final boolean isAsync,
			final KafkaProducer<String, String> producer
			)
	{
		this.filePath = filePath;
		this.delayMs = delayMs;
		this.readWhole = readWhole;
		this.reopen = reopen;
		this.kafkaTopic = kafkaTopic;
		this.isAsync = isAsync;
		this.producer = producer;
	}

	private void setUpMonitor()
	{
		FileAlterationListenerAdaptor fileAlterationListener = new FileAlterationListenerAdaptor()
		{
			@Override
			public void onFileCreate(final File file)
			{
				LOG.info("File created: {}", file.getAbsolutePath());

				startTailer(file, true);
			}

			@Override
			public void onFileChange(final File file)
			{
				startTailer(file, false);
			}

			@Override
			public void onFileDelete(final File file)
			{
				LOG.info("File deleted: {}", file.getAbsolutePath());

				stopTailer(file);
			}
		};

		String filename = filePath.substring(filePath.lastIndexOf('/') + 1);
		String directory = filePath.substring(0, filePath.lastIndexOf('/'));

		FileAlterationObserver observer = new FileAlterationObserver(
				new File(directory),
				new WildcardFileFilter(filename)
				);
		observer.addListener(fileAlterationListener);

		monitor = new FileAlterationMonitor(delayMs);
		monitor.addObserver(observer);

		try
		{
			monitor.start();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public synchronized void start()
	{
		if (running)
		{
			return;
		}

		LOG.info("File handler starting with pattern {}", filePath);

		running = true;

		setUpMonitor();

		Thread thread = new Thread(this);
		thread.setName(getClass().getName());
		thread.start();
	}

	private void startTailers() throws FileNotFoundException
	{
		String filename = filePath.substring(filePath.lastIndexOf('/') + 1);
		File directory = new File(filePath.substring(0, filePath.lastIndexOf('/')));
		if (!directory.exists())
		{
			if (!directoryNotFound)
			{
				LOG.warn("Directory {} does not exist, waiting for it to be created", directory.getAbsolutePath());
				directoryNotFound = true;
			}
			throw new FileNotFoundException("Directory " + directory.getAbsolutePath() + " does not exist");
		}

		if (directoryNotFound)
		{
			LOG.warn("Directory {} created", directory.getAbsolutePath());
			directoryNotFound = false;
		}

		Collection<File> files = FileUtils.listFiles(
				directory,
				new WildcardFileFilter(filename),
				FalseFileFilter.INSTANCE
				);

		for (File file : files)
		{
			startTailer(file, false);
		}
	}

	public void startTailer(final File file, final boolean newFile)
	{
		synchronized (tailers)
		{
			if (tailers.containsKey(file))
			{
				return;
			}

			LOG.info("Starting tailer: {}", file.getAbsolutePath());

			Tailer tailer;
			tailer = new Tailer(
					TailerFile.create(file), new TailerListener(file), delayMs, !newFile && !readWhole, reopen
					);
			tailers.put(file, tailer);

			Thread thread = new Thread(tailer);
			thread.setName("Tailer-" + file.getName());
			thread.start();
		}
	}

	private void stopTailer(final File file)
	{
		synchronized (tailers)
		{
			try
			{
				tailers.get(file).stop();
				tailers.remove(file);

				LOG.info("Tailer stopped: {}", file.getAbsolutePath());
			}
			catch (NullPointerException e)
			{
				// ignore
			}
		}
	}

	public synchronized void stop()
	{
		if (!running)
		{
			return;
		}

		LOG.info("File handler stopping");

		try
		{
			monitor.stop();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		synchronized (tailers)
		{
			for (File file : tailers.keySet())
			{
				tailers.get(file).stop();
				LOG.info("Tailer stopped: {}", file.getAbsolutePath());
			}
			tailers.clear();
		}

		running = false;

		LOG.info("File handler stopped");
	}

	@Override
	public void run()
	{
		LOG.info("File handler started");
		if (!tailersStarted)
		{
			try
			{
				startTailers();
				tailersStarted = true;
			}
			catch (FileNotFoundException e)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException ie)
				{
					// ignore
				}
			}
		}

	}

	private class TailerListener implements org.apache.commons.io.input.TailerListener
	{

		private final File file;

		private TailerListener(final File file)
		{
			this.file = file;
		}

		private boolean fileRotated = false;

		private boolean fileNotFound = false;

		private boolean errorFound = false;

		@Override
		public void init(final Tailer tailer)
		{
			LOG.info("Tailing file {}", file.getAbsolutePath());
		}

		@Override
		public void fileNotFound()
		{
			if (!fileNotFound)
			{
				LOG.error("Tailer error: file not found: {}", file.getAbsolutePath());
				fileNotFound = true;
			}

			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException ignored)
			{
				Thread.currentThread().interrupt();
			}
		}

		@Override
		public void fileRotated()
		{
			if (!fileRotated)
			{
				LOG.info("Tailer: file was rotated: {}", file.getAbsolutePath());
				fileRotated = true;
			}

			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException ignored)
			{
				Thread.currentThread().interrupt();
			}
		}

		@Override
		public void handle(final String line)
		{
			fileNotFound = false;
			fileRotated = false;
			errorFound = false;

			//readQueue.put(line);
			System.out.println(line);
		}

		@Override
		public void handle(final Exception ex)
		{
			if (!errorFound)
			{
				LOG.error("Tailer error", ex);
				errorFound = true;
			}

			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException ignored)
			{
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private void postLogToKafka(String messageStr, int messageNo) {
		long startTime = System.currentTimeMillis();
		if (isAsync) { // Send asynchronously
			producer.send(new ProducerRecord<>(kafkaTopic,
					String.valueOf(messageNo),
					messageStr), new DemoCallBackThread(startTime, messageNo, messageStr));
		} else { // Send synchronously
			try {
				producer.send(new ProducerRecord<>(kafkaTopic,
						String.valueOf(messageNo),
						messageStr)).get();
				LOG.debug("Sent message: (" + messageNo + ", " + messageStr + ")");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				// handle the exception
			}
		}
	}
}


