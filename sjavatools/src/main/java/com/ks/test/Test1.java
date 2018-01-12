package com.ks.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

/**
 * @author pks
 * @version 2017年5月24日
 */
public class Test1 {
	public static void main(String[] args) {
		try {
			String json = "%7B%22userIds%22%3A%22%5B1234795180411904%5D%22%2C%22account%22%3A%22netmanager_admin%22%2C%22password%22%3A%22netmanager_ohjy123.%22%7D=";
			json = URLDecoder.decode(json, "utf-8");
			JSONObject jsonObject = JSONObject.fromObject(json.substring(0,json.length()-1));
			System.out.println(jsonObject.getString("account"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

