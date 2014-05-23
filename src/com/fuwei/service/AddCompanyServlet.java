package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.CompanyDAO;
import com.fuwei.entity.Company;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.InitSystemDataUtil;

public class AddCompanyServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		try {
			String companyName=req.getParameter("companyName");
			String jianChen=req.getParameter("companyName_jc");
			String address=req.getParameter("address");
			
			String quanChen=req.getParameter("quanChen");
			String destination=req.getParameter("destination");
			Company companyEntity=new Company();
			companyEntity.setName(companyName);
			companyEntity.setJianChen(jianChen);
			companyEntity.setAddress(address);
			companyEntity.setQuanChen(quanChen);
			companyEntity.setDestination(destination);
			
			CompanyDAO companyDAO=new CompanyDAO();
			companyDAO.addCompany(companyEntity);
			
			new Thread(new threadClass()).start();
			JSONObject jObject = new JSONObject();
			jObject.put("OK", true);
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}
		
	}
	
	class threadClass implements Runnable{

		public void run() {
			FuweiSystemData.setCompanyNameSpell(InitSystemDataUtil.initCompanyNameSpell());
			FuweiSystemData.setCompanyList(InitSystemDataUtil.initCompanyList());
		}
		
	}

}
