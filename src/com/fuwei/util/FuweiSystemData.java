package com.fuwei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fuwei.entity.Company;
import com.fuwei.entity.CompanySalesMan;
import com.fuwei.entity.Developer;

public class FuweiSystemData {
	public static final int ADD_QUOTATION = 2;
	public static final int ALL_QUOTATION = 3;
	// ===================
	public static final int BASE_COMPANY = 2;
	public static final int BASE_COMPANY_SALESMAN = 4;

	public static final int BASE_DEVELOPER = 3;
	public static final int BASE_PRODUCT_NUMBER = 1;

	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_PERPAGE = 10;
	public static final int DEFAULT_SEARCH_TYPE = 0;
	
	//权限
	public static final int AUTHORITY_MANAGER=1;
	//普通权限
	
	public static final int AUTHORITY_GENERAL=3;
	// 报价单参数 必须大于0
	public static final int DELETE_QUOTATION = 1;
	
	private static List<Developer> developerList = new ArrayList<Developer>();//系统用户（跟单人列表）
	private static HashMap<String, List<Developer>> developerSpellDate = new HashMap<String, List<Developer>>(); 
	private static List<String> gongXuList = new ArrayList<String>();
	private static HashMap<String, List<CompanySalesMan>> salesManSpell = new HashMap<String, List<CompanySalesMan>>();
	
	private static HashMap<String, List<CompanySalesMan>> salesNameByCompany = new HashMap<String, List<CompanySalesMan>>();
	private static HashMap<String, List<Company>> companyNameSpell = new HashMap<String, List<Company>>();
	private static List<Company> companyList = new ArrayList<Company>();
	private static List<CompanySalesMan> salesmanList = new ArrayList<CompanySalesMan>();
	
	
	public static List<Company> getCompanyList() {
		return companyList;
	}

	public static void setCompanyList(List<Company> companyList) {
		FuweiSystemData.companyList = companyList;
	}

	public static List<CompanySalesMan> getSalesmanList() {
		return salesmanList;
	}

	public static void setSalesmanList(List<CompanySalesMan> salesmanList) {
		FuweiSystemData.salesmanList = salesmanList;
	}
	
	
	public static String getDeveloperNameById(int id){
		for(Developer developer:developerList){
			if(developer.getId() == id){
				return developer.getName();
			}
		}		
		return null;
	}
	
	public static String getCompanyNameById(int id){
		for(Company company:companyList){
			if(company.getId() == id){
				return company.getName();
			}
		}		
		return null;
	}
	
	public static String getSalesManNameById(int id){
		for(CompanySalesMan companySalesMan:salesmanList){
			if(companySalesMan.getId() == id){
				return companySalesMan.getName();
			}
		}		
		return null;
	}
	

	public static Developer getDeveloperById(int id){
		for(Developer developer:developerList){
			if(developer.getId() == id){
				return developer;
			}
		}		
		return null;
	}
	
	public static Company getCompanyById(int id){
		for(Company company:companyList){
			if(company.getId() == id){
				return company;
			}
		}		
		return null;
	}
	
	public static CompanySalesMan getCompanySalesManById(int id){
		for(CompanySalesMan companySalesMan:salesmanList){
			if(companySalesMan.getId() == id){
				return companySalesMan;
			}
		}		
		return null;
	}
	
	public static HashMap<String, List<CompanySalesMan>> getSalesManSpell() {
		return salesManSpell;
	}

	public static HashMap<String, List<Developer>> getDeveloperSpellDate() {
		return developerSpellDate;
	}

	public static void setDeveloperSpellDate(
			HashMap<String, List<Developer>> developerSpellDate) {
		FuweiSystemData.developerSpellDate = developerSpellDate;
	}

	public static void setSalesManSpell(
			HashMap<String, List<CompanySalesMan>> salesManSpell) {
		FuweiSystemData.salesManSpell = salesManSpell;
	}

	public static HashMap<String, List<CompanySalesMan>> getSalesNameByCompany() {
		return salesNameByCompany;
	}

	public static void setSalesNameByCompany(
			HashMap<String, List<CompanySalesMan>> salesNameByCompany) {
		FuweiSystemData.salesNameByCompany = salesNameByCompany;
	}

	public static HashMap<String, List<Company>> getCompanyNameSpell() {
		return companyNameSpell;
	}

	public static void setCompanyNameSpell(
			HashMap<String, List<Company>> companyNameSpell) {
		FuweiSystemData.companyNameSpell = companyNameSpell;
	}

	public static List<Developer> getDeveloperList() {
		return developerList;
	}

	public static void setDeveloperList(List<Developer> developerList) {
		FuweiSystemData.developerList = developerList;
	}

	public static List<String> getGongXuList() {
		return gongXuList;
	}


	public static void setGongXuList(List<String> gongXuList) {
		FuweiSystemData.gongXuList = gongXuList;
	
	}



}
