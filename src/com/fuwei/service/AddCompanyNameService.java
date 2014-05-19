package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.CompanyNameDAO;
import com.fuwei.entity.Company;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.InitSystemDataUtil;

public class AddCompanyNameService extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		try {
			String companyName=req.getParameter("companyName");
			String jianChen=req.getParameter("companyName_jc");
			String address=req.getParameter("address");
			String phone=req.getParameter("phone");
			String quanChen=req.getParameter("quanChen");
			String destination=req.getParameter("destination");
			Company companyNameEntity=new Company();
			companyNameEntity.setCompanyName(companyName);
			companyNameEntity.setJianChen(jianChen);
			companyNameEntity.setAddress(address);
			companyNameEntity.setQuanChen(quanChen);
			companyNameEntity.setDestination(destination);
			
			CompanyNameDAO companyNameDAO=new CompanyNameDAO();
			companyNameDAO.addCompanyName(companyNameEntity);
			
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
			FuweiSystemData.setCompanyNameList(InitSystemDataUtil.initCompanyName());
		}
		
	}

}
