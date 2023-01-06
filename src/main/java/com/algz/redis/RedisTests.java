//package com.algz.redis;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.context.SpringBootTest;
//
//
//
//public class RedisTests {
//	@Autowired
//    private MessageProducerService producer;
//
//    @Autowired
//    private MessageConsumerService consumer;
//    
//    @Test
//    public void testQueue() {
//        consumer.start();
//        producer.sendMeassage(new MessageEntity("1", "aaaa"));
//        producer.sendMeassage(new MessageEntity("2", "bbbb"));
//        
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        consumer.interrupt();
//    }
//}
