package com.ks.utils.webservice;



import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;  
import org.apache.axis.client.Service;  
 

/**
 * @author pks
 * @version 2018年11月27日
 * 
 * 	<dependency>
		<groupId>org.apache.cxf</groupId>
		<artifactId>cxf-rt-frontend-jaxws</artifactId>
		<version>2.7.6</version>
	</dependency>
	<dependency>
		<groupId>org.apache.cxf</groupId>
		<artifactId>cxf-rt-transports-http</artifactId>
		<version>2.7.6</version>
	</dependency>
 * 
 */
public class SUtilWs {
	public static void main(String[] args) throws Exception {
//		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
//		fac.setServiceClass(CnsServices.class);
//		fac.setAddress("http://10.131.1.11:8080/CnsServices?wsdl");
//		final CnsServices cs = (CnsServices) fac.create();
//		String s = cs.getMac("3.64.27.66");
		
		String endpoint = "http://rz.ohedu.cn/ohwg/User?wsdl";  
        // 直接引用远程的wsdl文件  
        // 以下都是套路  
        Service service = new Service();  
        Call call = (Call) service.createCall();  
        call.setTargetEndpointAddress(new java.net.URL(endpoint));  
        call.setOperationName("getToken");// WSDL里面描述的接口名称  
        call.addParameter("account",  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
                javax.xml.rpc.ParameterMode.IN);// 接口的参数  
        call.addParameter("password",  
                org.apache.axis.encoding.XMLType.XSD_STRING,  
                javax.xml.rpc.ParameterMode.IN);// 接口的参数  
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型  
        String account = "thanone_admin";
        String password = "ohedu123456";
        String result = (String) call.invoke(new Object[] { account,password });  
        // 给方法传递参数，并且调用方法  
        System.out.println("result is " + result); 
	}
}

