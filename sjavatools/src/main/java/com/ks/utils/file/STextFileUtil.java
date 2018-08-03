package com.ks.utils.file;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author pks
 * @version 2018年8月1日
 */
public class STextFileUtil {
	
	/**
	 * 获取txt 文件的编码格式 便于读txt内容
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String resolveCode(String path) throws Exception {  
        InputStream inputStream = new FileInputStream(path);    
        byte[] head = new byte[3];    
        inputStream.read(head);      
        String code = "gb2312";  //或GBK  
        if (head[0] == -1 && head[1] == -2 )    
            code = "UTF-16";    
        else if (head[0] == -2 && head[1] == -1 )    
            code = "Unicode";    
        else if(head[0]==-17 && head[1]==-69 && head[2] ==-65)    
            code = "UTF-8";    
            
        inputStream.close();  
          
        System.out.println(code);   
        return code;  
    } 
}

