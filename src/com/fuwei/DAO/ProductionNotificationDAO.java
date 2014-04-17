package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.ProductionNotification;
import com.fuwei.util.CreateNumberUtil;
import com.fuwei.util.HibernateUtil;

public class ProductionNotificationDAO {
	private Session session;

	public ProductionNotificationDAO() {
	}
	
	public int addProductionNotification(ProductionNotification productionNotification){
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(productionNotification);
			transaction.commit();
			session.close();
			int id=productionNotification.getId();
			updateNotification(id);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private void updateNotification(int id){
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		ProductionNotification productionNotification = (ProductionNotification) session.get(
				ProductionNotification.class, id);
		productionNotification.setNotificationNumber(CreateNumberUtil.createProductNotificationNumber(id));
		session.update(productionNotification);
		transaction.commit();
		session.close();
	}
	
	
	public ProductionNotification getProductionNotification(int id){
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		ProductionNotification productionNotification = (ProductionNotification) session.get(ProductionNotification.class, id);
		transaction.commit();
		session.close();
		return productionNotification;
	}
	
}
