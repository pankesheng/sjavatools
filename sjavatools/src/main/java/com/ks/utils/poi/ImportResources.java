package com.ks.utils.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * @date 2018年12月12日
 * @author pks
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ImportResources {
	
	/**map的key值，即为excel中的列序号，从0开始*/
	int index() ;
	
}

