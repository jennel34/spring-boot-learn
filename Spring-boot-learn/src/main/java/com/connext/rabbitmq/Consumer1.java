package com.connext.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

public class Consumer1 {
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
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            由于4.*的版本以后QueueingConsumer被停用
            Consumer consumer = new MyConsumer(channel);
//            创建队列消费者
//            QueueingConsumer consumer = new QueueingConsumer(channel);
//            指定消费队列
            channel.basicConsume(QUEUE_NAME, true, consumer);
            /*while (true){
//                nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("Received the message : " + message );
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


