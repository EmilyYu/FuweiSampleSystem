package com.fuwei.entity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.ProductionNotificationDAO;

public class PrintProductNotificationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		try {
			int id=Integer.valueOf(req.getParameter("productNotificationID"));
			String printerName=req.getParameter("printerName");

			ProductionNotificationDAO productionNotificationDAO=new ProductionNotificationDAO();
			ProductionNotification ProductionNotification=productionNotificationDAO.getProductionNotification(id);
			
			
		} catch (Exception e) {
		}
		
	}

}
