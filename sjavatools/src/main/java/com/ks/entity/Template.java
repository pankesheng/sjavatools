package com.ks.entity;

import java.util.Date;

import com.ks.utils.generator.DBField;
import com.ks.utils.generator.DBTable;

/**
 * @author pks
 * @version 2018年1月12日
 */
@DBTable(name="t_template",index_unique={"name,birthday"})
public class Template { 
	
	@DBField(type="bigint",length=20,allownull=false,comment="单位id")
	private Long unitId;
	@DBField(type="varchar",length=100,comment="名称")
	private String name;
	@DBField(type="int",length=4,allownull=false,comment="性别")
	private Integer sex;
	@DBField(type="text",comment="备注")
	private String content;
	@DBField(type="datetime",allownull=false)
	private Date birthday;
	
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}

