package com.fuwei.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;

import net.sf.json.JSONObject;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.SampleDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.Sample;
import com.fuwei.util.UploadImageName;
import com.fuwei.util.YaSuoPicture;
import com.jspsmart.upload.SmartUpload;

public class AddSampleServlet extends HttpServlet {
	SmartUpload smartUpload;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

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
		System.out.println("收到");

		try {
			// 公司
	/*		CompanyPrice companyPrice = new CompanyPrice();

			companyPrice.setProductName((String) smartUpload.getRequest()
					.getParameter("companyNumber"));
			companyPrice.setNote((String) smartUpload.getRequest()
					.getParameter("companyNote"));
			companyPrice.setPrice(Double.valueOf(smartUpload.getRequest()
					.getParameter("companyPrice")));
			companyPrice.setSalesMan((String) smartUpload.getRequest()
					.getParameter("salesMan"));
			companyPrice.setCompanyName((String) smartUpload.getRequest()
					.getParameter("companyName"));
			companyPrice.setTime(new Date());*/

			String productName = smartUpload.getRequest().getParameter(
					"productName");
			System.out.println("product===" + productName);

			String material = smartUpload.getRequest().getParameter("material");
			System.out.println("product:" + productName);
			double sampleweight = Double.valueOf(smartUpload.getRequest()
					.getParameter("kezhong"));
			System.out.println("+++" + sampleweight);
			String size = smartUpload.getRequest().getParameter("size");
			String detail = smartUpload.getRequest().getParameter("detail");
			System.out.println("材料：" + material);
			double samplecost = Double.valueOf(smartUpload.getRequest()
					.getParameter("samplecost"));
			System.out.println("cost:" + samplecost);

			String pictureName = uploadImage(req, resp);
			System.out.println("上传结束===" + pictureName);

			Sample sample = new Sample();
			sample.setProductNumber(productName);
			sample.setMaterial(material);
			sample.setWeight(sampleweight);
			sample.setSize(size);
			sample.setDetail(detail);
			sample.setCost(samplecost);
			sample.setDate(new Date());
			sample.setPicturePath(pictureName);

			//addSample(sample, companyPrice);
			addSample(sample);
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

	private void addSample(Sample sample/*, CompanyPrice companyPrice*/) {
		SampleDAO sampleDAO = new SampleDAO();
		int returnID = sampleDAO.addSample(sample);
		/*if (returnID > -1) {
			addCompanyPrice(companyPrice, returnID);
		}*/
	}

	private void addCompanyPrice(CompanyPrice companyPrice, int sampleId) {
		CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
		companyPrice.setSampleId(sampleId);
		companyPriceDAO.addCompanyPrice(companyPrice);
	}

	private String uploadImage(HttpServletRequest req, HttpServletResponse resp) {

		UploadImageName uploadImageName = new UploadImageName(req
				.getRemoteAddr());
		System.out.println("======================");
		String ext = smartUpload.getFiles().getFile(0).getFileExt();
		System.out.println("======================");
		String fileName = uploadImageName.getIPTimeRand() + "." + ext;
		System.out.println("======================");
		try {
			smartUpload.getFiles().getFile(0).saveAs(
					this.getServletContext().getRealPath("/") + "image"
							+ File.separator + fileName);
			System.out.println("======================");
		} catch (Exception e1) {
			e1.printStackTrace();
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

}
