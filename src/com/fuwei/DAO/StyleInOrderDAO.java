package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.StyleINOrder;
import com.fuwei.util.HibernateUtil;

public class StyleInOrderDAO {
	private Session session;

	public StyleInOrderDAO() {
	}

	public int addStyleINOrder(StyleINOrder styleINOrder) {
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(styleINOrder);
			transaction.commit();
			session.close();
			int id=styleINOrder.getId();
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}

	}
	
	
}
