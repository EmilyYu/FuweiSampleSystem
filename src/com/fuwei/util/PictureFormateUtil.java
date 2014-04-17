package com.fuwei.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PictureFormateUtil {
	// JGP格式
	public static final String JPG = "jpg";
	// GIF格式
	public static final String GIF = "gif";
	// PNG格式
	public static final String PNG = "png";
	// BMP格式
	public static final String BMP = "bmp";

	public static String formateTOPNG(String imgFileName,String imgPath)
			throws IOException {
		if(imgFileName.length()>3){
			String formatFileName=imgFileName.subSequence(0, imgFileName.length()-3)+"png";
			File imgFile=new File(imgPath+imgFileName);
			File formatFile=new File(imgPath+formatFileName);
			BufferedImage bIMG = ImageIO.read(imgFile);
			ImageIO.write(bIMG, PNG, formatFile);
			return formatFileName;
		}
		return null;
		
	}
	
	public static boolean checkPictureFormat(String fileName){
		int length=fileName.length();
		String string=fileName.subSequence(length-3, length).toString();
		if(string.equalsIgnoreCase(PNG)){
			return true;
		}else {
			return false;
		}
	}
}
