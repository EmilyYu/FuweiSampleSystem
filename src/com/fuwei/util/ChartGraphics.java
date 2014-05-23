package com.fuwei.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.fuwei.entity.UnPricedSample;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartGraphics {

	BufferedImage image;
	int imageWidth = 400;// 图片的宽度

	int imageHeight = 639;// 图片的高度
	
	private final int signStyleX=18;
	private static final int contentStyleX=105;
	
	private static final String LINE = "_____________________";
	private static final String STYLE_NO="款号：";
	private static final String DEVELOPER="打样：";
	private static final String SIZE="尺寸：";
	private static final String MATERIA="材料：";
	private static final String KEZHONG="克重：";
	private static final String MACHINE="机织：";
	private static final String TIME="时间：";
	

	public void createImage(OutputStream fileOutputStream,String fileLocation) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

	public void graphicsGeneration(List<UnPricedSample> unPricedSampleList,
			String imgName,OutputStream fileOutputStream) {
		System.out.println("size:"+unPricedSampleList.size());
		int totalHeight=imageHeight*unPricedSampleList.size();
		if(unPricedSampleList.size()<1){
			totalHeight=imageHeight;
		}
		
		image = new BufferedImage(imageWidth, totalHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		Font f = new Font("微软雅黑", 0, 30);
		graphics.setFont(f);
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, imageWidth, totalHeight);
		for (int i = 0; i < unPricedSampleList.size(); i++) {
			UnPricedSample unPricedSample=unPricedSampleList.get(i);
			createFormatString(graphics,unPricedSample,f,i);
		}
		graphics.dispose();

		createImage(fileOutputStream,imgName);

	}


	private void createFormatString(Graphics graphics,UnPricedSample unPricedSample,Font f,int i) {
		
		int length = unPricedSample.getMaterial().length();
		System.out.println(length);
		switch (length/10) {
		case 0:{
			graphics.setColor(Color.BLACK);
			graphics.setFont(f);
			graphics.drawString(STYLE_NO, signStyleX, 75+(i*imageHeight));
			drawString(graphics,unPricedSample.getProductNumber(), contentStyleX, 75+(i*imageHeight));
			graphics.setFont(f);
			graphics.drawString(DEVELOPER, signStyleX, 150+(i*imageHeight));
			drawString(graphics,FuweiSystemData.getDeveloperById(unPricedSample.getDeveloperId()).getName() , contentStyleX,150+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(SIZE, signStyleX,225+(i*imageHeight));
			drawString(graphics,unPricedSample.getSize(), contentStyleX,225+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MATERIA, signStyleX,300+(i*imageHeight));
			drawString(graphics,unPricedSample.getMaterial(), contentStyleX,300+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(KEZHONG, signStyleX,375+(i*imageHeight));
			drawString(graphics,unPricedSample.getWeight()+"克", contentStyleX,375+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MACHINE, signStyleX,450+(i*imageHeight));
			drawString(graphics,unPricedSample.getMachine(), contentStyleX,450+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(TIME, signStyleX,525+(i*imageHeight));
			drawString(graphics,DateFormateUtil.formateDate(unPricedSample.getDate()), contentStyleX,525+(i*imageHeight));
		}

			break;
		case 1:{
			
			graphics.setColor(Color.BLACK);
			graphics.setFont(f);
			graphics.drawString(STYLE_NO, signStyleX, 75+(i*imageHeight));
			drawString(graphics,unPricedSample.getProductNumber(), contentStyleX, 75+(i*imageHeight));
			graphics.setFont(f);
			graphics.drawString(DEVELOPER, signStyleX, 150+(i*imageHeight));
			drawString(graphics,FuweiSystemData.getDeveloperById(unPricedSample.getDeveloperId()).getName(), contentStyleX,150+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(SIZE, signStyleX,225+(i*imageHeight));
			drawString(graphics,unPricedSample.getSize(), contentStyleX,225+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MATERIA, signStyleX,300+(i*imageHeight));
			drawString(graphics,unPricedSample.getMaterial().subSequence(0, 9).toString(), contentStyleX,300+(i*imageHeight));
			graphics.setFont(f);
			drawString(graphics,unPricedSample.getMaterial().subSequence(9, unPricedSample.getMaterial().length()).toString(), contentStyleX,340+(i*imageHeight));

			graphics.setFont(f);
			graphics.drawString(KEZHONG, signStyleX,410+(i*imageHeight));
			drawString(graphics,unPricedSample.getWeight()+"克", contentStyleX,410+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MACHINE, signStyleX,485+(i*imageHeight));
			drawString(graphics,unPricedSample.getMachine(), contentStyleX,485+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(TIME, signStyleX,560+(i*imageHeight));
			drawString(graphics,DateFormateUtil.formateDate(unPricedSample.getDate()), contentStyleX,560+(i*imageHeight));

			
		}
			break;

		case 2:{
			graphics.setColor(Color.BLACK);
			graphics.setFont(f);
			graphics.drawString(STYLE_NO, signStyleX, 55+(i*imageHeight));
			drawString(graphics,unPricedSample.getProductNumber(), contentStyleX, 55+(i*imageHeight));
			graphics.setFont(f);
			graphics.drawString(DEVELOPER, signStyleX, 130+(i*imageHeight));
			drawString(graphics,FuweiSystemData.getDeveloperById(unPricedSample.getDeveloperId()).getName(), contentStyleX,130+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(SIZE, signStyleX,205+(i*imageHeight));
			drawString(graphics,unPricedSample.getSize(), contentStyleX,205+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MATERIA, signStyleX,280+(i*imageHeight));
			drawString(graphics,unPricedSample.getMaterial().subSequence(0, 9).toString(), contentStyleX,280+(i*imageHeight));
			graphics.setFont(f);
			drawString(graphics,unPricedSample.getMaterial().subSequence(9,18).toString(), contentStyleX,320+(i*imageHeight));
			graphics.setFont(f);
			drawString(graphics,unPricedSample.getMaterial().subSequence(18,unPricedSample.getMaterial().length()).toString(), contentStyleX,360+(i*imageHeight));

			graphics.setFont(f);
			graphics.drawString(KEZHONG, signStyleX,420+(i*imageHeight));
			drawString(graphics,unPricedSample.getWeight()+"克", contentStyleX,20+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MACHINE, signStyleX,495+(i*imageHeight));
			drawString(graphics,unPricedSample.getMachine(), contentStyleX,495+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(TIME, signStyleX,570+(i*imageHeight));
			drawString(graphics,DateFormateUtil.formateDate(unPricedSample.getDate()), contentStyleX,570+(i*imageHeight));

	
		}
			break;

		default:{
			graphics.setColor(Color.BLACK);
			graphics.drawString(STYLE_NO, signStyleX, 75+(i*imageHeight));
			drawString(graphics,unPricedSample.getProductNumber(), contentStyleX, 75+(i*imageHeight));
			graphics.setFont(f);
			graphics.drawString(DEVELOPER, signStyleX, 150+(i*imageHeight));
			drawString(graphics,FuweiSystemData.getDeveloperById(unPricedSample.getDeveloperId()).getName(), contentStyleX,150+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(SIZE, signStyleX,225+(i*imageHeight));
			drawString(graphics,unPricedSample.getSize(), contentStyleX,225+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MATERIA, signStyleX,300+(i*imageHeight));
			drawString(graphics,"", contentStyleX,300+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(KEZHONG, signStyleX,375+(i*imageHeight));
			drawString(graphics,unPricedSample.getWeight()+"克", contentStyleX,375+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(MACHINE, signStyleX,450+(i*imageHeight));
			drawString(graphics,unPricedSample.getMachine(), contentStyleX,450+(i*imageHeight));
			
			graphics.setFont(f);
			graphics.drawString(TIME, signStyleX,525+(i*imageHeight));
			drawString(graphics,DateFormateUtil.formateDate(unPricedSample.getDate()), contentStyleX,525+(i*imageHeight));

		}
			break;
		}
	}
	
	
	private void drawString(Graphics g,String str,int xPoint,int yPoint){
		g.drawString(LINE, contentStyleX, yPoint+1);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("宋体", Font.BOLD, 30)); 
		FontMetrics fm = g2d.getFontMetrics();
		int stringWidth = fm.stringWidth(str); 
		int x = (imageWidth-xPoint-10) / 2 - stringWidth / 2; 
		g2d.drawString(str,contentStyleX+ x, yPoint); 
	
	}
}