package com.myutility.code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class ImageUtil {

	 //public static String imageUrl="https://farm9.staticflickr.com/8362/8254601537_fed9a15a47_o.jpg";
	 //public static String imageUrl="http://www.cityofmeriden.org/Customer-Content/WWW/CMS/images/HHS/DJI_SeptDoodlers_ABC_c.jpg";
	 public static String imageUrl="http://esbisbt_nd_prf.staples.com/invoke/SplsManaageDocument.sbdlegacyoms.v1.services/recvPODImageRetrievalReq?ImageName=0510005004233000001_000000000.jpg";
	 public static String destinationFile="result.txt";

	 public static void main(String[] args) throws Exception {
			long entrytime=System.currentTimeMillis();
			saveImage(imageUrl, destinationFile);
			//saveImageRead(imageUrl, destinationFile);
			long exittime=System.currentTimeMillis();
			System.out.println("Image read processing time :"+DateUtil.getHrMinSec(exittime-entrytime));
		}
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		
		URLConnection openConnection = new URL(imageUrl).openConnection();	
		openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		InputStream is = openConnection.getInputStream();
		
		//URL url = new URL(imageUrl);
		//InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);
		
		byte[] b = new byte[2048];
		int length;
		int size=1024;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
			size+=length;
		}
		System.out.println("Image URL :"+imageUrl);
		System.out.println("Size of the file fetched :"+size/1024+" Kb");
		is.close();
		os.close();
	}

	public static void saveImageRead(String imageUrl, String destinationFile) throws IOException {
        try {
            URL url = new URL(imageUrl);
            final BufferedImage bi = ImageIO.read(url);
            final String size = bi.getWidth() + "x" + bi.getHeight();
            File outputfile = new File(destinationFile);
            ImageIO.write(bi, "jpg", outputfile);
            System.out.println("Image Size: "+size);
        } catch (IOException e) {
        	e.printStackTrace();
        }
 
	
	}

}
