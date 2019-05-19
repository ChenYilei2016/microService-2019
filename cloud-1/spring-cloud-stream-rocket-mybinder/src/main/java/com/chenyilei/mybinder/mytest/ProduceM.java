package com.chenyilei.mybinder.mytest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/18- 11:48
 */
public class ProduceM {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        byte[] messageBodyBytes = "Hello, world!".getBytes();
        channel.basicPublish("test2018", "testm", null, messageBodyBytes);

        channel.close();
        conn.close();
    }
}
