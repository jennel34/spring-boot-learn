package com.connext.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    // 使用JmsListener配置消费者监听的队列，其中message是接收到的消息
    @JmsListener(destination = "test.queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String message) {
        logger.info("Consumer get the message from queue: {}", message);
    }

    @JmsListener(destination = "test.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String message) {
        logger.info("Consumer get the message from topic: {}", message);
    }
}
