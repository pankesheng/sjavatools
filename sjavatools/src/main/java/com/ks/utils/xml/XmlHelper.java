package com.ks.utils.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * XML的帮助类
 * 
 * @author pks
 * @version 2018年1月12日
 */
@SuppressWarnings("rawtypes")
public class XmlHelper {

	/**
	 * 将自定义数据对象转化为XML字符串
	 * 
	 * @param clazz
	 *            自定义数据类型
	 * @param object
	 *            自定义数据对象
	 * @return XML字符串
	 * @throws JAXBException
	 *             异常
	 */
	public static String objectToXML(Class clazz, Object object)
			throws JAXBException {
		String xml = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller m = context.createMarshaller();
//		m.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//是否格式化生成的xml串
//		m.setProperty(Marshaller.JAXB_FRAGMENT, false);//是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）
		
		Writer w = new StringWriter();
		m.marshal(object, w);
		xml = w.toString();
		return xml;
	}

	/**
	 * 将XML字符串转化为自定义数据对象
	 * 
	 * @param clazz
	 *            自定义数据类型
	 * @param xml
	 *            XML字符串
	 * @return 自定义数据对象
	 * @throws JAXBException
	 *             异常
	 */
	public static Object xmlToObject(Class clazz, String xml)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller um = context.createUnmarshaller();
		return um.unmarshal(new StringReader(xml));
	}
}
