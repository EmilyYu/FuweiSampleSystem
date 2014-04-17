package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.FWUser;
import com.fuwei.entity.StyleINOrder;
import com.fuwei.util.CreateNumberUtil;
import com.fuwei.util.DateFormateUtil;
import com.fuwei.util.HibernateUtil;

public class FWUserDAO {
	private Session session;
	public FWUserDAO(){}
	
	public int addUser(FWUser user){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			session.close();
			return user.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Object getUserByUserName(String userName){
		
		session=HibernateUtil.getSession();
		String hql="from FWUser as user where user.userName=:userName";
		Query query=session.createQuery(hql);
		query.setString("userName", userName);
		return query.list();
	}
	
	public void updateUser(FWUser user){
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
	}
	
	public Object getAllUser(){
		session=HibernateUtil.getSession();
		String hql="from FWUser as user";
		Query query=session.createQuery(hql);
		return query.list();
	}
	
	public int getUserCountByUserName(String userName){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from FWUser as fwuser where fwuser.userName=:userName";
		Query query = session.createQuery(hql);
		query.setString("userName", userName);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
}
