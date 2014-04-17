package com.fuwei.service;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.DeveloperDAO;
import com.fuwei.DAO.QuotationListDAO;
import com.fuwei.DAO.SampleDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.Quotation;
import com.fuwei.entity.QuotationList;
import com.fuwei.entity.Sample;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.FuweiSystemData;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String string=req.getParameter("key");
		System.out.println("key:"+string);
/*		QuotationListDAO quotationListDAO=new QuotationListDAO();
		QuotationList quotationList=(QuotationList)quotationListDAO.getQuotationListById(7);
		req.setAttribute("quotationList", quotationList);
		req.getRequestDispatcher("quotationListDetail.jsp").forward(req, resp);*/
		/*int id=Integer.valueOf(req.getParameter("id"));
		
		req.setAttribute("operationType", FuweiSystemData.ADD_QUOTATION);
		req.setAttribute("companyPriceID", id);
		
		req.getRequestDispatcher("quotationServlet").forward(req, resp);*/
		
	/*	Enumeration enuma=req.getParameterNames();
		while (enuma.hasMoreElements()) {
			String object = (String) enuma.nextElement();
			System.out.println("参数名："+object);
		}		
		Set<Integer> set=new HashSet<Integer>();
		for (int i = 12; i < 33; i++) {
			set.add(i);
			System.out.println("id:   "+i);
		}
		
		SampleDAO sampleDAO=new SampleDAO();
		List<Sample>samples=(List<Sample>)sampleDAO.searchSampleBYIDSet(set, 10, 10);
		
		for (Sample sample : samples) {
			System.out.println("sample id:"+sample.getId());
		}
		*/
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
