package com.fuwei.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.QuotationListDAO;
import com.fuwei.entity.QuotationList;

public class QuotationListTOQuotationListDetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		int id=Integer.valueOf(req.getParameter("id"));
		QuotationListDAO quotationListDAO=new QuotationListDAO();
		QuotationList quotationList=(QuotationList)quotationListDAO.getQuotationListById(id);
		req.setAttribute("quotationList", quotationList);
		req.getRequestDispatcher("quotationListDetail.jsp").forward(req, resp);
	}
	
}
