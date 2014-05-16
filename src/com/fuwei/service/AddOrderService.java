package com.fuwei.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.FWOrderDAO;
import com.fuwei.entity.FWOrder;
import com.fuwei.util.StringTODate;
import com.jspsmart.upload.SmartUpload;

public class AddOrderService extends HttpServlet {
	SmartUpload smartUpload;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		try {
			
			String companyOrderNumber=req.getParameter("companyOrderNumber");
			String companyName=req.getParameter("companyName");
			String orderTimeString=req.getParameter("orderTime");
			if(StringTODate.canChangeStringTODate(orderTimeString)){
				Date orderTime=StringTODate.changeStringTODate(orderTimeString);
				String salesMan=req.getParameter("salesMan");
				String developer=req.getParameter("developer");
				String note=req.getParameter("note");
				FWOrder order=new FWOrder();
//				order.setCompanyName(companyName);
//				order.setCompanyOrderNumber(companyOrderNumber);
//				order.setDeveloper(developer);
//				order.setFwOrderNumber("");
//				order.setNote(note);
//				order.setOrderTime(orderTime);
//				order.setSalesManName(salesMan);
				FWOrderDAO orderDAO=new FWOrderDAO();
				orderDAO.addFWOrder(order);
			}else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	
}
