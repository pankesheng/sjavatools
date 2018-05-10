package com.ks.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * @author pks
 * @version 2018年5月10日
 */
public class JoinListener implements DataListener<ChatObject> {

    SocketIOServer server;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public void onData(SocketIOClient client, ChatObject data,
            AckRequest ackSender) throws Exception {
        this.server.getBroadcastOperations().sendEvent("join", data);
    }
}
