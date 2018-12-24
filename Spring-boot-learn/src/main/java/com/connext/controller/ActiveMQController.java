package com.connext.controller;

import com.connext.activemq.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping("/activemq")
public class ActiveMQController {
    @Autowired
    private Producer producer;
    @Autowired
    private ActiveMQConnectionFactory connectionFactory;

    @RequestMapping("/queue/{msg}")
    public void sendQueue(@PathVariable("msg") String msg) {
        Destination destination = new ActiveMQQueue("test.queue");
        producer.sendMessage(destination, msg);
    }

    @RequestMapping("/topic/{msg}")
    public void sendTopic(@PathVariable("msg") String msg) {
        Destination destination = new ActiveMQTopic("test.topic");
        producer.sendMessage(destination, msg);
    }
}
