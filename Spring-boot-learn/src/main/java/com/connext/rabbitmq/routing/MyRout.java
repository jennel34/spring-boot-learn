package com.connext.rabbitmq.routing;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyRout extends DefaultConsumer {
    private Channel channel;
    public MyRout(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println("Receive the message : "+message);
        System.out.println("Finished");
//        另外需要在每次处理完成一个消息后，手动发送一次应答。
        channel.basicAck(envelope.getDeliveryTag(), true);
    }
}