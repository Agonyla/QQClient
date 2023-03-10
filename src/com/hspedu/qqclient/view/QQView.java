package com.hspedu.qqclient.view;

import com.hspedu.qqclient.service.FileClientServier;
import com.hspedu.qqclient.service.MessageClientService;
import com.hspedu.qqclient.service.UserClientService;
import com.hspedu.qqclient.utils.Utility;

/**
 * @Author Agony
 * @Create 2023/2/26 20:04
 * @Version 1.0
 * 客户端的菜单界面
 */
public class QQView {

    public static void main(String[] args) {
        QQView qqView = new QQView();
        qqView.mainMenu();
        System.out.println("\n退出客户端系统");
    }

    // 控制是否显示菜单
    private boolean loop = true;
    // 接受用户的的键盘输入
    private String key;
    // 用于登录服务器/注册用户
    private UserClientService userClientService = new UserClientService();
    // 对象用户私聊/群聊
    private MessageClientService messageClientService = new MessageClientService();

    // 发送文件
    private FileClientServier fileClientServier = new FileClientServier();

    // 显示主菜单
    public void mainMenu() {

        while (loop) {
            System.out.println("========欢迎登录网络通信系统========");
            System.out.println("\t\t\t1 登录系统");
            System.out.println("\t\t\t9 退出系统");
            System.out.print("请输入你的选择: ");

            key = Utility.readString(1);
            switch (key) {
                case "1" -> {
                    System.out.print("请输入用户名: ");
                    String userID = Utility.readString(50);
                    System.out.print("请输入密  码: ");
                    String passwd = Utility.readString(50);
                    //
                    // 需要去服务器端验证用户是否合法
                    // 编写一个类 UserClientService[用户登录/注册功能]
                    // 这里有很多代码
                    if (userClientService.checkUser(userID, passwd)) {
                        System.out.println("\n========欢迎 (用户 " + userID + " 登录成功) ========");
                        // 进入二级菜单
                        while (loop) {
                            System.out.println("\n========网络通信系统二级菜单(用户 " + userID + ")========");
                            System.out.println("\t\t\t1 显示在线用户列表");
                            System.out.println("\t\t\t2 群发消息");
                            System.out.println("\t\t\t3 私聊消息");
                            System.out.println("\t\t\t4 发送文件");
                            System.out.println("\t\t\t9 退出系统");
                            System.out.print("请输入你的选择: ");

                            key = Utility.readString(1);
                            switch (key) {

                                // 写一个方法 获取在线用户列表
                                case "1" -> {
                                    userClientService.onlineFriendList();
                                }
                                case "2" -> {
                                    System.out.print("请输入想对大家说的话: ");
                                    String content = Utility.readString(50);
                                    // 编写一个方法
                                    messageClientService.sendMessageToAll(content, userID);

                                }
                                case "3" -> {
                                    System.out.print("请输入想聊天的用户号(在线): ");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入想说的话: ");
                                    String content = Utility.readString(100);
                                    // 编写一个方法
                                    messageClientService.sendMessageToOne(content, userID, getterId);

                                }
                                case "4" -> {
                                    System.out.print("请输入你想发送文件的用户(在线用户): ");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入发送文件的路径(D:\\Test\\QQClient\\xx.jpg): ");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入接受文件的路径(D:\\Test\\QQServer\\yy.jpg): ");
                                    String dest = Utility.readString(100);
                                    fileClientServier.sendFileToOne(src, dest, userID, getterId);
                                    
                                }
                                case "9" -> {
                                    // 调用方法， 给服务器发送一个退出系统的message
                                    userClientService.logout();
                                    loop = false;
                                }
                            }
                        }
                    } else {
                        System.out.println("========登录失败========\n\n\n");
                    }
                }
                case "9" -> loop = false;
            }
        }
    }

}
