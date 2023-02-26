package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Author Agony
 * @Create 2023/2/26 21:13
 * @Version 1.0
 */
public class ClientConnectServerThread extends Thread {


    private Socket socket;

    // 接受一个Socket对象
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        // 因为Thread需要在后台和服务器通信, 因此我们while循环
        while (true) {
            System.out.println("客户端线程, 等待读取从服务器发送的消息....");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 如果没有数据, 线程会阻塞在这里
                Message message = (Message) ois.readObject();
                // 后面再做其他的处理
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
