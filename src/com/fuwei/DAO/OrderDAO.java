package com.fuwei.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.FWOrder;
import com.fuwei.util.HibernateUtil;

public class OrderDAO {
	private Session session;

	public OrderDAO() {
	}
	
	public int addOrder(FWOrder order){
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(order);
			transaction.commit();
			session.close();
			return order.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public Object getAllOrder(){
		session = HibernateUtil.getSession();
		String hql = "from Order as o";
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public Object getOrderByID(int id){
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		FWOrder o = (FWOrder) session.get(FWOrder.class, id);
		transaction.commit();
		session.close();
		return o;
	}
	
}
