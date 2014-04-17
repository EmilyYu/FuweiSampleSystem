package com.fuwei.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;

import net.sf.json.JSONObject;

import com.fuwei.DAO.UnPricedSampleDAO;
import com.fuwei.entity.UnPricedSample;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.PictureFormateUtil;
import com.fuwei.util.PrintExcel;
import com.fuwei.util.UploadImageName;
import com.fuwei.util.YaSuoPicture;
import com.jspsmart.upload.SmartUpload;

public class AddUnPricedSampleServlet extends HttpServlet {
	SmartUpload smartUpload;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("收到");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		smartUpload = new SmartUpload();
		smartUpload.initialize(JspFactory.getDefaultFactory().getPageContext(
				this, req, resp, null, true, 8192, true));
		try {
			smartUpload.upload();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}
		System.out.println("收到");

		try {
			String material = smartUpload.getRequest().getParameter("material");
			String machine = smartUpload.getRequest().getParameter("machine");
			double sampleweight = Double.valueOf(smartUpload.getRequest()
					.getParameter("kezhong"));
			String size = smartUpload.getRequest().getParameter("size");
			String developer = smartUpload.getRequest().getParameter("developer");
			String note = smartUpload.getRequest().getParameter("note");
		
			String pictureName = uploadImage(req, resp);
			UnPricedSample sample=new UnPricedSample();
			sample.setProductNumber("");
			sample.setMaterial(material);
			sample.setWeight(sampleweight);
			sample.setSize(size);
			sample.setDate(new Date());
			sample.setPicturePath(pictureName);
			sample.setDeveloper(developer);
			sample.setMachine(machine);
			if(note==null){
				sample.setNote("");
			}else {
				sample.setNote(note);
			}
			
			addUnPricedSample(sample);
			getYaSuoPicture(pictureName);
			UploadImageName uploadImageName = new UploadImageName(req
					.getRemoteAddr());
			String suijiNameString=uploadImageName.getIPTimeRand();
			List<UnPricedSample> unPricedSampleList=new ArrayList<UnPricedSample>();
			unPricedSampleList.add(sample);
			ExportExcel.exportSampleSignExcel(unPricedSampleList,this.getServletContext().getRealPath("/") + "excel/", suijiNameString+".xls");
			new Thread(new printThread(this.getServletContext().getRealPath("/") + "excel/"+suijiNameString+".xls")).start();
		
			JSONObject jObject = new JSONObject();
			jObject.put("OK", true);
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}

	}

	private void addUnPricedSample(UnPricedSample sample) {
		UnPricedSampleDAO sampleDAO = new UnPricedSampleDAO();
		int returnID = sampleDAO.addUnPricedSample(sample);
	}
	private String uploadImage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		UploadImageName uploadImageName = new UploadImageName(req
				.getRemoteAddr());
		String ext = smartUpload.getFiles().getFile(0).getFileExt();
		String fileName = uploadImageName.getIPTimeRand() + "." + ext;
		try {
			smartUpload.getFiles().getFile(0).saveAs(
					this.getServletContext().getRealPath("/") + "image"
							+ File.separator + fileName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(!PictureFormateUtil.checkPictureFormat(fileName)){
			fileName=PictureFormateUtil.formateTOPNG(fileName, this.getServletContext().getRealPath("/") + "image"
							+ File.separator);
		}
		return fileName;
	}

	private void getYaSuoPicture(String pictureName) {
		System.out.println(this.getServletContext().getRealPath("/") + "image");
		String realPath = this.getServletContext().getRealPath("/") + "image";

		String path = changDanXieGangTOShuangXieGang(realPath);
		System.out.println("转换后path:" + path);
		YaSuoPicture yaSuoPicture = new YaSuoPicture();
		/*
		 * yaSuoPicture.compressPic(this.getServletContext().getRealPath("/") +
		 * "image", this.getServletContext().getRealPath("/") + "image/",
		 * pictureName, "s" + pictureName, 500, 500, true);
		 */

		yaSuoPicture.compressPic(path, path, pictureName, "s" + pictureName,
				350, 350, true);
		yaSuoPicture.compressPic(path, path, pictureName, "ss" + pictureName,
				120, 120, true);
	}

	private String changDanXieGangTOShuangXieGang(String path) {
		String[] strings = path.split("\\\\");
		StringBuffer path2 = new StringBuffer();
		for (String string : strings) {
			path2.append(string + "\\\\");
		}
		return path2.toString();
	}
	
	class printThread implements Runnable{
		private String filePath;
		public printThread(String filePath){
			this.filePath=filePath;
		}
		public void run() {
			PrintExcel.printExcel(filePath);
			File file=new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		
	}

}
