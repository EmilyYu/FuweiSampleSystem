package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.entity.CompanyPrice;

public class AddCompanyPriceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			int sampleID = Integer.valueOf(req.getParameter("sampleid"));
			String companyName = req.getParameter("companyName");
			String productNumber = req.getParameter("productNumber");
			double price = Double.valueOf(req.getParameter("price"));
			String salesman = req.getParameter("salesman");
			String note = req.getParameter("note");
			CompanyPrice companyPrice = new CompanyPrice();
			companyPrice.setCompanyName(companyName);
			companyPrice.setProductName(productNumber);
			companyPrice.setNote(note);
			companyPrice.setPrice(price);
			companyPrice.setSalesMan(salesman);
			companyPrice.setSampleId(sampleID);
			companyPrice.setTime(new Date());
			CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
			companyPriceDAO.addCompanyPrice(companyPrice);
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

}
