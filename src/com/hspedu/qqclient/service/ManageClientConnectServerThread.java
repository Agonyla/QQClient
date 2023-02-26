package com.hspedu.qqclient.service;

import java.util.HashMap;

/**
 * @Author Agony
 * @Create 2023/2/26 21:24
 * @Version 1.0
 * 该类管理客户端连接到服务器端线程的类
 */
public class ManageClientConnectServerThread {

    // 把多个线程放入到HashMap集合中 key 为 用户ID, value 为 ClientConnectServerThread
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    // 将某个线程加入到集合中
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userId, clientConnectServerThread);

    }

    // 通过userId得到对应的线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hm.get(userId);
    }
}
