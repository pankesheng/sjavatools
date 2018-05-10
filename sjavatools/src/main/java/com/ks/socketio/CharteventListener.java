package com.ks.socketio;

import java.util.Collection;
import java.util.List;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * @author pks
 * @version 2018年5月10日
 */
public class CharteventListener implements DataListener<ChatObject> {

    SocketIOServer server;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public void onData(SocketIOClient client, ChatObject data,
            AckRequest ackSender) throws Exception {
        // chatevent为 事件的名称， data为发送的内容
    	Collection<SocketIOClient> clients = this.server.getAllClients(); 
    	System.out.println(client.getSessionId());
    	System.out.println("----------------------");
    	for (SocketIOClient c : clients) {
			System.out.println(c.getSessionId());
		}
    	System.out.println("----------------------");
        this.server.getBroadcastOperations().sendEvent("chatevent", data);
    }
}
