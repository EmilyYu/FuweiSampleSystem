package com.fuwei.DAO;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.Quotation;
import com.fuwei.util.HibernateUtil;

public class QuotationDAO {
	private Session session;

	public QuotationDAO() {
	}

	public int addQuotation(Quotation quotation) {
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(quotation);
			transaction.commit();
			session.close();
			return quotation.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Object getQuotationByCompanyPriceID(int companyPriceID) {
		session = HibernateUtil.getSession();
		String hql = "from Quotation as quotation where quotation.companyPriceID in(:companyPriceID)";
		Query query = session.createQuery(hql);
		query.setParameter("companyPriceID", companyPriceID);

		return query.list();
	}

	public Object getAllQuotation() {
		session = HibernateUtil.getSession();
		String hql = "from Quotation as quotation";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public int deleteQutationWithCompanyPriceIDSet(Set<Integer> idSet) {
		session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		String hql = "delete from Quotation as quotation where quotation.companyPriceID in(:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", idSet);
		int result=query.executeUpdate();
		ts.commit();
		return result;
	}
}
