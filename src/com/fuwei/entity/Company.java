package com.fuwei.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*公司类*/
@Entity
public class Company {
	private String address;
	private String name;
	private String destination;
	private int id;
	private String jianChen;
	private String quanChen;
	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDestination() {
		return destination;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public String getJianChen() {
		return jianChen;
	}
	public String getQuanChen() {
		return quanChen;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setJianChen(String jianChen) {
		this.jianChen = jianChen;
	}
	public void setQuanChen(String quanChen) {
		this.quanChen = quanChen;
	}
}
