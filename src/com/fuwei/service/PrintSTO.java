package com.fuwei.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.fuwei.DAO.CompanyDAO;
import com.fuwei.DAO.SalesmanDAO;
import com.fuwei.entity.Company;
import com.fuwei.entity.FWUser;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.Print;
import com.fuwei.util.PrinterNameUtil;
import com.fuwei.util.UploadImageName;

public class PrintSTO extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			HttpSession session=req.getSession();
			FWUser user=(FWUser)session.getAttribute("user");
			if(user==null){
				resp.sendRedirect("login.jsp");
			}else {
				String companyName=req.getParameter("companyName");
				String salesName=req.getParameter("salesman");
				
				SalesmanDAO salesmanDAO=new SalesmanDAO();
				String phoneNumber=salesmanDAO.getSalesManNamePhoneNumber(salesName);
				CompanyDAO companyDAO=new CompanyDAO();
				List<Company> companyList=(List<Company>)companyDAO.getCompanyByName(companyName);
				Company company=companyList.get(0);
				UploadImageName uploadImageName = new UploadImageName();
				String suijiNameString = uploadImageName.getIPTimeRand();
				String printerName=PrinterNameUtil.getPrinterName("Jolimark");
				if(printerName!=null){
					System.out.println("打印机名称："+printerName);
					ExportExcel.exportKuaiDiDan(this.getServletContext().getRealPath("/") + "excel/",  suijiNameString+".xls", user.getChineseName(), company.getQuanChen(), salesName, company.getDestination(), company.getAddress(), phoneNumber);
					System.out.println("打印前");
					new Thread(new printThread(this.getServletContext().getRealPath("/") + "excel/"+suijiNameString+".xls",printerName)).start();
					System.out.println("打印后");
					JSONObject jObject = new JSONObject();
					jObject.put("OK", true);
					PrintWriter printWriter = resp.getWriter();
					printWriter.write(jObject.toString());
					printWriter.flush();
					printWriter.close();
				}else {
					JSONObject jObject = new JSONObject();
					jObject.put("OK", false);
					jObject.put("message","没有找到打印机，请检查打印机是否已经开机！");
					PrintWriter printWriter = resp.getWriter();
					printWriter.write(jObject.toString());
					printWriter.flush();
					printWriter.close();
				}
			}
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
