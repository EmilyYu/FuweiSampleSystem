package com.fuwei.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.UnPricedSampleDAO;
import com.fuwei.entity.UnPricedSample;

public class SearchUnPricedSampleServlet extends HttpServlet {
	 
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		Enumeration paraterNames=req.getParameterNames();
		String developer=null;
		if (paraterNames.hasMoreElements()) {
			String parameterName = (String) paraterNames.nextElement();
			System.out.println("parater:"+parameterName);
			if (parameterName.equals("developer")) {
				developer = req.getParameter(parameterName);
			}
		}
		List<UnPricedSample> unPricedSampleList=null;
		UnPricedSampleDAO unPricedSampleDAO=new UnPricedSampleDAO();
		if(developer!=null){
			unPricedSampleList=(List<UnPricedSample>)unPricedSampleDAO.getUnPricedSampleByDeveloper(developer);
		}else {
			developer="";
			unPricedSampleList=(List<UnPricedSample>)unPricedSampleDAO.getAllUnPricedSample();
		}
		
		if(unPricedSampleList==null){
			unPricedSampleList=new ArrayList<UnPricedSample>();
		}
		
		redirect(req,resp,unPricedSampleList,developer);
	}
	
	
	private void redirect(HttpServletRequest req, HttpServletResponse resp,List<UnPricedSample> unPricedSamples,String developer){
		try {
			req.setAttribute("unPricedSampleList", unPricedSamples);
			req.setAttribute("developer", developer);
			req.getRequestDispatcher("unPricedSample.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
