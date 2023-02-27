package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;
import com.hspedu.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author Agony
 * @Create 2023/2/26 21:01
 * @Version 1.0
 * 该类完成用户登录验证和注册等功能
 */
public class UserClientService {

    // 可能在其他地方会使用user信息
    private User user = new User();
    private Socket socket;

    public boolean checkUser(String userId, String password) {

        // 确认时候接受成功
        boolean b = false;
        user.setUserId(userId);
        user.setPasswd(password);

        try {
            // 连接到服务端, 发送user对象
            socket = new Socket(InetAddress.getByName("10.51.8.30"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);

            // 读取从服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {

                // 创建一个和服务器端保持通信的线程 -> 创建一个类 ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                // 启动客户端的线程
                clientConnectServerThread.start();
                // 为了后面客户端的扩展, 我们将线程放到集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                b = true;

            } else {
                // 登陆失败
                socket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b;

    }

    // 向服务器端请求在线用户列表
    public void onlineFriendList() {

        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());

        // 发送给服务器
        // 应该得到当前线程的socket 对应的 ObjectOutputStream
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // 编写方法退出客户端，并给服务器发送一个退出系统的message对象
    public void logout() {

        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(user.getUserId());

        // 发送message
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(user.getUserId() + " 退出系统");
            System.exit(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
