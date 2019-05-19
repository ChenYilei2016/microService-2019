package com.chenyilei.mybinder.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/19- 13:39
 */
public class MyConnUtil {
    public static final String Username = "guest";
    public static final String Password = "guest";
    public static final String VirtualHost = "/";
    public static final String Host = "localhost";
    public static final Integer Port = 5672;

    private static ConnectionFactory factory ;
    static {
        factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
    }

    public static Connection getConnection(){
        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

}
