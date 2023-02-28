package com.hspedu.qqcommon;

/**
 * @Author Agony
 * @Create 2023/2/26 19:59
 * @Version 1.0
 */
public interface MessageType {

    String MESSAGE_LOGIN_SUCCEED = "1"; // 表示登录成功
    String MESSAGE_LOGIN_FAILED = "2"; // 表示登录失败
    String MESSAGE_COMM_MES = "3"; // 普通消息包
    String MESSAGE_GET_ONLINE_FRIEND = "4"; // 要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5"; // 返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6"; // 客户端请求退出
    String MESSAGE_TO_ALL_MES = "7"; // 群发消息包


}
