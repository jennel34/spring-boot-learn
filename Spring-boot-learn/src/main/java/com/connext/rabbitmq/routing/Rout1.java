package com.connext.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

public class Rout1 {
    private final static String EXCHANGE_NAME = "RoutingEx";

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
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            // 创建一个非持久的、唯一的且自动删除的队列
            String queueName = channel.queueDeclare().getQueue();
            // 为转发器指定队列，设置binding
            channel.queueBind(queueName, EXCHANGE_NAME, "one");
            Consumer consumer = new MyRout(channel);
            channel.basicConsume(queueName, false, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
