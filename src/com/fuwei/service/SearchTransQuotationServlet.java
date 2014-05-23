package com.fuwei.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.QuotationDAO;
import com.fuwei.DAO.SampleDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.Quotation;
import com.fuwei.entity.Sample;
import com.fuwei.entity.TransQuotation;

public class SearchTransQuotationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			QuotationDAO quotationDAO=new QuotationDAO();
			List<Quotation> quotationList=(List<Quotation>)quotationDAO.getAllQuotation();
			CompanyPriceDAO companyPriceDAO=new CompanyPriceDAO();
			SampleDAO sampleDAO=new SampleDAO();
			HashMap<String, List<TransQuotation>> hashMap=new HashMap<String, List<TransQuotation>>();
			for (Quotation quotation : quotationList) {
				TransQuotation transQuotation=new TransQuotation();
				CompanyPrice companyPrice=companyPriceDAO.getCompanyPrice(quotation.getCompanyPriceID());
				Sample sample=sampleDAO.getSample(companyPrice.getSampleId());
				transQuotation.setCompanyId(companyPrice.getCompanyId());
				transQuotation.setCompanyPriceID(companyPrice.getId());
				transQuotation.setFwStyleNumber(sample.getProductNumber());
				transQuotation.setKezhong(sample.getWeight());
				transQuotation.setPictureName("ss"+sample.getPicturePath());
				transQuotation.setPrice(companyPrice.getPrice());
				transQuotation.setSampleID(sample.getId());
				transQuotation.setSalesManId(companyPrice.getSalesManId());
				String tempString=transQuotation.getCompanyId()+","+transQuotation.getSalesManId();
				if(hashMap.containsKey(tempString)){
					hashMap.get(tempString).add(transQuotation);
				}else {
					List<TransQuotation> tList=new ArrayList<TransQuotation>();
					tList.add(transQuotation);
					hashMap.put(tempString, tList);
				}
			}
			
			req.setAttribute("transQuotationHashMap", hashMap);
			req.getRequestDispatcher("samplecar.jsp").forward(req, resp);
			
		} catch (Exception e) {
			req.setAttribute("transQuotationHashMap",new HashMap<String, List<TransQuotation>>());
			req.getRequestDispatcher("samplecar.jsp").forward(req, resp);
		}
	}

}
