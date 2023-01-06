package com.algz.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息的生产者，这个类提供一个发送消息的方法。
 * @author algz
 *
 */
@Service
public class MessageProducerService {
	 	@Autowired
	    private RedisTemplate<Object, Object> redisTemplate;
	    
	    @Value("${redis.queue.key}")
	    private String queueKey;
	    
	    public Long sendMeassage(MessageEntity message) {
	        System.out.println("发送了" + message);
	        return redisTemplate.opsForList().leftPush(queueKey, message);
	    }
}
