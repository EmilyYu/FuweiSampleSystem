package com.fuwei.util;

import java.util.HashMap;
import java.util.List;

import com.fuwei.entity.CompanyName;

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
	private static List<String> developerNameList;
	private static HashMap<String, List<String>> developerSpellDate; 
	private static List<String> gongXuList;
	private static HashMap<String, List<String>> salesManSpell;
	
	private static HashMap<String, List<String>> salesNameByCompanyName;
	private static HashMap<String, List<String>> companyNameSpell;
	private static List<CompanyName> companyNameList;

	public static HashMap<String, List<String>> getCompanyNameSpell() {
		return companyNameSpell;
	}

	public static List<CompanyName> getCompanyNameList() {
		return companyNameList;
	}

	public static List<String> getDeveloperNameList() {
		return developerNameList;
	}

	public static HashMap<String, List<String>> getDeveloperSpellDate() {
		return developerSpellDate;
	}
	
	

	public static List<String> getGongXuList() {
		return gongXuList;
	}

	public static HashMap<String, List<String>> getSalesManSpell() {
		return salesManSpell;
	}

	public static HashMap<String, List<String>> getSalesNameByCompanyName() {
		return salesNameByCompanyName;
	}

	public static void setCompanyNameSpell(
			HashMap<String, List<String>> companyNameSpell) {
		FuweiSystemData.companyNameSpell = companyNameSpell;
	}

	public static void setCompanyNameList(List<CompanyName> companyNameList) {
		FuweiSystemData.companyNameList = companyNameList;
	}

	public static void setDeveloperNameList(List<String> developerNameList) {
		FuweiSystemData.developerNameList = developerNameList;
	}

	public static void setDeveloperSpellDate(
			HashMap<String, List<String>> developerSpellDate) {
		FuweiSystemData.developerSpellDate = developerSpellDate;
	}

	public static void setGongXuList(List<String> gongXuList) {
		FuweiSystemData.gongXuList = gongXuList;
	
	}

	public static void setSalesManSpell(
			HashMap<String, List<String>> salesManSpell) {
		FuweiSystemData.salesManSpell = salesManSpell;
	}

	public static void setSalesNameByCompanyName(
			HashMap<String, List<String>> salesNameByCompanyName) {
		FuweiSystemData.salesNameByCompanyName = salesNameByCompanyName;
	}
	

}
