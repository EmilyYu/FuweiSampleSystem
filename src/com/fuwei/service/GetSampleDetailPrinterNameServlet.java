package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.util.PrinterNameUtil;

public class GetSampleDetailPrinterNameServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			List<String> printerName=new ArrayList<String>();
			String canon=PrinterNameUtil.getPrinterName("Canon");
			String hp=PrinterNameUtil.getPrinterName("M1212");
			if(canon!=null){
				printerName.add(canon);
			}
			if(hp!=null){
				printerName.add(hp);
			}
			PrintWriter printWriter = resp.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("printerName", printerName);
			printWriter.print(jsonObject.toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
	

}
