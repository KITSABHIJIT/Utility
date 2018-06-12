package com.imageUtil.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.WriterException;

public class CapthaGenerator {
	public static void main(String[] args) {
		String captcha=null;
		int width = 150;
		int height = 50;
		String destination = CommonConstants.FILE_PATH;
		String filePath = destination+System.getProperty("file.separator")+"captha.png";
		String fileType = "png";
		File dest = new File(destination);
		if(!dest.exists()){
			dest.mkdirs();
		}
		try { 
			File myFile = new File(filePath);
			List<Character> arrayList = new ArrayList<Character>();
			String capcode = "abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMONOPQURSTUVWXYZ0123456789!@#$%&*";
			for (int i = 1; i < capcode.length() - 1; i++) {
				arrayList.add(capcode.charAt(i));
			}
			Collections.shuffle(arrayList);
			Iterator<Character> itr = arrayList.iterator();
			String s = "";
			String s2 = "";
			Object obj;
			while (itr.hasNext()) {
				obj = itr.next();
				s = obj.toString();
				s2 = s2 + s;
			}
			String s1 = s2.substring(0, 6);
			char[] s3 = s1.toCharArray();
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bufferedImage.createGraphics();
			Font font = new Font("Georgia", Font.BOLD, 22);
			g2d.setFont(font);
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setRenderingHints(rh);
			GradientPaint gp = new GradientPaint(0, 0, Color.red, 0, height / 2, Color.black, true);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, width, height);
			g2d.setColor(new Color(255, 153, 0));
			Random r = new Random();
			captcha = String.copyValueOf(s3);
			int x = 0;
			int y = 0;
			for (int i = 0; i < s3.length; i++) {
				x += 10 + (Math.abs(r.nextInt()) % 15);
				y = 20 + Math.abs(r.nextInt()) % 20;
				g2d.drawChars(s3, i, 1, x, y);
			}
			g2d.dispose();
			ImageIO.write(bufferedImage, fileType, myFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\nYou have successfully created Captha Code with text: "+captcha);
	}
}
