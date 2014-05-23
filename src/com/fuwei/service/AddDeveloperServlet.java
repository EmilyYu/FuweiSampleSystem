package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.DeveloperDAO;
import com.fuwei.DAO.FWUserDAO;
import com.fuwei.entity.Developer;
import com.fuwei.entity.FWUser;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.HanyuPinyinUtil;
import com.fuwei.util.InitSystemDataUtil;

public class AddDeveloperServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			String name = req.getParameter("manName");
			String jianChen=HanyuPinyinUtil.getFirstSpellByString(name);
			int authority=Integer.valueOf(req.getParameter("authority"));
			
			Developer developer = new Developer();
			developer.setName(name);
			
			developer.setJianChen(jianChen);
			DeveloperDAO developerDAO = new DeveloperDAO();
			developerDAO.addDeveloper(developer);
			
			FWUserDAO userDAO=new FWUserDAO();
			String userName=null;
			
			if(userDAO.getUserCountByUserName(jianChen)>0){
				int i=1;
				userName=jianChen+i;
				while (userDAO.getUserCountByUserName(userName)>1) {
					i++;
					userName=jianChen+i;
				}
				FWUser user=new FWUser();
				user.setAuthority(authority);
				user.setPassword("12345678");
				user.setUserName(userName);
				user.setChineseName(name);
				userDAO.addUser(user);
			}else {
				FWUser user=new FWUser();
				user.setAuthority(authority);
				user.setPassword("12345678");
				user.setUserName(jianChen);
				user.setChineseName(name);
				userDAO.addUser(user);
			}
			

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

	class threadClass implements Runnable {

		public void run() {
			FuweiSystemData.setDeveloperSpellDate(InitSystemDataUtil
					.initDeveloperSpell());
			FuweiSystemData.setDeveloperList(InitSystemDataUtil.initDeveloperList());
		}

	}

}
