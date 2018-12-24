package com.connext.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProConfirm {
    private final static String QUEUE_NAME = "TestQueue";
    public static void main(String[] args) {
        send();
    }

    public static void send(){
        ConnectionFactory factory = null;
        Connection connection = null;
        Channel channel = null;
        try{
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "Test Message";
            channel.confirmSelect();
            final long start = System.currentTimeMillis();
            for (int i = 0; i < 50; i++) {
                channel.basicPublish("",QUEUE_NAME,null,((i+1)+message).getBytes("UTF-8"));
                /*if(channel.waitForConfirms()){
                    System.out.println((i+1)+"success");
                }*/
            }
            if(channel.waitForConfirms()){
                    System.out.println("success");
            }
            System.out.println("执行waitForConfirmsOrDie耗费时间: "+(System.currentTimeMillis()-start)+"ms");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                channel.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

