package com.chenyilei.stream;

import com.chenyilei.stream.ext.MyInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import javax.annotation.PostConstruct;

/**
 * Source 发送端 {@link org.springframework.cloud.stream.messaging.Source}
 * Processor 发送+接受[中间]
 * Sink 接收端 {@link org.springframework.cloud.stream.messaging.Sink}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/12- 19:42
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(MyInput.class)
public class StreamInputMain {
    public static void main(String[] args) {
        SpringApplication.run(StreamInputMain.class,args);
    }

    @Autowired
    MyInput myInput;

    @PostConstruct
    public void init(){
        myInput.gupao().subscribe(message -> {
            System.out.println("接受:"+message.getPayload());
        });
    }
}
