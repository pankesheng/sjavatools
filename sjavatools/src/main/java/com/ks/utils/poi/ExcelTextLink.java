package com.ks.utils.poi;

import java.io.Serializable;

public class ExcelTextLink implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -127587885194991144L;
	private String address;
	private String title;
	
	public ExcelTextLink() {
		// TODO Auto-generated constructor stub
	}
	
	public ExcelTextLink(String address, String title) {
		super();
		this.address = address;
		this.title = title;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
