package com.fuwei.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductionNotification {
	private int id;
	private int fworderID;
	//加工单位
	private String jiagongdanwei;
	//生产通知单号
	private String notificationNumber;
	private String companyName;
	//订单号
	private String fworderNumber;
	//货号
	private String styleNumber;
	//产品名称
	private String productName;
	//生产计划数
	private int exceptProductQuantity;
	//产品克重
	private double kezhong;
	//交货日期
	private Date deadlineTime;
	//机器针型
	private String machineZhenXing;
	//备注
	private String note;
	private String pictureName;
	private Date creatTime;
	//内容json字符串  json数组， json对象内容：色号，色别，尺寸，生产数量，材料名称，材料数量，损耗，总材料
	private String contentJSONString;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFworderID() {
		return fworderID;
	}
	public void setFworderID(int fworderID) {
		this.fworderID = fworderID;
	}
	public String getJiagongdanwei() {
		return jiagongdanwei;
	}
	public void setJiagongdanwei(String jiagongdanwei) {
		this.jiagongdanwei = jiagongdanwei;
	}
	public String getNotificationNumber() {
		return notificationNumber;
	}
	public void setNotificationNumber(String notificationNumber) {
		this.notificationNumber = notificationNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFworderNumber() {
		return fworderNumber;
	}
	public void setFworderNumber(String fworderNumber) {
		this.fworderNumber = fworderNumber;
	}
	public String getStyleNumber() {
		return styleNumber;
	}
	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getExceptProductQuantity() {
		return exceptProductQuantity;
	}
	public void setExceptProductQuantity(int exceptProductQuantity) {
		this.exceptProductQuantity = exceptProductQuantity;
	}
	public double getKezhong() {
		return kezhong;
	}
	public void setKezhong(double kezhong) {
		this.kezhong = kezhong;
	}
	public Date getDeadlineTime() {
		return deadlineTime;
	}
	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = deadlineTime;
	}
	public String getMachineZhenXing() {
		return machineZhenXing;
	}
	public void setMachineZhenXing(String machineZhenXing) {
		this.machineZhenXing = machineZhenXing;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getContentJSONString() {
		return contentJSONString;
	}
	public void setContentJSONString(String contentJSONString) {
		this.contentJSONString = contentJSONString;
	}
}
