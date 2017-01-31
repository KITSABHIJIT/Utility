package com.producerConsumer.test;

public class Producer extends Thread {
	private Container container;
	private int number;

	public Producer(Container c, int number) {
		container = c;
		this.number = number;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			container.put(i);
			System.out.println("Producer #" + this.number
					+ " put: " + i);
			try {
				Thread.sleep((int)(Math.random() * 100));
			} catch (InterruptedException e) { }
		}
	}
}
