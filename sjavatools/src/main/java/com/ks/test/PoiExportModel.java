package com.ks.test;

import java.io.Serializable;

import com.ks.utils.poi.ExcelImage;
import com.ks.utils.poi.ExcelResources;

public class PoiExportModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1980425730713047505L;
	
	private String name;
	private ExcelImage tiaoMa;
	
	@ExcelResources(title = "名称", order = 1)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ExcelResources(title = "条码", order = 2)
	public ExcelImage getTiaoMa() {
		return tiaoMa;
	}
	public void setTiaoMa(ExcelImage tiaoMa) {
		this.tiaoMa = tiaoMa;
	}
	
}
