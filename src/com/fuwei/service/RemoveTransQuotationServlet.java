package com.fuwei.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.QuotationDAO;

public class RemoveTransQuotationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			int companyPrice = Integer.valueOf(req.getParameter("companyPriceId"));
			System.out.println("id:=="+companyPrice);
			QuotationDAO quotationDAO=new QuotationDAO();
			Set<Integer> set=new HashSet<Integer>();
			set.add(Integer.valueOf(companyPrice));
			System.out.println("set size:"+set.size());
			quotationDAO.deleteQutationWithCompanyPriceIDSet(set);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}finally{
			req.getRequestDispatcher("searchTransQuotation.do").forward(req, resp);
		}
		
		
	}

}
