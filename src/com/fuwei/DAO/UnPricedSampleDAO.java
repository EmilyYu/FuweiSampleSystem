package com.fuwei.DAO;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.UnPricedSample;
import com.fuwei.util.CreateNumberUtil;
import com.fuwei.util.HibernateUtil;

public class UnPricedSampleDAO {
	private Session session;

	public UnPricedSampleDAO() {
	}

	public int addUnPricedSample(UnPricedSample unPricedSample) {
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(unPricedSample);
			transaction.commit();
			session.close();
			int id=unPricedSample.getId();
			updateUnPricedSampleStyleNumberByID(id);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}

	}
	
	
	private void updateUnPricedSampleStyleNumberByID(int id) {
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		UnPricedSample unPricedSample = (UnPricedSample) session.get(
				UnPricedSample.class, id);
		unPricedSample.setProductNumber(CreateNumberUtil.createSampleStyleNumber(id));
		session.update(unPricedSample);
		transaction.commit();
		session.close();
	}

	public UnPricedSample getUnPricedSampleBYId(int id) {
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		UnPricedSample unPricedSample = (UnPricedSample) session.get(UnPricedSample.class, id);
		transaction.commit();
		session.close();
		return unPricedSample;
	}
	
	public Object getAllUnPricedSample(){
		session = HibernateUtil.getSession();
		String hql = "from UnPricedSample as sample order by sample.id desc";
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public int deleteUnPricedSampleById(int id){
		session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		String hql = "delete from UnPricedSample as sample where sample.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int result=query.executeUpdate();
		ts.commit();
		return result;
	}
	
	public Object getUnPricedSampleByDeveloper(String developer){
		session = HibernateUtil.getSession();
		String hql = "from UnPricedSample as sample where sample.developer=:developer order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setString("developer", developer);
		return query.list();
	}
	
	public Object getUnPricedSampleByIDSET(Set<Integer> idSet){
		session = HibernateUtil.getSession();
		String hql = "from UnPricedSample as sample where sample.id in (:idSet)";
		Query query = session.createQuery(hql);
		query.setParameterList("idSet", idSet);
		return query.list();
	}
}
