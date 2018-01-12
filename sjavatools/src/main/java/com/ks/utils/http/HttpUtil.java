package com.ks.utils.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 * @version 2017年3月10日
 */
public class HttpUtil {
	
	/** 
     * 发起http get请求获取返回结果 
     * @param requestUrl 请求地址 
     * @return 
     */  
    public static String httpGetRequest(String requestUrl) {  
        StringBuffer buffer = new StringBuffer();  
        try {
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
            // http正文内，因此需要设为true, 默认情况下是false;   
            httpUrlConn.setDoOutput(false);  
            // 设置是否从httpUrlConnection读入，默认情况下是true; 
            httpUrlConn.setDoInput(true);  
            // Post 请求不能使用缓存 
            httpUrlConn.setUseCaches(false);  
  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
  
        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  
    
    /** 
     * 发起http get请求获取返回结果 
     * @param requestUrl 请求地址 
     * @param params 中的Object暂只支持对象不支持对象集合
     * @return 
     */  
    public static String httpPostRequest(String requestUrl,Map<String, Object> params) {  
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
            // http正文内，因此需要设为true, 默认情况下是false;   
            httpUrlConn.setDoOutput(true);  
            // 设置是否从httpUrlConnection读入，默认情况下是true; 
            httpUrlConn.setDoInput(true);  
            // Post 请求不能使用缓存 
            httpUrlConn.setUseCaches(false);  
  
            httpUrlConn.setRequestMethod("POST");  
            DataOutputStream out = new DataOutputStream(httpUrlConn.getOutputStream());
            StringBuilder sb = new StringBuilder("{");
            int i = 0;
            for (Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                if(i>0){
                	sb.append(",");
                }
                sb.append("\""+entry.getKey()+"\":");
                if (entry.getValue() instanceof String) {
                	sb.append("\""+entry.getValue().toString()+"\"");
				}else{
					sb.append(entry.getValue());
				}
                i++;
            }
            sb.append("}");
            out.writeBytes(sb.toString());
            httpUrlConn.connect();  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }
    
    
    /** 
     * 发起http get请求获取返回结果 
     * @param requestUrl 请求地址 
     * @param paramsJson 组装好的json参数
     * @return 
     */  
    public static String httpPostRequest(String requestUrl,String paramsJson) {  
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
            // http正文内，因此需要设为true, 默认情况下是false;   
            httpUrlConn.setDoOutput(true);  
            // 设置是否从httpUrlConnection读入，默认情况下是true; 
            httpUrlConn.setDoInput(true);  
            // Post 请求不能使用缓存 
            httpUrlConn.setUseCaches(false);  
  
            httpUrlConn.setRequestMethod("POST");  
            DataOutputStream out = new DataOutputStream(httpUrlConn.getOutputStream());
            out.writeBytes(paramsJson);
            httpUrlConn.connect();  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }

}

