package com.algz.socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
        // 定义ServerSocket和Socket对象
        ServerSocket ss = null;
        Socket sk;
        // 定义发送字符串，即客户端收到的信息
        String sendString = "hello！我是服务端...";
        // 定义OutputStream类
        OutputStream s1out;
        // 定义DataOutputStream类
        DataOutputStream dos;
        // 通过1314端口建立连接
        try {
            // 创建ServerSocket并把传入端口号
            ss = new ServerSocket(1314);
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        }
        // 循环运行监听程序，以监视连接请求
        while (true) {
            try {
                // 监听端口请求，等待连接
                sk = ss.accept();
                // 得到与socket相连接的数据流对象
                s1out = sk.getOutputStream();
                dos = new DataOutputStream(s1out);
                // 发送字符串
                dos.writeUTF(sendString);
                // 关闭数据流（但不是关闭Socket连接）
                dos.close();
                s1out.close();
                sk.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
