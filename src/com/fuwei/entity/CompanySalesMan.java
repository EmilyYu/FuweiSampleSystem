package com.fuwei.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*业务员类*/
@Entity
public class CompanySalesMan {
	private int id;
	private String name;
	private String jianChen;/*简称*/
	private String phone;
	private int companyId;/*公司外键*/
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getJianChen() {
		return jianChen;
	}
	public void setJianChen(String jianChen) {
		this.jianChen = jianChen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
}
