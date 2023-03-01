package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author Agony
 * @Create 2023/3/1 10:12
 * @Version 1.0
 * 该类完成文件传输服务
 */
public class FileClientServier {

    /**
     * @param src      源文件路径
     * @param dest     目标路径
     * @param senderId 发送用户id
     * @param getterId 接受用户id
     */
    public void sendFileToOne(String src, String dest, String senderId, String getterId) {

        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        // 从本地读取文件
        FileInputStream fileInputStream = null;
        byte[] bytes = new byte[(int) new File(src).length()];
        try {

            fileInputStream = new FileInputStream(src);
            fileInputStream.read(bytes);
            message.setFileBytes(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 提示信息
        System.out.println("\n" + senderId + " 给 " + getterId + " 发送文件: " + src
                + "\n" + " 到对方电脑的目录: " + dest);

        // 向服务器发送文件
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
