package com.ks.dto;

public class GridHeaderDto {
	private String title;
	private String dataIndex;
	private String excel_formula;
	
	public GridHeaderDto() {
		// TODO Auto-generated constructor stub
	}
	
	public GridHeaderDto(String title, String dataIndex) {
		super();
		this.title = title;
		this.dataIndex = dataIndex;
	}
	
	public GridHeaderDto(String title, String dataIndex,String excel_formula) {
		super();
		this.title = title;
		this.dataIndex = dataIndex;
		this.excel_formula = excel_formula;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataIndex() {
		return dataIndex;
	}
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getExcel_formula() {
		return excel_formula;
	}

	public void setExcel_formula(String excel_formula) {
		this.excel_formula = excel_formula;
	}
	
}
