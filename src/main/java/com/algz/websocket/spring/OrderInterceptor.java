package com.algz.websocket.spring;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 *  
 * @author algz
 *
 */
public class OrderInterceptor extends HttpSessionHandshakeInterceptor {
	@Override  
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
       System.out.println("Before Handshake");
       String userCode = ((ServletServerHttpRequest) request).getServletRequest().getParameter("userCode");
       System.out.println(userCode);
       
        attributes.put("userCode", userCode);  //存入用户ID
  
        return super.beforeHandshake(request, response, wsHandler, attributes);  
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
        System.out.println("After Handshake");  
        super.afterHandshake(request, response, wsHandler, ex);  
    }  

}
