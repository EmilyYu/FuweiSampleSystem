package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.Developer;
import com.fuwei.util.HibernateUtil;

public class DeveloperDAO {
	private Session session;
	public DeveloperDAO(){}
	
	public int addDeveloper(Developer developer){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(developer);
			transaction.commit();
			session.close();
			return developer.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public Object getAllDeveloper(){
		session=HibernateUtil.getSession();
		String hql="from Developer as developer";
		Query query=session.createQuery(hql);
		return query.list();
	}
}
