package com.ks.socketio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author pks
 * @version 2018年5月9日
 */
public class Server {

	public static void main(String[] args) throws Exception {
		server1();
	}

	public static void server1() throws IOException {
		int port = 1122;
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress(port), 3);
		Socket socket = null;
		// 记录客户端的数量
		int count = 0;
		System.out.println("***服务器即将启动，等待客户端的连接***");
		// 循环监听等待客户端的连接
		while (true) {
			// 调用accept()方法开始监听，等待客户端的连接
			socket = serverSocket.accept();
			// 创建一个新的线程
			ServerThread serverThread = new ServerThread(socket);
			// 启动线程
			serverThread.start();
			count++;// 统计客户端的数量
			System.out.println("客户端的数量：" + count);
			InetAddress address = socket.getInetAddress();
			System.out.println("当前客户端的IP：" + address.getHostAddress());
		}
	}

	public static void server2() throws IOException {
		
		
		
	}

}
