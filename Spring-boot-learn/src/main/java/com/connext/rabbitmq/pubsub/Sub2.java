package com.connext.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

public class Sub2 {
    private final static String EXCHANGE_NAME = "TestEx";

    public static void main(String[] args) {
        receive();
    }

    public static void receive() {
        ConnectionFactory factory = null;
        Connection connection = null;
        Channel channel = null;
        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName,EXCHANGE_NAME,"");
            Consumer consumer = new MyPubSub(channel);
            channel.basicConsume(queueName,false,consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
