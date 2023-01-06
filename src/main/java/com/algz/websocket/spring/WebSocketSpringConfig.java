package com.algz.websocket.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Configuration  
@EnableWebSocket 
public class WebSocketSpringConfig implements WebSocketConfigurer {

	/**
	 * ws://127.0.0.1:8080/algz/websocket?userCode=1
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(httpAuthHandler(),"/websocket")  
        .addInterceptors(new OrderInterceptor()).setAllowedOrigins("*");
	}
	
	 @Bean  
	 public TextWebSocketHandler httpAuthHandler(){  
	        return new HttpAuthHandler();  
	 }  

}
