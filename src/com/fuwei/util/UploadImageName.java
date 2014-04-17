package com.fuwei.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 根据上传者的ip 当前时间戳以及三位随机数生存一个ip=9位 时间戳=17位 随机数=3位的文件名
 */
public class UploadImageName {
	private SimpleDateFormat sdf = null;
	private String ip= null;
	public UploadImageName(){
		
	}
	
	public UploadImageName(String ip){
		this.ip=ip;
	}
	
	public String getIPTimeRand(){
		StringBuffer buf=new StringBuffer();
//		if(this.ip!=null){
//			String s[]=this.ip.split("\\.");
//			for (int i = 0; i < s.length; i++) {
//				buf.append(this.addZero(s[i], 3));
//			}
//		}
		buf.append(this.getTimeStamp());
		Random r=new Random();
		for (int i = 0; i < 3; i++) {
			buf.append(r.nextInt(10));
		}
		return buf.toString();
	}
	public String getDate(){
		this.sdf=new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss:SSS");
		return this.sdf.format(new Date());
	}
	public String getTimeStamp(){
		this.sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return this.sdf.format(new Date());
	}
	
	public String addZero(String str,int len){
		StringBuffer s=new StringBuffer();
		s.append(str);
		while (s.length()<len) {
			s.insert(0, "0");
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new UploadImageName("192.168.1.1").getIPTimeRand());
	}
}
