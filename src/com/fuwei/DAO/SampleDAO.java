package com.fuwei.DAO;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.Sample;
import com.fuwei.util.DateFormateUtil;
import com.fuwei.util.HibernateUtil;

public class SampleDAO {
	private Session session;

	public SampleDAO() {
	}

	public int addSample(Sample sample) {
		try {
			session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(sample);
			transaction.commit();
			session.close();
			return sample.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}

	}

	public Sample getSample(int id) {
		session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Sample sample = (Sample) session.get(Sample.class, id);
		transaction.commit();
		session.close();
		return sample;
	}
	
	//默认搜索
	public int getAllSampleBetweenTwoTimeCount(Date aTime,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where 1=1 and sample.date <:date1 and sample.date >:date2 ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getAllSampleBetweenTwoTime(Date aTime,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where 1=1 and sample.date <:date1 and sample.date >:date2 order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	public int getAllSampleAfterTimeCount(Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where 1=1 and sample.date >:date";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getAllSampleAfterTime(Date bTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where 1=1 and sample.date >:date order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getBeforeDay(bTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	public int getAllSampleBeforeTimeCount(Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where 1=1 and sample.date <:date";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getAllSampleBeforeTime(Date aTime,int firstPag,int maxPage){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where 1=1 and sample.date <:date order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date", DateFormateUtil.getNextDay(aTime));
		query.setFirstResult(firstPag);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	public int getAllSampleCount(){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample";
		Query query = session.createQuery(hql);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getAllSample(int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
		
	}
	//按款号搜索

	
	//SAMPLE独有开始
	public Object searchSampleByProductNumber(String productNumber) {

		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.productNumber=:productNumber";
		Query query = session.createQuery(hql);
		query.setString("productNumber", productNumber);
		List samples = query.list();
		if (!samples.isEmpty()) {
			// return samples.get(0);
			// bug修改
			return samples;
			// bug修改
		}
		return null;
	}
	
	
	public int getSampleCountByIDSETWithoutTime(Set<Integer> idSet){
		if (idSet == null || idSet.size() < 1) {
			return 0;
		}
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.id in(:id)";
		Query query = session.createQuery(hql);

		query.setParameterList("id", idSet);
		int count = Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getSampleByIDSETWithoutTime(Set<Integer> idSet,int first,int max){
		// bug修改
		if (idSet.size() == 0 || idSet == null) {
			return null;
		}
		// bug修改
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.id in(:id) order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setParameterList("id", idSet);
		query.setFirstResult(first);
		query.setMaxResults(max);

		return query.list();
	}
	
	
	public int getSampleCountByIDSETBetweenTwoTime(Set<Integer> idSet,Date aTime,Date bTime){
	
		if (idSet.size() == 0 || idSet == null) {
			return 0;
		}
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.id in(:idSet) and sample.date <:date1 and sample.date >:date2 ";
		Query query = session.createQuery(hql);
		query.setParameterList("idSet", idSet);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getSampleByIDSETBetweenTwoTime(Set<Integer> idSet,Date aTime,Date bTime,int first,int max){
		if (idSet.size() == 0 || idSet == null) {
			return null;
		}
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.id in(:id) and sample.date <:date1 and sample.date >:date2 order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setParameterList("id", idSet);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	public int getSampleCountByIDSETBeforeTime(Set<Integer> idSet,Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.id in(:id) and sample.date >:date2 ";
		Query query = session.createQuery(hql);
		query.setDate("date2", aTime);
		query.setParameterList("id", idSet);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getSampleByIDSETBeforeTime(Set<Integer> idSet,Date aTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.id in(:id) and sample.date >:date2 order by sample.id desc ";
		Query query = session.createQuery(hql);
		query.setDate("date2", aTime);
		query.setParameterList("id", idSet);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	public Object getSampleByIDSETAfterTime(Set<Integer> idSet,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.id in(:id) and sample.date <:date1 order by sample.id desc ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setParameterList("id", idSet);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	
	public int getSampleCountByIDSETAfterTime(Set<Integer> idSet,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.id in(:id) and sample.date <:date1 ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setParameterList("id", idSet);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	//SAMPLE独有结束
	
	//根据业务员搜索
	
	public int getSampleCountByDeveloperAfterTime(String developer,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.developer=:developer and sample.date <:date1 ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setString("developer", developer);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getSampleByDeveloperAfterTime(String developer,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.developer=:developer and sample.date <:date1 order by sample.id desc ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setString("developer", developer);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	
	
	public int getSampleCountByDeveloperBeforeTime(String developer,Date aTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.developer=:developer and sample.date >:date2 ";
		Query query = session.createQuery(hql);
		query.setDate("date2", aTime);
		query.setString("developer", developer);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	public Object getSampleByDeveloperBeforeTime(String developer,Date aTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.developer=:developer and sample.date >:date2 order by sample.id desc ";
		Query query = session.createQuery(hql);
		query.setDate("date2", aTime);
		query.setString("developer", developer);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	
	public int getSampleCountByDeveloperBetweenTwoTime(String developer,Date aTime,Date bTime){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.developer=:developer and sample.date <:date1 and sample.date >:date2 ";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setString("developer", developer);
		int count=Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	public Object getSampleByDeveloperBetweenTwoTime(String developer,Date aTime,Date bTime,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.developer=:developer and sample.date <:date1 and sample.date >:date2 order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setDate("date1", DateFormateUtil.getNextDay(bTime));
		query.setDate("date2", aTime);
		query.setString("developer", developer);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}
	
	
	public int getSampleCountByDeveloperWithoutTime(String developer){
		session = HibernateUtil.getSession();
		String hql = "select count(*) as count from Sample as sample where sample.developer=:developer";
		Query query = session.createQuery(hql);

		query.setString("developer", developer);
		int count = Integer.valueOf(query.iterate().next().toString());
		return count;
	}
	
	
	
	public Object getSampleByDeveloperWithoutTime(String developer,int first,int max){
		session = HibernateUtil.getSession();
		String hql = "from Sample as sample where sample.developer=:developer order by sample.id desc";
		Query query = session.createQuery(hql);
		query.setString("developer", developer);
		query.setFirstResult(first);
		query.setMaxResults(max);

		return query.list();
	}
}
