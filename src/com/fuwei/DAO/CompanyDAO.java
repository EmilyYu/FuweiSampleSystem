package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.Company;
import com.fuwei.util.HibernateUtil;

public class CompanyDAO {
	private Session session;
	public CompanyDAO() {}
	
	public int addCompany(Company company){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction=session.beginTransaction();
			session.save(company);
			transaction.commit();
			session.close();
			return company.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Company getCompanyById(int id){
		session=HibernateUtil.getSession();
		Transaction transaction=session.beginTransaction();
		Company companyName=(Company)session.get(Company.class, id);
		transaction.commit();
		session.close();
		return companyName;
	}
	
	public Object getAllCompany(){
		session=HibernateUtil.getSession();
		String hql="from Company as company";
		Query query=session.createQuery(hql);
		return query.list();
	}
	
	public Object getCompanyByName(String companyName){
		session=HibernateUtil.getSession();
		String hql="from Company as company where company.companyName =:companyName";
		Query query=session.createQuery(hql);
		query.setString("companyName", companyName);
		return query.list();
	}
	
	
	
}
