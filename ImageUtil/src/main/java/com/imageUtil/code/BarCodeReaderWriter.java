package com.imageUtil.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarCodeReaderWriter {

	public static void main(String[] args) throws WriterException, IOException,
	NotFoundException {
		String BarCodeData = "Hello World!";
		String destination = CommonConstants.FILE_PATH;
		String filePath = destination+System.getProperty("file.separator")+"BarCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		File dest = new File(destination);
		if(!dest.exists()){
			dest.mkdirs();
		}
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		createBarCode(BarCodeData, filePath, charset, hintMap, 100, 500);
		System.out.println("Bar Code image created successfully!");

		System.out.println("Data read from Bar Code: "
				+ readBarCode(filePath, charset, hintMap));

	}

	public static void createBarCode(String BarCodeData, String filePath,
			String charset, Map<EncodeHintType, Object> hintMap, int BarCodeheight, int BarCodewidth)
					throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(BarCodeData.getBytes(charset), charset),
				BarcodeFormat.CODE_128, BarCodewidth, BarCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readBarCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result BarCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return BarCodeResult.getText();
	}

}
