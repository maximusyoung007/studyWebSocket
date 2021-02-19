package com.maximus.youngws;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws/youngWS")
public class YoungWSServer {
    private Session session;

    @OnOpen
    public void OnOpen(Session session) {
        this.session = session;
        ServerManager.add(this);
    }

    public void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void OnClose() {
        ServerManager.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息" + message);
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
