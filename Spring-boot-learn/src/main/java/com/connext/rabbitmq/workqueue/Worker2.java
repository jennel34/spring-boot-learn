package com.connext.rabbitmq.workqueue;

import com.rabbitmq.client.*;

public class Worker2 {
    private final static String QUEUE_NAME = "WorkQueue";

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
            channel.basicQos(1);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Consumer consumer = new MyWorker(channel);
            channel.basicConsume(QUEUE_NAME,false,consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
