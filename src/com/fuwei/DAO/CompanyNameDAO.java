package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.CompanyName;
import com.fuwei.util.HibernateUtil;

public class CompanyNameDAO {
	private Session session;
	public CompanyNameDAO() {}
	
	public int addCompanyName(CompanyName companyName){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction=session.beginTransaction();
			session.save(companyName);
			transaction.commit();
			session.close();
			return companyName.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public CompanyName getCompanyNameById(int id){
		session=HibernateUtil.getSession();
		Transaction transaction=session.beginTransaction();
		CompanyName companyName=(CompanyName)session.get(CompanyName.class, id);
		transaction.commit();
		session.close();
		return companyName;
	}
	
	public Object getAllCompanyName(){
		session=HibernateUtil.getSession();
		String hql="from CompanyName as company";
		Query query=session.createQuery(hql);
		return query.list();
	}
	
	public Object getCompanyByCompanyName(String companyName){
		session=HibernateUtil.getSession();
		String hql="from CompanyName as company where company.companyName =:companyName";
		Query query=session.createQuery(hql);
		query.setString("companyName", companyName);
		return query.list();
	}
	
	
	
}
