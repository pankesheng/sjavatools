package com.ks.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

/**
 * @author pks
 * @version 2018年5月10日
 */
public class SocketioServer {
	public static void main(String[] args) throws InterruptedException {
		Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        SocketIOServer server = new SocketIOServer(config);
        CharteventListener listner = new CharteventListener();
        listner.setServer(server);
        JoinListener join_listner = new JoinListener();
        join_listner.setServer(server);
        // chatevent为事件名称
        server.addEventListener("chatevent", ChatObject.class, listner);
        server.addEventListener("join", ChatObject.class, join_listner);
        server.addConnectListener(new ConnectListener() {
			@Override
			public void onConnect(SocketIOClient client) {
				System.out.println("------onconnect-------");
				System.out.println(client.getSessionId());
				System.out.println("----------------------");
			}
		});
        server.addDisconnectListener(new DisconnectListener() {
			
			@Override
			public void onDisconnect(SocketIOClient client) {
				System.out.println("------disconnect------");
				System.out.println(client.getSessionId());
				System.out.println("----------------------");
			}
		});
        //启动服务
        server.start();
        Thread.sleep(Integer.MAX_VALUE) ;
        server.stop();
		
	}
	
}

