package com.fuwei.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.CompanySalesMan;
import com.fuwei.util.HibernateUtil;

public class SalesmanDAO {
	private Session session;
	public SalesmanDAO(){}
	
	public int addSample(CompanySalesMan companySalesMan){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(companySalesMan);
			transaction.commit();
			session.close();
			return companySalesMan.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Object getAllSalesManName(){
		session=HibernateUtil.getSession();
		String hql="from CompanySalesMan as companysalesman";
		Query query=session.createQuery(hql);
		return query.list();
	}
	
	public String getSalesManNamePhoneNumber(String name){
		session=HibernateUtil.getSession();
		String hql="from CompanySalesMan as companysalesman where companysalesman.salesManName=:name";
		Query query=session.createQuery(hql);
		query.setString("name", name);
		List<CompanySalesMan> salesList=(List<CompanySalesMan>)query.list();
		if(salesList.size()>0){
			return salesList.get(0).getPhone();
		}else {
			return null;
		}
	}
}
