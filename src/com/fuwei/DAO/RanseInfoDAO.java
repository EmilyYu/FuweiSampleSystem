package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.RanseInfo;
import com.fuwei.util.HibernateUtil;

public class RanseInfoDAO {
	private Session session;

	public RanseInfoDAO() {
	}
	
	public int addRanseInfo(RanseInfo ranseInfo){
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(ranseInfo);
			transaction.commit();
			session.close();
			return ranseInfo.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
