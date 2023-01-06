package com.algz.websocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {


	 /**
     * 注册stomp端点，主要是起到连接作用
     * @param stompEndpointRegistry
     */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint("/gs-guide-websocket")  //端点名称
            //.setHandshakeHandler() 握手处理，主要是连接的时候认证获取其他数据验证等
            //.addInterceptors() 拦截处理，和http拦截类似
            .setAllowedOrigins("*") //跨域
			.withSockJS(); //使用sockJS
	}

    /**
     * 注册相关服务
     * @param registry
     */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
        //这里使用的是内存模式，生产环境可以使用rabbitmq或者其他mq。
        //这里注册两个，主要是目的是将广播和队列分开。
        //registry.enableStompBrokerRelay().setRelayHost().setRelayPort() 其他方式
		config.enableSimpleBroker("/topic");
		//客户端名称前缀
		config.setApplicationDestinationPrefixes("/app");
		//用户名称前
		config.setUserDestinationPrefix("/user");
	}
}
