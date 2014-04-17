package com.fuwei.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.RanseInfoDAO;
import com.fuwei.entity.RanseInfo;
import com.fuwei.util.StringTODate;

public class AddRanseInfoServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		try {
			int styleINOrderID=Integer.valueOf(req.getParameter("styleINOrderID"));
			String ranchang=req.getParameter("ranchang");
			String productNumber=req.getParameter("productNumber");
			String deliveryTimeString=req.getParameter("deliveryTime");
			String colorInfoJSONString=req.getParameter("colorInfoJSONString");
			String productName=req.getParameter("productName");
			if(StringTODate.canChangeStringTODate(deliveryTimeString)){
				RanseInfo ranseInfo=new RanseInfo();
				ranseInfo.setColorInfoJSONString(colorInfoJSONString);
				ranseInfo.setDeliveryTime(StringTODate.changeStringTODate(deliveryTimeString));
				ranseInfo.setProductName(productName);
				ranseInfo.setProductNumber(productNumber);
				ranseInfo.setRanchang(ranchang);
				ranseInfo.setCreateTime(new Date());
				ranseInfo.setStyleINOrderID(styleINOrderID);
				RanseInfoDAO ranseInfoDAO=new RanseInfoDAO();
				ranseInfoDAO.addRanseInfo(ranseInfo);
			}else {
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
