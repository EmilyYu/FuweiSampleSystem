package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.FWUserDAO;
import com.fuwei.entity.FWUser;

import net.sf.json.JSONObject;

public class UpdatePasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			String password=req.getParameter("password");
			String new_password=req.getParameter("new_password");
			String new_password_two=req.getParameter("new_password_two");
			FWUser user=(FWUser)req.getSession().getAttribute("user");
			if(user.getPassword().equals(password)){
				if(new_password.equals(new_password_two)&&new_password.length()>7){
					user.setPassword(new_password);
					FWUserDAO userDAO=new FWUserDAO();
					userDAO.updateUser(user);
					JSONObject jObject = new JSONObject();
					jObject.put("OK", true);
					PrintWriter printWriter = resp.getWriter();
					printWriter.write(jObject.toString());
					printWriter.flush();
					printWriter.close();
				}else {
					JSONObject jObject = new JSONObject();
					jObject.put("OK", false);
					jObject.put("message", "新密码有误");
					PrintWriter printWriter = resp.getWriter();
					printWriter.write(jObject.toString());
					printWriter.flush();
					printWriter.close();
				}
			}else {
				JSONObject jObject = new JSONObject();
				jObject.put("OK", false);
				jObject.put("message", "原始密码有误");
				PrintWriter printWriter = resp.getWriter();
				printWriter.write(jObject.toString());
				printWriter.flush();
				printWriter.close();
			}
		
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
