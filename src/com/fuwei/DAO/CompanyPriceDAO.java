package com.fuwei.DAO;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fuwei.entity.CompanyPrice;
import com.fuwei.util.HibernateUtil;

public class CompanyPriceDAO {
	private Session session;
	public CompanyPriceDAO(){}
	
	public int addCompanyPrice(CompanyPrice companyPrice){
		try {
			session=HibernateUtil.getSession();
			Transaction transaction=session.beginTransaction();
			session.save(companyPrice);
			transaction.commit();
			session.close();
			return companyPrice.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public CompanyPrice getCompanyPrice(int id){
		session=HibernateUtil.getSession();
		Transaction transaction=session.beginTransaction();
		CompanyPrice companyPrice=(CompanyPrice)session.get(CompanyPrice.class, id);
		transaction.commit();
		session.close();
		return companyPrice;
	}
	
	public Object searchCompanyPriceBySampleID(int sampleID){
		
		session= HibernateUtil.getSession();
		String hql="from CompanyPrice as company where company.sampleId=:sampleID";
		Query query=session.createQuery(hql);
		query.setInteger("sampleID", sampleID);
		List companys=query.list();
		
		return companys;
	}
	
	public Object searchCompanyPriceByCompanyName(String companyName){
		session=HibernateUtil.getSession();
		String hql="from CompanyPrice as company where company.companyName=:companyName";
		Query query=session.createQuery(hql);
		query.setString("companyName", companyName);
		return query.list();
	}
	
	public Object searchCompanyPriceByProductNumber(String productName){
		session=HibernateUtil.getSession();
		String hql="from CompanyPrice as company where company.productName=:productName";
		Query query=session.createQuery(hql);
		query.setString("productName", productName);
		return query.list();
	}
	
	
	public Object searchCompanyPriceBySalesManName(String salesManName){
		session=HibernateUtil.getSession();
		String hql="from CompanyPrice as company where company.salesMan=:salesMan";
		Query query=session.createQuery(hql);
		query.setString("salesMan", salesManName);
		return query.list();
	}
	
	public Object getCompanyPriceBYIDSET(Set<Integer> idSet){
		session = HibernateUtil.getSession();
		String hql = "from CompanyPrice as companyprice where companyprice.id in(:id)";
		Query query = session.createQuery(hql);
		query.setParameterList("id", idSet);
		return query.list();
	}
	
	
	public List<CompanyPrice> getAllCompanyPrice(){
		session=HibernateUtil.getSession();
		String hql="from CompanyPrice as company";
		Query query=session.createQuery(hql);
		return query.list();
	}
	

}
