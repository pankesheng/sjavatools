package com.ks.utils.pingyin;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 * @version 2017年4月6日
 */
public class test {
	public static void main(String[] args) {
		String v = "2123730144971776,2123730145577984";
		String v2[] = v.split(",",-1);
		for (String s : v2) {
			String key = v.substring(0,v.lastIndexOf(s)+s.length());
			if(StringUtils.isNotBlank(key)){
				System.out.println("-----------------"+s);
				System.out.println(key);
			}
		}
	}
}

