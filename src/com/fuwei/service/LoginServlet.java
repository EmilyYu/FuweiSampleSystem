package com.fuwei.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fuwei.DAO.FWUserDAO;
import com.fuwei.entity.FWUser;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		FWUserDAO userDAO=new FWUserDAO();
		List<FWUser> userList=(List<FWUser>)userDAO.getUserByUserName(userName);
		if(userList!=null&&userList.size()>0){
			FWUser user=userList.get(0);
			if(password.equals(user.getPassword())){
				HttpSession session =req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("index.jsp");
			}else {
				req.setAttribute("message", "密码错误");
				System.out.println("密码错误");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}else {
			req.setAttribute("message", "账户不存在");
			System.out.println("账户不存在");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
		
	}

}
