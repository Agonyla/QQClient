package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @Author Agony
 * @Create 2023/2/28 20:48
 * @Version 1.0
 * 该类提供和消息相关的方法
 */
public class MessageClientService {

    /**
     * @param content  内容
     * @param senderId 发送用户id
     * @param getterId 接受用户id
     */
    public void sendMessageToOne(String content, String senderId, String getterId) {

        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_COMM_MIS);
        message.setContent(content);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + " 对 " + getterId + " 说 " + content);

        // 发送给服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
