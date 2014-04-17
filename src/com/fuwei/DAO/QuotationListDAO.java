package com.fuwei.DAO;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.QuotationList;
import com.fuwei.util.CreateNumberUtil;
import com.fuwei.util.DateFormateUtil;
import com.fuwei.util.HibernateUtil;

public class QuotationListDAO {
	private Session session;

	public QuotationListDAO() {
	}

	public int addQuotation(QuotationList quotationList) {
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(quotationList);
			transaction.commit();
			session.close();
			int id = quotationList.getId();
			updateQuotationNumberByID(id);
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Object getQuotationListById(int id) {

		session = HibernateUtil.getSession();
		return session.get(QuotationList.class, id);

	}

	private void updateQuotationNumberByID(int id) {
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		QuotationList quotationList = (QuotationList) session.get(
				QuotationList.class, id);
		quotationList.setQuotationNumber(CreateNumberUtil
				.createQuotationListNumber(id));
		session.update(quotationList);
		transaction.commit();
		session.close();
	}
	
	public Object getAllQuotationList(int first,int max){
		
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
		
	}
	
	public int getAllQuotationListCount(){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation";
		Query query = session.createQuery(hql);
		int count=Integer.valueOf(query.iterate().next().toString());
		System.out.println(count);
		return count;
	}
	
	public Object getAllQuotationListBeforeTime(Date aTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where 1=1 and quotation.time <:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	public Object getAllQuotationListAfterTime(Date bTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where 1=1 and quotation.time >:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	public int getAllQuotationListBeforeTimeCount(Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where 1=1 and quotation.time <:date";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public int getAllQuotationListAfterTimeCount(Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where 1=1 and quotation.time >:date";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public int getAllQuotationListBetweenTwoTimeCount(Date aTime,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where 1=1 and quotation.time <:date1 and quotation.time >:date2 ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getAllQuotationListBetweenTwoTime(Date aTime,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where 1=1 and quotation.time <:date1 and quotation.time >:date2 order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	//按公司搜索
	
	public int getQuotationListCountByCompanyWithoutTime(String companyName){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.companyName=:companyName";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getQuotationListByCompanyWithoutTime(String companyName,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.companyName=:companyName order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	
	public Object getQuotationListByCompanyBetweenTwoTime(String companyName,Date aTime,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.companyName=:companyName and quotation.time <:date1 and quotation.time >:date2 order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	public int getQuotationListCountByCompanyBetweenTwoTime(String companyName,Date aTime,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.companyName=:companyName and quotation.time <:date1 and quotation.time >:date2 ";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public int getQuotationListByCompanyBeforeTimeCount(String companyName,Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.companyName=:companyName and quotation.time <:date";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getQuotationListByCompanyBeforeTime(String companyName,Date aTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.companyName=:companyName and quotation.time <:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	public int getQuotationListByCompanyAfterTimeCount(String companyName,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.companyName=:companyName and quotation.time >:date";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getQuotationListByCompanyAfterTime(String companyName,Date bTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.companyName=:companyName and quotation.time >:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("companyName", companyName);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	//按报价单号
	public int getQuotationListCountByQuotationListNumberAfterTime(String quotationListNumber,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber and quotation.time >:date";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getQuotationListByQuotationListNumberAfterTime(String quotationListNumber,Date bTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber and quotation.time >:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	public int getQuotationListCountByQuotationListNumberBeforeTime(String quotationListNumber,Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber and quotation.time <:date";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getQuotationListByQuotationListNumberBeforeTime(String quotationListNumber,Date aTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber and quotation.time <:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	public int getQuotationListCountByQuotationListNumberBetweenTwoTime(String quotationListNumber,Date aTime,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber and quotation.time <:date1 and quotation.time >:date2 ";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getQuotationListByQuotationListNumberBetweenTwoTime(String quotationListNumber,Date aTime,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber and quotation.time <:date1 and quotation.time >:date2 order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	public int getQuotationListCountByQuotationListNumberWithoutTime(String quotationListNumber){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getQuotationListByQuotationListNumberWithoutTime(String quotationListNumber,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.quotationNumber=:quotationListNumber order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("quotationListNumber", quotationListNumber);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	//按公司业务员搜索
	public int getQuotationListCountBySalesmanAfterTime(String salesMan,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.salesName=:salesMan and quotation.time >:date";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getQuotationListBySalesmanAfterTime(String salesMan,Date bTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.salesName=:salesMan and quotation.time >:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	public int getQuotationListCountBySalesmanBeforeTime(String salesMan,Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.salesName=:salesMan and quotation.time <:date";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getQuotationListBySalesmanBeforeTime(String salesMan,Date aTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.salesName=:salesMan and quotation.time <:date order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	public int getQuotationListCountBySalesmanBetweenTwoTime(String salesMan,Date aTime,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.salesName=:salesMan and quotation.time <:date1 and quotation.time >:date2 ";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getQuotationListBySalesmanBetweenTwoTime(String salesMan,Date aTime,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.salesName=:salesMan and quotation.time <:date1 and quotation.time >:date2 order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	public int getQuotationListCountBySalesmanWithoutTime(String salesMan){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from QuotationList as quotation where quotation.salesName=:salesMan";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getQuotationListBySalesmanWithoutTime(String salesMan,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from QuotationList as quotation where quotation.salesName=:salesMan order by quotation.id desc";
		Query query = session.createQuery(hql);
		query.setString("salesMan", salesMan);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	
	
	
}
