package com.fuwei.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.SampleDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.Sample;

public class SearchSampleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("utf-8");
		int id=Integer.valueOf(req.getParameter("id"));
		//System.out.println(productNumber);
		SampleDAO sampleDAO=new SampleDAO();
		Sample sample=(Sample)sampleDAO.getSample(id);
		if(sample!=null){
			
			CompanyPriceDAO companyPriceDAO=new CompanyPriceDAO();
			List<CompanyPrice> companys=(List<CompanyPrice>)companyPriceDAO.searchCompanyPriceBySampleID(sample.getId());
			req.setAttribute("sample", sample);
			req.setAttribute("companys", companys);
			req.getRequestDispatcher("sampleDetail.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("nosearch.jsp").forward(req, resp);
		}
		System.out.println(sample.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String productNumber=req.getParameter("product");
		//System.out.println(productNumber);
		SampleDAO sampleDAO=new SampleDAO();
		Sample sample=(Sample)sampleDAO.searchSampleByProductNumber(productNumber);
		if(sample!=null){
			
			CompanyPriceDAO companyPriceDAO=new CompanyPriceDAO();
			List<CompanyPrice> companys=(List<CompanyPrice>)companyPriceDAO.searchCompanyPriceBySampleID(sample.getId());
			req.setAttribute("sample", sample);
			req.setAttribute("companys", companys);
			req.getRequestDispatcher("sampleDetail.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("nosearch.jsp").forward(req, resp);
		}
	}

}
