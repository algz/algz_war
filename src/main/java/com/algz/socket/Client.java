package com.algz.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client{
	
	/**
         * 采用socket连接向服务器发送消息，接受响应信息 注意，如果是通过不同的ip port确定调用接口 这个方法要重写，将ip
     * port作为请求条件,现改为传固有参数进来
     * 
     * @param text
     * @throws Exception
     */
    public static String sendBySocket(String text, String ip, int port)throws Exception {
        //log.info("开始建立socket连接");
        Socket socket = new Socket(ip, port);
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "GBK");
        // 2.获取客户端输出流
        OutputStream dos = socket.getOutputStream();
        //log.info("连上服务端");
        // 3.向服务端发送消息
        dos.write(text.getBytes());
        dos.flush();
        //log.info("成功向服务器发送消息");
        // 4.获取输入流，并读取服务器端的响应信息
        BufferedReader br = new BufferedReader(isr);
        String returnInfo = br.readLine();
        //log.info("服务器端返回数据为：" + returnInfo);
        // 4.关闭资源
        br.close();
        isr.close();
        is.close();
        dos.close();
        socket.close();
        return returnInfo;
    }
}