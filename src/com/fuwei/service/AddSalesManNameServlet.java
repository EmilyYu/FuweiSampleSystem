package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.SalesmanDAO;
import com.fuwei.entity.CompanySalesMan;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.HanyuPinyinUtil;
import com.fuwei.util.InitSystemDataUtil;

public class AddSalesManNameServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			String salesManName=req.getParameter("salesName");
			String companyName=req.getParameter("companyName");
			String phone=req.getParameter("phone");
			CompanySalesMan companySalesMan=new CompanySalesMan();
			companySalesMan.setCompanyName(companyName);
			companySalesMan.setSalesManName(salesManName);
			companySalesMan.setJianChen(HanyuPinyinUtil.getFirstSpellByString(salesManName));
			companySalesMan.setPhone(phone);
			
			SalesmanDAO salesmanDAO=new SalesmanDAO();
			salesmanDAO.addSample(companySalesMan);
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
			FuweiSystemData.setSalesManSpell(InitSystemDataUtil.initCompanySalesmanSpell());
			FuweiSystemData.setSalesNameByCompanyName(InitSystemDataUtil.initSalesNameByCompanyName());
		}
		
	}
	
	

}
