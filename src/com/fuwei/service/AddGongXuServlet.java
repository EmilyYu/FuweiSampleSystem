package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.GongXuDAO;
import com.fuwei.entity.GongXu;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.InitSystemDataUtil;

public class AddGongXuServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			String gongXuNameg=req.getParameter("gongxuName");
			GongXu gongXu=new GongXu();
			gongXu.setName(gongXuNameg);
			GongXuDAO gongXuDAO=new GongXuDAO();
			gongXuDAO.addGongXu(gongXu);
			System.out.println("主线程："+Thread.currentThread().getName());
			new Thread(new threadClass()).start();
			JSONObject jObject = new JSONObject();
			jObject.put("OK", true);
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}
	}
	
	class threadClass implements Runnable{

		public void run() {
			System.out.println("初始化工序线程："+Thread.currentThread().getName());
			FuweiSystemData.setGongXuList(InitSystemDataUtil.initGongXu());
		}
		
	}
	
	

}
