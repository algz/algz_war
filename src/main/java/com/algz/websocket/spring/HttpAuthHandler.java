package com.algz.websocket.spring;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class HttpAuthHandler extends TextWebSocketHandler {
	public static final HashMap<String, WebSocketSession> userSessionMap;
	 
	 
    static {  
        userSessionMap = new HashMap<>();
    }  
  
    /** 
     * ws://127.0.0.1:8080/algz/websocket?userCode=1
     * afterConnectionEstablished 方法是在 socket 连接成功后被触发，同原生注解里的 @OnOpen 功能
     * 连接成功时候.
     */  
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        System.out.println("connect to the websocket success......");  
        String userCode = (String) session.getAttributes().get("userCode");
        if(userCode != null) {
        	// 用户连接成功，放入在线用户缓存
        	userSessionMap.put(userCode, session);
        	sendMessageToUser(userCode,new TextMessage("欢迎 " + userCode + " 加入 WebSocketSpring 连接！"));
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
    }  
  
    /** 
     * 接收消息的时候。
     * (在UI在用js调用websocket.send()时候，会调用该方法)
     * **handleTextMessage **方法是在客户端发送信息时触发，同原生注解里的 @OnMessage 功能
     */  
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
    	// 获得客户端传来的消息
        sendMessageToUsers(message);  
    }  
  
    /** 
     * 给某个用户发送消息 
     * 
     * @param userName 
     * @param message 
     */  
    public void sendMessageToUser(String userName, TextMessage message) throws Exception{  
    	String payload = message.getPayload();
        System.out.println("server 接收到 " + userName + " 发送的 " + payload);
        userSessionMap.get(userName).sendMessage(new TextMessage("server 发送给 " + userName + " 消息: " + payload + " " + LocalDateTime.now().toString()));
   
    }  
  
    /** 
     * 给所有在线用户发送消息 
     * 
     * @param message 
     * @throws Exception 
     */  
    public void sendMessageToUsers(TextMessage message) throws Exception {  
    	for(String key:userSessionMap.keySet()) {
    		sendMessageToUser(key,message);
    	}
    	
    }  
  
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        if (session.isOpen()) {  
            session.close();  
        }
        Map<String, Object> attributes = session.getAttributes();
		String userCode = (String) attributes.get("userCode");
		if(userCode != null) {
			userSessionMap.remove(userCode);
		}
    }  
    
    /**
     * **afterConnectionClosed  **方法是在 socket 连接关闭后被触发，同原生注解里的 @OnClose 功能
     */
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		Map<String, Object> attributes = session.getAttributes();
		String userCode = (String) attributes.get("userCode");
		if(userCode != null) {
			userSessionMap.remove(userCode);
		}
    }  
  
    @Override  
    public boolean supportsPartialMessages() {  
        return false;  
    }  

}
