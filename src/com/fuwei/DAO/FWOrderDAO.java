package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.FWOrder;
import com.fuwei.util.CreateNumberUtil;
import com.fuwei.util.HibernateUtil;

public class FWOrderDAO {
	private Session session;
	public FWOrderDAO(){}
	
	public int addFWOrder(FWOrder fwOrder){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(fwOrder);
			transaction.commit();
			session.close();
			int id=fwOrder.getId();
			updateFWOrderTOAddFWOrderNumber(id);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private void updateFWOrderTOAddFWOrderNumber(int id){
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		FWOrder fwOrder=(FWOrder)session.get(FWOrder.class, id);
		fwOrder.setFwOrderNumber(CreateNumberUtil.createFWStyleNumber(id));
		session.update(fwOrder);
		transaction.commit();
		session.close();
	}
	
}
