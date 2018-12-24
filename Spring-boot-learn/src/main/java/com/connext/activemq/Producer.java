package com.connext.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import javax.jms.Destination;
@Service("producer")
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination,final String message){
        logger.info("Producer start send Message!");
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    @JmsListener(destination = "outTest.topic")
    public void getReturn (String msg){
        logger.info("Producer get the return message:{}",msg);
    }
}
