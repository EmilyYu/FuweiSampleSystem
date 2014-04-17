package com.fuwei.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.SampleDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.Sample;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.Print;
import com.fuwei.util.UploadImageName;

public class PrintSampleDetailServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		int sampleID=Integer.valueOf(req.getParameter("sampleID"));
		int companyPriceID=Integer.valueOf(req.getParameter("companyPriceID"));
		String printerName=req.getParameter("printerName");
		SampleDAO sampleDAO=new SampleDAO();
		CompanyPriceDAO companyPriceDAO=new CompanyPriceDAO();
		
		Sample sample=sampleDAO.getSample(sampleID);
		CompanyPrice companyPrice=companyPriceDAO.getCompanyPrice(companyPriceID);
		if(sample==null||companyPrice==null){
			UploadImageName uploadImageName = new UploadImageName(req
					.getRemoteAddr());
			String suijiNameString=uploadImageName.getIPTimeRand();
			ExportExcel.exportSampleDetailExcel(sample,companyPrice,this.getServletContext().getRealPath("/") + "excel/", suijiNameString+".xls");
			new Thread(new printThread(this.getServletContext().getRealPath("/") + "excel/"+suijiNameString+".xls",printerName)).start();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	class printThread implements Runnable{
		private String filePath;
		private String printerName;
		public printThread(String filePath,String printerName){
			this.filePath=filePath;
			this.printerName=printerName;
		}
		public void run() {
			Print.printExcel(filePath, 1, printerName);
				File file=new File(filePath);
				if(file.exists()){
					file.delete();
				}
		}
	}

}
