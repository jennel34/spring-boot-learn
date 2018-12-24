package com.connext.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class Consumer2 {
    private static final Logger logger = LoggerFactory.getLogger(Consumer2.class);

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "test.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("outTest.queue")
    public String receiveQueue(String message) {
        logger.info("Consumer2 get the message from queue: {}", message);
        return "I have got the message:" + message;
    }

    @JmsListener(destination = "test.topic", containerFactory = "jmsListenerContainerTopic")
    @SendTo("outTest.topic")
    public String receiveTopic(String message) {
        logger.info("Consumer2 get the message from topic: {}", message);
        return "I have got the message:" + message;
    }
}
