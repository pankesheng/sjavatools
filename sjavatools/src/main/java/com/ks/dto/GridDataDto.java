package com.ks.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class GridDataDto {
	private String key;
	private String value;
	private String color;
	private boolean operbool;
	private String oper;
	
	public GridDataDto() {
		// TODO Auto-generated constructor stub
	}
	
	public GridDataDto(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public GridDataDto(String key, String value, String color) {
		super();
		this.key = key;
		this.value = value;
		this.color = color;
	}
	
	

	public GridDataDto(String key, String value, String color,
			boolean operbool, String oper) {
		super();
		this.key = key;
		this.value = value;
		this.color = color;
		this.operbool = operbool;
		this.oper = oper;
	}

	public boolean isOperbool() {
		return operbool;
	}

	public void setOperbool(boolean operbool) {
		this.operbool = operbool;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public static String toJson(List<List<GridDataDto>> list){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if(i>0)	sb.append(",");
			sb.append("{");
			List<GridDataDto> list2 = list.get(i);
			for (int j = 0; j < list2.size(); j++) {
				GridDataDto dto = list2.get(j);
				if(j>0) sb.append(",");
				if(dto.operbool){
					sb.append("\"oper\":\"<div style='overflow:hidden;'><a class='btn btn-primary' href='###' onclick='"+dto.getOper()+"'>编辑</a>\\n</div>\"");
				}else{
					sb.append("\""+dto.getKey()+"\":");
					if(StringUtils.isNotBlank(dto.getColor())){
						sb.append("\"<font color='"+dto.getColor()+"'>"+dto.getValue()+"</font>\"");
					}else{
						sb.append("\""+dto.getValue()+"\"");
					}
				}
			}
			sb.append("}");
		}
		return sb.toString();
	}
	
}
