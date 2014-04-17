package com.fuwei.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.ProductionNotificationDAO;
import com.fuwei.entity.ProductionNotification;
import com.fuwei.util.StringTODate;

public class AddProductionNotificationServlet extends HttpServlet {

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
			int fworderID=Integer.valueOf(req.getParameter("fworderID"));
			String jiagongdanwei=req.getParameter("jiagongdanwei");
			String companyName=req.getParameter("companyName");
			String fworderNumber=req.getParameter("fworderNumber");
			String styleNumber=req.getParameter("styleNumber");
			String productName=req.getParameter("productName");
			int exceptProductQuantity=Integer.valueOf(req.getParameter("excepyProductQuantity"));
			double kezhong=Double.valueOf(req.getParameter("kezhong"));
			String deadlineTimeString=req.getParameter("deadlineTime");
			String machineZhenXing=req.getParameter("machineZhenXing");
			String note=req.getParameter("note");
			String pictureName=req.getParameter("pictureName");
			Date createTime=new Date();
			String contentJSONString=req.getParameter("contentJSONString");
			if(StringTODate.canChangeStringTODate(deadlineTimeString)){
				ProductionNotification productionNotification=new ProductionNotification();
				productionNotification.setFworderID(fworderID);
				productionNotification.setJiagongdanwei(jiagongdanwei);
				productionNotification.setCompanyName(companyName);
				productionNotification.setFworderNumber(fworderNumber);
				productionNotification.setStyleNumber(styleNumber);
				productionNotification.setPictureName(productName);
				productionNotification.setExceptProductQuantity(exceptProductQuantity);
				productionNotification.setKezhong(kezhong);
				productionNotification.setDeadlineTime(StringTODate.changeStringTODate(deadlineTimeString));
				productionNotification.setMachineZhenXing(machineZhenXing);
				productionNotification.setNote(note);
				productionNotification.setPictureName(pictureName);
				productionNotification.setCreatTime(createTime);
				productionNotification.setContentJSONString(contentJSONString);
				ProductionNotificationDAO productionNotificationDAO=new ProductionNotificationDAO();
				productionNotificationDAO.addProductionNotification(productionNotification);
			}else {
				
				
			}
		} catch (Exception e) {
		}
	}

}
