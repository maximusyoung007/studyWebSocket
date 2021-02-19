package com.maximus.youngws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ServerManager {
    private static Collection<YoungWSServer> servers = Collections.synchronizedCollection(new ArrayList<YoungWSServer>());

    public static void broadCast(String msg) {
        for(YoungWSServer server : servers) {
            try{
                server.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getTotal() {
        return servers.size();
    }

    public static void add(YoungWSServer server) {
        System.out.println("有新连接加入，当前总连接数是：" + servers.size());
        servers.add(server);
    }

    public static void remove(YoungWSServer server) {
        System.out.println("有连接退出，当前连接总数为" + servers.size());
        servers.remove(server);
    }
}
