package com.connext.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class ProPubSub {
    private final static String EXCHANGE_NAME = "TestEx";
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
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
            channel.confirmSelect();
            Set<Integer> set = new LinkedHashSet<>();
            /*Random random = new Random();
            while (set.size() < 10){
                set.add(random.nextInt(10)+1);
            }*/
            set.add(20);set.add(1);set.add(2);set.add(3);set.add(4);
            for (Integer i : set) {
                String dots = "";
                for (int j = 0; j < i; j++) {
                    dots += ".";
                }
                String message = "Test Message"+dots+dots.length();
                channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
            }
            if(channel.waitForConfirms()){
                    System.out.println("success");
            }
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

