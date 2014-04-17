package com.fuwei.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class FWOrder {
	private int id;
	//公司合同号
	private String companyOrderNumber;
	//工厂合同号  自动生成
	private String fwOrderNumber;
	//公司名称
	private String companyName;
	//下单时间
	private Date orderTime;
	//公司业务员
	private String salesManName;
	//工厂跟单人员
	private String developer;
	
	private String note;
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSalesManName() {
		return salesManName;
	}
	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyOrderNumber() {
		return companyOrderNumber;
	}
	public void setCompanyOrderNumber(String companyOrderNumber) {
		this.companyOrderNumber = companyOrderNumber;
	}
	public String getFwOrderNumber() {
		return fwOrderNumber;
	}
	public void setFwOrderNumber(String fwOrderNumber) {
		this.fwOrderNumber = fwOrderNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
