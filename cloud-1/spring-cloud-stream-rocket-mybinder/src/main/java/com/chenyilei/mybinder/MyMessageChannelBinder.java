package com.chenyilei.mybinder;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.chenyilei.mybinder.utils.MyConnUtil;
import com.rabbitmq.client.*;
import com.sun.tools.javac.main.JavaCompiler;
import org.springframework.cloud.stream.binder.*;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Indexed;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * TODO: stream binder 实现
 * spring cloud stream 实际进行传信息
 * 用户操作 streamApi -> binder -> mq
 *
 * 发送消息: MessageChannel -> binder -> 发送到mq
 * 接受消息: binder接受消息 -> MessageChannel
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/18- 12:45
 */
public class MyMessageChannelBinder implements Binder<MessageChannel,ConsumerProperties, ProducerProperties> {

    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel messageChannel
            , ConsumerProperties consumerProperties) {

        Connection connection = MyConnUtil.getConnection();
        try {
            Channel rabbitchannel = connection.createChannel();
            rabbitchannel.basicConsume("testQueue",true,new DefaultConsumer(rabbitchannel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties proerties, byte[] body) throws IOException {
                    messageChannel.send(new GenericMessage<>(body));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return () -> {
            System.err.println("consumer 接受成功!");
        };
    }

    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel messageChannel,
                                                    ProducerProperties producerProperties) {

        try {
            Connection conn = MyConnUtil.getConnection();
            Channel channel = conn.createChannel();

//            byte[] messageBodyBytes = "Hello, world!".getBytes();
//            channel.basicPublish("test2018", "testm", null, messageBodyBytes);
            SubscribableChannel subscribableChannel = (SubscribableChannel) messageChannel;
            subscribableChannel.subscribe(message -> {
                try {
                    channel.basicPublish("cloudbus", "testm", null, mySerializable(message.getPayload()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }catch (Exception e){

        }

        return () -> {
            System.err.println("producer 发送成功!");
        };
    }

    private byte[] mySerializable(Object payload) throws IOException {
        if(payload instanceof byte[]){
            return (byte[]) payload;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(payload);
        return baos.toByteArray();
    }


}
