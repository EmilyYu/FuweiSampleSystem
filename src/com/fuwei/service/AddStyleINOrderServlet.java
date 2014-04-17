package com.fuwei.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;

import net.sf.json.JSONObject;

import com.fuwei.DAO.StyleInOrderDAO;
import com.fuwei.entity.StyleINOrder;
import com.fuwei.util.UploadImageName;
import com.fuwei.util.YaSuoPicture;
import com.jspsmart.upload.SmartUpload;

public class AddStyleINOrderServlet extends HttpServlet {
	SmartUpload smartUpload;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
		try {
			StyleINOrder styleINOrder=new StyleINOrder();
			String styleNumber=smartUpload.getRequest().getParameter("styleNumber");
			String styleName=smartUpload.getRequest().getParameter("styleName");
			String material=smartUpload.getRequest().getParameter("material");
			String shazhi=smartUpload.getRequest().getParameter("shazhi");
			String color=smartUpload.getRequest().getParameter("color");
			String size=smartUpload.getRequest().getParameter("size");
			double kezhong=Double.valueOf(smartUpload.getRequest().getParameter("kezhong"));
			int quantity=Integer.valueOf(smartUpload.getRequest().getParameter("quantity"));
			double price=Double.valueOf(smartUpload.getRequest().getParameter("price"));
			double expectedMaterialTotal=Double.valueOf(smartUpload.getRequest().getParameter("expectedMaterialTotal"));
			int fwOrderID=Integer.valueOf(smartUpload.getRequest().getParameter("fwOrderID"));
			String note=smartUpload.getRequest().getParameter("note");
			if(note==null||note.length()<1){
				styleINOrder.setNote("");
			}else {
				styleINOrder.setNote(note);
			}
			styleINOrder.setStyleName(styleName);
			styleINOrder.setStyleNumber(styleNumber);
			styleINOrder.setMaterial(material);
			styleINOrder.setShazhi(shazhi);
			styleINOrder.setColor(color);
			styleINOrder.setSize(size);
			styleINOrder.setKezhong(kezhong);
			styleINOrder.setQuantity(quantity);
			styleINOrder.setPrice(price);
			styleINOrder.setExpectedMaterialTotal(expectedMaterialTotal);
			styleINOrder.setFWOrderID(fwOrderID);
			String pictureName=uploadImage(req, resp);
			styleINOrder.setPictureName(pictureName);
			
			StyleInOrderDAO styleInOrderDAO=new StyleInOrderDAO();
			styleInOrderDAO.addStyleINOrder(styleINOrder);
			getYaSuoPicture(pictureName);
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
	
	private String uploadImage(HttpServletRequest req, HttpServletResponse resp) {

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
		return fileName;
	}
	
	private void getYaSuoPicture(String pictureName) {
		String realPath = this.getServletContext().getRealPath("/") + "image";

		String path = changDanXieGangTOShuangXieGang(realPath);
		YaSuoPicture yaSuoPicture = new YaSuoPicture();
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

}
