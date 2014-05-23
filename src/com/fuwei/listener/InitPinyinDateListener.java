package com.fuwei.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fuwei.DAO.FWUserDAO;
import com.fuwei.entity.FWUser;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.InitSystemDataUtil;

public class InitPinyinDateListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("初始化容器");
		initCompanyNameSpell();
		initDeveloperSpell();
		initCompanySalesmanSpell();
		initGongXu();
		initSalesManByCompany();
		initCompanyName();
		initDeveloperName();
		initUser();
		initSalesmanList();
	}
	
	private void initSalesmanList() {
		FuweiSystemData.setSalesmanList(InitSystemDataUtil.initSalesmanList());
	}
	
	private void initDeveloperName() {
		FuweiSystemData.setDeveloperList(InitSystemDataUtil.initDeveloperList());
	}

	private void initCompanyName() {
		FuweiSystemData.setCompanyList(InitSystemDataUtil.initCompanyList());
	}

	private void initCompanyNameSpell() {
		FuweiSystemData.setCompanyNameSpell(InitSystemDataUtil
				.initCompanyNameSpell());
	}

	private void initDeveloperSpell() {
		FuweiSystemData.setDeveloperSpellDate(InitSystemDataUtil
				.initDeveloperSpell());
	}

	private void initCompanySalesmanSpell() {
		FuweiSystemData.setSalesManSpell(InitSystemDataUtil
				.initCompanySalesmanSpell());
	}
	
	private void initSalesManByCompany() {
		FuweiSystemData.setSalesNameByCompany(InitSystemDataUtil.initSalesmanByCompany());
	}

	private void initGongXu() {
		
		FuweiSystemData.setGongXuList(InitSystemDataUtil.initGongXu());
	}
	
	private void initUser(){
		FWUserDAO userDAO=new FWUserDAO();
		List<FWUser> userList=(List<FWUser>)userDAO.getAllUser();
		if(userList==null){
			FWUser user=new FWUser();
			user.setAuthority(1);
			user.setChineseName("胡盼");
			user.setPassword("12345678");
			user.setUserName("hp");
			userDAO.addUser(user);
		}else if(userList.size()<1){
			FWUser user=new FWUser();
			user.setAuthority(1);
			user.setChineseName("胡盼");
			user.setPassword("12345678");
			user.setUserName("hp");
			userDAO.addUser(user);
		}
	}

	

}
