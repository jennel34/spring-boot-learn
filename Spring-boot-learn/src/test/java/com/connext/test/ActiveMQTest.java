package com.connext.test;

import com.connext.activemq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQTest {
    @Autowired
    private Producer producer;
    @Test
    public void contextLoads() throws InterruptedException{
        Destination destination = new ActiveMQQueue("test.queue");
        for (int i = 0; i < 20 ; i++) {
            producer.sendMessage(destination,"Test Message!");
        }
    }
}
