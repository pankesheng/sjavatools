/**
 * 
 */
package com.ks.utils.poi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @date 2018年12月12日
 * @author pks
 *
 */
public class ImportUtil {
	
	
	public static Object convertObj(Class<?> c,Map<Integer,String> map){
		Object obj = null;
		try {
			obj = c.newInstance();
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if(field.isAnnotationPresent(ImportResources.class)) {
					ImportResources resources = field.getAnnotation(ImportResources.class);
					int index = resources.index();
					String value = map.get(index);
					if(value!=null) {
						String field_type = field.getGenericType().toString();
						if("class java.lang.String".equals(field_type)) {
							setter(obj, field.getName(), value, String.class);
						} else if("class java.lang.Integer".equals(field_type)) {
							if(value.matches("^[0-9]*$")) {
								Integer vInteger = Integer.parseInt(value);
								setter(obj, field.getName(), vInteger, Integer.class);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	/**
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * @param value
	 *            设置的值
	 * @param type
	 *            参数的属性
	 * */
	public static void setter(Object obj, String att, Object value,
			Class<?> type) {
		try {
			if(StringUtils.isNotBlank(att)) {
				att = toUpperCaseFirstOne(att);
				Method method = obj.getClass().getMethod("set" + att, type);
				method.invoke(obj, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//首字母转大写
	public static String toUpperCaseFirstOne(String s){
	  if(Character.isUpperCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
}
