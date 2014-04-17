package com.fuwei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fuwei.DAO.CompanyNameDAO;
import com.fuwei.DAO.DeveloperDAO;
import com.fuwei.DAO.GongXuDAO;
import com.fuwei.DAO.SalesmanDAO;
import com.fuwei.entity.CompanyName;
import com.fuwei.entity.CompanySalesMan;
import com.fuwei.entity.Developer;
import com.fuwei.entity.GongXu;

public class InitSystemDataUtil {
	
	
	public static HashMap<String, List<String>> initCompanySalesmanSpell(){
		System.out.println("初始化Salesman数据开始");
		SalesmanDAO salesmanDAO=new SalesmanDAO();
		List<CompanySalesMan> companySalesMans=(List<CompanySalesMan>)salesmanDAO.getAllSalesManName();
		HashMap<String, List<String>> hashMap=new HashMap<String, List<String>>();
		for (CompanySalesMan companySalesMan : companySalesMans) {
			String jianChen=companySalesMan.getJianChen();
			for (int i = 1; i <=jianChen.length(); i++) {
				String subString=jianChen.substring(0, i);
				if(hashMap.containsKey(subString)){
					hashMap.get(subString).add(companySalesMan.getSalesManName());
				}else {
					List<String> nameList=new ArrayList<String>();
					nameList.add(companySalesMan.getSalesManName());
					hashMap.put(subString, nameList);
				}
			}
			
		}
		
		
		Set<String> keySet=hashMap.keySet();
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<String> stringList=hashMap.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i)+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化Salesman数据结束");
		return hashMap;
	}
	
	
	public  static HashMap<String, List<String>> initSalesNameByCompanyName(){
		System.out.println("初始化salesMan数据开始");
		SalesmanDAO salesmanDAO=new SalesmanDAO();
		List<CompanySalesMan> salesMan=(List<CompanySalesMan>)salesmanDAO.getAllSalesManName();
		HashMap<String,List<String>> hashSet=new HashMap<String,List<String>>();
		for (CompanySalesMan companySalesMan : salesMan) {
			if(hashSet.containsKey(companySalesMan.getCompanyName())){
				hashSet.get(companySalesMan.getCompanyName()).add(companySalesMan.getSalesManName());
			}else {
				List<String> listString=new ArrayList<String>();
				listString.add(companySalesMan.getSalesManName());
				hashSet.put(companySalesMan.getCompanyName(), listString);
			}
		}
		
		Set<String> keySet=hashSet.keySet();
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<String> stringList=hashSet.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i)+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化salesMan数据结束");
		return hashSet;
	}
	
	public static HashMap<String, List<String>> initDeveloperSpell(){
		System.out.println("初始化Developer数据开始");
		DeveloperDAO developerDAO=new DeveloperDAO();
		List<Developer> developers=(List<Developer>)developerDAO.getAllDeveloper();
		System.out.println("developers size:"+developers.size());
		HashMap<String, List<String>> hashMap=new HashMap<String, List<String>>();
		for (Developer developer : developers) {
			String jianChen=developer.getJianChen();
			for (int i = 1; i <=jianChen.length(); i++) {
				String subuString=jianChen.substring(0,i);
				if(hashMap.containsKey(subuString)){
					hashMap.get(subuString).add(developer.getDeveloperName());
				}else {
					List<String> nameList=new ArrayList<String>();
					nameList.add(developer.getDeveloperName());
					hashMap.put(subuString, nameList);
				}
			}
		}
		
		Set<String> keySet=hashMap.keySet();
		System.out.println("keySet size:"+keySet.size());
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<String> stringList=hashMap.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i)+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化Developer数据结束");
		return hashMap;
	}
	
	public static HashMap<String, List<String>> initCompanyNameSpell() {
		System.out.println("初始化CompanyName数据开始");
		CompanyNameDAO companyNameDAO=new CompanyNameDAO();
		List<CompanyName> companyNames=(List<CompanyName>)companyNameDAO.getAllCompanyName();
		HashMap<String, List<String>> hashMap=new HashMap<String, List<String>>();
		for (CompanyName companyName : companyNames) {
			String jianchen=companyName.getJianChen();
			for (int i = 1; i <= jianchen.length(); i++) {
				String subString=jianchen.substring(0, i);
				if(hashMap.containsKey(subString)){
					hashMap.get(subString).add(companyName.getCompanyName());
				}else {
					List<String> nameList=new ArrayList<String>();
					nameList.add(companyName.getCompanyName());
					hashMap.put(subString, nameList);
				}
			}
		}
		
		Set<String> keySet=hashMap.keySet();
		for (String string : keySet) {
			System.out.println("==================================");
			System.out.println("key:"+string);
			List<String> stringList=hashMap.get(string);
			for (int i = 0; i < stringList.size(); i++) {
				System.out.print(stringList.get(i)+"  ");
			}
			System.out.println("\n");
		}
		System.out.println("初始化CompanyName数据结束");
		return hashMap;
	}
	
	

	
	public static List<CompanyName> initCompanyName(){
		CompanyNameDAO companyNameDAO=new CompanyNameDAO();
		List<CompanyName> comList=(List<CompanyName>)companyNameDAO.getAllCompanyName();
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
	
	
	public static List<String> initDeveloperName(){
		DeveloperDAO developerDAO=new DeveloperDAO();
		List<Developer> developers=(List<Developer>)developerDAO.getAllDeveloper();
		List<String> names=new ArrayList<String>();
		for (Developer developer : developers) {
			names.add(developer.getDeveloperName());
		}
		return names;
	}
}
