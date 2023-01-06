package com.algz.websocket.javax;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.algz.demo.typicalExample.TypicalExampleController;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ws://127.0.0.1:8080/algz/websocket/123
 * @author algz
 *
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
	
	private static final Logger logger=LoggerFactory.getLogger(WebSocketServer.class);
	
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName){
    	InetSocketAddress remoteAddress = getRemoteAddress(session);
        System.out.println("有新连接加入！" + remoteAddress);   
        
        sessionPools.put(userName, session);
        addOnlineCount();
        logger.info(userName + "加入 WebSocketJavax ！当前人数为" + onlineNum);
//        System.out.println(userName + "加入 WebSocketJavax ！当前人数为" + onlineNum);
        try {
            sendMessage(session, "欢迎" + userName + "加入 WebSocketJavax 连接！");
        } catch (IOException e) {
        	logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName){
        sessionPools.remove(userName);
        subOnlineCount();
        logger.info(userName + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(@PathParam(value = "sid") String userName,String message) throws IOException{
        message = "客户端" +userName+":"+ message ;
        logger.info(message);
        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, "已收到"+session.getId());
            } catch(Exception e){
            	logger.error(e.getLocalizedMessage());
//                e.printStackTrace();
                continue;
            }
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
    	String sid=session.getPathParameters().get("sid");
    	logger.error(sid+" 发生错误,"+throwable.getMessage());
//        throwable.printStackTrace();
    }

    
  //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
//                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);
                logger.info("向客户端发送信息："+message);
            }
        }
    }
    
    
    /**
     * 给指定用户发送信息
     * @param userName
     * @param message
     */
    public void sendMsgToUser(String userName, String message){
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 给指定用户发送信息
     * @param userName
     * @param message
     */
    public void sendMsgToAllUser(String message){
        try {
        	for(String key:sessionPools.keySet()) {
        		Session session=sessionPools.get(key);
        		sendMessage(session, message);
        	}
        }catch (Exception e){
        	logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    
    private static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    private static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

    //获取客户端Ip.
    public static InetSocketAddress getRemoteAddress(Session session) {
		if (session == null) {
			return null;
		}
		Async async = session.getAsyncRemote();
		
		//在Tomcat 8.0.x版本有效
//		InetSocketAddress addr = (InetSocketAddress) getFieldInstance(async,"base#sos#socketWrapper#socket#sc#remoteAddress");
		//在Tomcat 8.5以上版本有效
		InetSocketAddress addr = (InetSocketAddress) getFieldInstance(async,"base#socketWrapper#socket#sc#remoteAddress");
		return addr;
	}
 
	private static Object getFieldInstance(Object obj, String fieldPath) {
		String fields[] = fieldPath.split("#");
		for (String field : fields) {
			obj = getField(obj, obj.getClass(), field);
			if (obj == null) {
				return null;
			}
		}
 
		return obj;
	}
 
	private static Object getField(Object obj, Class<?> clazz, String fieldName) {
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field field;
				field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field.get(obj);
			} catch (Exception e) {
			}
		}
 
		return null;

	}
}