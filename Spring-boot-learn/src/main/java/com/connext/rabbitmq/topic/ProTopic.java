package com.connext.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class ProTopic {
    private final static String EXCHANGE_NAME = "TopicEx";

    public static void main(String[] args) {
        send();
    }

    public static void send() {
        ConnectionFactory factory = null;
        Connection connection = null;
        Channel channel = null;
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            channel.confirmSelect();
            Set<Integer> set = new LinkedHashSet<>();
            Random random = new Random();
            while (set.size() < 10) {
                set.add(random.nextInt(10) + 1);
            }
            for (Integer i : set) {
                String routing = null;
                if (i % 3 == 0) {
                    routing = "one.three";
                    if (i % 6 == 0) {
                        routing = "two.three";
                    }
                } else if (i % 2 == 0) {
                    routing = "one.two";
                    if (i % 4 == 0) {
                        routing = "two.two";
                    }
                } else {
                    routing = "other";
                }
                String message = "Test Message" + i;
                channel.basicPublish(EXCHANGE_NAME, routing, null, message.getBytes("UTF-8"));
            }
            if (channel.waitForConfirms()) {
                System.out.println("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

