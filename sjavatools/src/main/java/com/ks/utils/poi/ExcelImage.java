package com.ks.utils.poi;

import java.io.Serializable;

/**
 * @author pks
 * @version 2018年1月10日
 */
public class ExcelImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5325932553756421130L;
	
	private byte[] image_data;
	private int width;
	private int height;

	public ExcelImage() {
		// TODO Auto-generated constructor stub
	}
	
	public ExcelImage(byte[] image_data, int width, int height) {
		super();
		this.image_data = image_data;
		this.width = width;
		this.height = height;
	}

	public byte[] getImage_data() {
		return image_data;
	}
	public void setImage_data(byte[] image_data) {
		this.image_data = image_data;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}

