package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.util.FuweiSystemData;

import net.sf.json.JSONObject;

public class PinyinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		System.out.println("收到请求");
		int searchType = Integer.valueOf(req.getParameter("searchtype"));
		String searchQuery = req.getParameter("searchQuery");
		System.out.println("searchType:" + searchType);
		System.out.println("searchQuery:" + searchQuery);
		;
		switch (searchType) {
		case FuweiSystemData.BASE_PRODUCT_NUMBER:

			break;
		case FuweiSystemData.BASE_COMPANY: {
			if (FuweiSystemData.getCompanyNameSpell().containsKey(searchQuery)) {
				List<String> values = FuweiSystemData.getCompanyNameSpell().get(
						searchQuery);
				PrintWriter printWriter = resp.getWriter();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("value", values);
				System.out.println("result:" + jsonObject.toString());
				printWriter.print(jsonObject.toString());
				printWriter.flush();
				printWriter.close();
			}
		}

			break;
		case FuweiSystemData.BASE_DEVELOPER:{
			if(FuweiSystemData.getDeveloperSpellDate().containsKey(searchQuery)){
				List<String> values=FuweiSystemData.getDeveloperSpellDate().get(searchQuery);
				PrintWriter printWriter = resp.getWriter();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("value", values);
				System.out.println("result:" + jsonObject.toString());
				printWriter.print(jsonObject.toString());
				printWriter.flush();
				printWriter.close();
			}
			
		}

			break;
		case FuweiSystemData.BASE_COMPANY_SALESMAN:{
			if(FuweiSystemData.getSalesManSpell().containsKey(searchQuery)){
				List<String> values=FuweiSystemData.getSalesManSpell().get(searchQuery);
				PrintWriter printWriter = resp.getWriter();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("value", values);
				System.out.println("result:" + jsonObject.toString());
				printWriter.print(jsonObject.toString());
				printWriter.flush();
				printWriter.close();
			}
			
		}

			break;

		default:
			break;
		}

	}

}
