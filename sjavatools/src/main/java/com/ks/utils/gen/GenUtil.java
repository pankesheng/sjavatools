package com.ks.utils.gen;

import java.util.Set;

/**
 * @author pks
 * @version 2018年1月12日
 */
public class GenUtil {
	public static void gen_db(){
		String packagename = "com.ks.entity";
		Set<Class<?>> classes = UtilClass.getClasses(packagename);
		for (Class<?> class1 : classes) {
			System.out.println(class1.getName());
		}
	}
}

