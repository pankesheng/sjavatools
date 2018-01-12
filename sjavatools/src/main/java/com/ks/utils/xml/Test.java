package com.ks.utils.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pks
 * @version 2018年1月12日
 */
public class Test {
	public static void main(String[] args) throws JAXBException {
		User u1 = new User(1L, "张三");
		User u2 = new User(2L, "李四");
		List<User> userlist = new ArrayList<User>();
		userlist.add(u1);userlist.add(u2);
		Unit p_unit = new Unit(0L, "父单位", null, null);
		Unit unit = new Unit(1L, "单位", p_unit, userlist);
		String s = XmlHelper.objectToXML(Unit.class, unit);
		System.out.println(s);
		Unit xmlUnit = (Unit) XmlHelper.xmlToObject(Unit.class, s);
		System.out.println(xmlUnit);
	}
}

@XmlRootElement(name="unit")
class Unit {
	
	private Long id;
	private String name;
	private Unit p_unit;
	private List<User> userlist;
	
	public Unit() {
		// TODO Auto-generated constructor stub
	}
	
	public Unit(Long id, String name, Unit p_unit, List<User> userlist) {
		super();
		this.id = id;
		this.name = name;
		this.p_unit = p_unit;
		this.userlist = userlist;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Unit getP_unit() {
		return p_unit;
	}
	public void setP_unit(Unit p_unit) {
		this.p_unit = p_unit;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	
}

@XmlRootElement(name="user")
class User {
	private Long id;
	private String name;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
