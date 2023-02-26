package com.hspedu.qqclient.view;

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
                        System.out.println("\n========欢迎 (用户 " + userID + ") ========");
                        // 进入二级菜单
                        while (loop) {
                            System.out.println("\n========网络通信系统二级菜单(用户 " + userID + ")========");
                            System.out.println("\t\t\t1 显示在线用户列表");
                            System.out.println("\t\t\t2 群发消息");
                            System.out.println("\t\t\t3 私聊消息");
                            System.out.println("\t\t\t4 发送文件");
                            System.out.println("\t\t\t9 群发消息");
                            System.out.print("请输入你的选择: ");

                            key = Utility.readString(1);
                            switch (key) {
                                case "1" -> {
                                    System.out.println("显示在线用户列表");
                                }
                                case "2" -> {
                                    System.out.printf("群发消息");
                                }
                                case "3" -> {
                                    System.out.println("私聊消息");
                                }
                                case "4" -> {
                                    System.out.println("发送文件");
                                }
                                case "9" -> {
                                    loop = false;
                                }
                            }
                        }
                    } else {
                        System.out.println("========登录失败========\n\n\n");
                    }
                }
                case "9" -> this.loop = false;
            }
        }
    }

}
