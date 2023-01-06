package com.algz.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息的消费者，消费者需要不断轮询队列，有消息便取出来，
 * @author algz
 *
 */
@Service
public class MessageConsumerService extends Thread {
	
	@Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    private volatile boolean flag = true;
    
    @Value("${redis.queue.key}")
    private String queueKey;
    
    @Value("${redis.queue.pop.timeout}")
    private Long popTimeout;
    
    @Override
    public void run() {
        try {
            MessageEntity message;
            while(flag && !Thread.currentThread().isInterrupted()) {
                message = (MessageEntity)redisTemplate.opsForList().rightPop(queueKey, popTimeout, TimeUnit.SECONDS);
                System.out.println("接收到了" + message);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
