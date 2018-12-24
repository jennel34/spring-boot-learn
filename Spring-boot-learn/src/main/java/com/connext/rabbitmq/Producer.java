package com.connext.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
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
            channel.txSelect();
            for(int i=0;i<10;i++){
                String message2 = message+i;
                channel.basicPublish("",QUEUE_NAME,null,message2.getBytes("UTF-8"));

                System.out.println("Send the message : "+message2);
            }

//            int i = 1/0;
            channel.txCommit();
        }catch (Exception e){
            try {
                channel.txRollback();
            }catch (Exception e1){
                e1.printStackTrace();
            }
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

