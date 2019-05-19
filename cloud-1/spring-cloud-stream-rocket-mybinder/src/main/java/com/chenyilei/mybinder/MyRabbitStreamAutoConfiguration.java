package com.chenyilei.mybinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rocketmq 装了麻烦, 还有些小问题, 用rabbitmq 原理都是一样的,
 * 使用springcloud 的stream 作为入口,实际调用的是binder和mq交互
 *
 * {@link org.springframework.cloud.stream.annotation.Output} {@link org.springframework.cloud.stream.messaging.Source}
 * {@link org.springframework.cloud.stream.annotation.Input} {@link org.springframework.cloud.stream.messaging.Sink}
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/19- 14:07
 */
@Configuration
public class MyRabbitStreamAutoConfiguration {

    @Bean
    public MyMessageChannelBinder myMessageChannelBinder(){
        return new MyMessageChannelBinder();
    }
}
