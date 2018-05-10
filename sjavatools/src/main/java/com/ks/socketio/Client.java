package com.ks.socketio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.ks.entity.Template;

/**
 * @author pks
 * @version 2018年5月9日
 */
public class Client {
	public static void main(String[] args) {
		client1();
	}
	
	public static void client1(){
		try {
			// 1.创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 1122);
			// 2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();// 字节输出流
			PrintWriter pw = new PrintWriter(os);// 将输出流包装为打印流
			String json = "用户名：whf;密码：789";
			Template t = new Template();
			t.setName("1111");
			t.setContent("content1111");
			t.setBirthday(new Date());
			Template t2 = new Template();
			t2.setName("2222");
			t2.setContent("content2222");
			t2.setBirthday(new Date());
			List<Template> list = new ArrayList<Template>();
			list.add(t);
			list.add(t2);
			json = new Gson().toJson(list);
			pw.write(json);
			pw.flush();
			socket.shutdownOutput();// 关闭输出流
			// 3.获取输入流，并读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是客户端，服务器说：" + info);
			}
			// 4.关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
