package com.fuwei.entity;

public class TransQuotation {
	private String pictureName;
	private String companyName;
	private String salesManName;
	private String fwStyleNumber;
	private double price;
	private double kezhong;
	private int companyPriceID;
	private int sampleID;
	public int getSampleID() {
		return sampleID;
	}
	public void setSampleID(int sampleID) {
		this.sampleID = sampleID;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSalesManName() {
		return salesManName;
	}
	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}
	public String getFwStyleNumber() {
		return fwStyleNumber;
	}
	public void setFwStyleNumber(String fwStyleNumber) {
		this.fwStyleNumber = fwStyleNumber;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getKezhong() {
		return kezhong;
	}
	public void setKezhong(double kezhong) {
		this.kezhong = kezhong;
	}
	public int getCompanyPriceID() {
		return companyPriceID;
	}
	public void setCompanyPriceID(int companyPriceID) {
		this.companyPriceID = companyPriceID;
	}

}
