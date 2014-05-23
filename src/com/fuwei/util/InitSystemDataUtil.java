package com.fuwei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fuwei.DAO.CompanyDAO;
import com.fuwei.DAO.DeveloperDAO;
import com.fuwei.DAO.GongXuDAO;
import com.fuwei.DAO.SalesmanDAO;
import com.fuwei.entity.Company;
import com.fuwei.entity.CompanySalesMan;
import com.fuwei.entity.Developer;
import com.fuwei.entity.GongXu;

public class InitSystemDataUtil {
	
	public static List<CompanySalesMan> initSalesmanList(){
		SalesmanDAO salesmanDAO=new SalesmanDAO();
		List<CompanySalesMan> saleList=(List<CompanySalesMan>)salesmanDAO.getAllSalesMan();
		return saleList;
	}
	public static HashMap<String, List<CompanySalesMan>> initCompanySalesmanSpell(){
		System.out.println("初始化Salesman数据开始");
		SalesmanDAO salesmanDAO=new SalesmanDAO();
		List<CompanySalesMan> companySalesMans=(List<CompanySalesMan>)salesmanDAO.getAllSalesMan();
		HashMap<String, List<CompanySalesMan>> hashMap=new HashMap<String, List<CompanySalesMan>>();
		for (CompanySalesMan companySalesMan : companySalesMans) {
			String jianChen=companySalesMan.getJianChen();
			for (int i = 1; i <=jianChen.length(); i++) {
				String subString=jianChen.substring(0, i);
				if(hashMap.containsKey(subString)){
					hashMap.get(subString).add(companySalesMan);
				}else {
					List<CompanySalesMan> nameList=new ArrayList<CompanySalesMan>();
					nameList.add(companySalesMan);
					hashMap.put(subString, nameList);
				}
			}
			
		}
		
		
		Set<String> keySet=hashMap.keySet();
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<CompanySalesMan> stringList=hashMap.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i).toString()+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化Salesman数据结束");
		return hashMap;
	}
	
	
	public  static HashMap<String, List<CompanySalesMan>> initSalesmanByCompany(){
		System.out.println("初始化salesMan数据开始");
		SalesmanDAO salesmanDAO=new SalesmanDAO();
		List<CompanySalesMan> salesMan=(List<CompanySalesMan>)salesmanDAO.getAllSalesMan();
		HashMap<String,List<CompanySalesMan>> hashSet=new HashMap<String,List<CompanySalesMan>>();
		for (CompanySalesMan companySalesMan : salesMan) {
			String companyId = String.valueOf(companySalesMan.getCompanyId());
			if(hashSet.containsKey(companyId)){
				hashSet.get(companyId).add(companySalesMan);
			}else {
				List<CompanySalesMan> listString=new ArrayList<CompanySalesMan>();
				listString.add(companySalesMan);
				hashSet.put(companyId, listString);
			}
		}
		
		Set<String> keySet=hashSet.keySet();
		for (String key : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+key);
			List<CompanySalesMan> stringList=hashSet.get(key);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i).toString()+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化salesMan数据结束");
		return hashSet;
	}
	
	public static HashMap<String, List<Developer>> initDeveloperSpell(){
		System.out.println("初始化Developer数据开始");
		DeveloperDAO developerDAO=new DeveloperDAO();
		List<Developer> developers=(List<Developer>)developerDAO.getAllDeveloper();
		System.out.println("developers size:"+developers.size());
		HashMap<String, List<Developer>> hashMap=new HashMap<String, List<Developer>>();
		for (Developer developer : developers) {
			String jianChen=developer.getJianChen();
			for (int i = 1; i <=jianChen.length(); i++) {
				String subuString=jianChen.substring(0,i);
				if(hashMap.containsKey(subuString)){
					hashMap.get(subuString).add(developer);
				}else {
					List<Developer> developerList=new ArrayList<Developer>();
					developerList.add(developer);
					hashMap.put(subuString, developerList);
				}
			}
		}
		
		Set<String> keySet=hashMap.keySet();
		System.out.println("keySet size:"+keySet.size());
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<Developer> stringList=hashMap.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i).toString()+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化Developer数据结束");
		return hashMap;
	}
	
	public static HashMap<String, List<Company>> initCompanyNameSpell() {
		System.out.println("初始化CompanyName数据开始");
		CompanyDAO companyNameDAO=new CompanyDAO();
		List<Company> companylist=(List<Company>)companyNameDAO.getAllCompany();
		HashMap<String, List<Company>> hashMap=new HashMap<String, List<Company>>();
		for (Company company : companylist) {
			String jianchen=company.getJianChen();
			for (int i = 1; i <= jianchen.length(); i++) {
				String subString=jianchen.substring(0, i);
				if(hashMap.containsKey(subString)){
					hashMap.get(subString).add(company);
				}else {
					List<Company> company_list=new ArrayList<Company>();
					company_list.add(company);
					hashMap.put(subString, company_list);
				}
			}
		}
		
		Set<String> keySet=hashMap.keySet();
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<Company> stringList=hashMap.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i).toString()+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化CompanyName数据结束");
		return hashMap;
	}
	
	

	
	public static List<Company> initCompanyName(){
		CompanyDAO companyNameDAO=new CompanyDAO();
		List<Company> comList=(List<Company>)companyNameDAO.getAllCompany();
		return comList;
	}
	
	public static List<Company> initCompanyList(){
		CompanyDAO companyNameDAO=new CompanyDAO();
		List<Company> comList=(List<Company>)companyNameDAO.getAllCompany();
		return comList;
	}
	
	
	public static List<String> initGongXu(){
		List<String> gongXuString = new ArrayList<String>();
		GongXuDAO gongXuDAO = new GongXuDAO();
		List<GongXu> gongXus = (List<GongXu>) gongXuDAO.getAllGongXu();
		for (GongXu gongXu : gongXus) {
			gongXuString.add(gongXu.getName());
		}
		return gongXuString;
	}
	
	
//	public static List<String> initDeveloperName(){
//		DeveloperDAO developerDAO=new DeveloperDAO();
//		List<Developer> developers=(List<Developer>)developerDAO.getAllDeveloper();
//		List<String> names=new ArrayList<String>();
//		for (Developer developer : developers) {
//			names.add(developer.getName());
//		}
//		return names;
//	}
	
	public static List<Developer> initDeveloperList(){
		DeveloperDAO developerDAO=new DeveloperDAO();
		List<Developer> developers=(List<Developer>)developerDAO.getAllDeveloper();
		return developers;
	}
}
