package com.chenyilei.testm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/19- 14:48
 */
@RestController
public class MController {
    @RequestMapping("/hello")
    public String hello(String msg){
        return "hello ok";
    }

    @Autowired
    @Output(Source.OUTPUT)
    public MessageChannel messageChannel;

    @RequestMapping("/send")
    public String send(String msg){
        messageChannel.send(new Message<Object>() {
            @Override
            public Object getPayload() {
                return msg;
            }

            @Override
            public MessageHeaders getHeaders() {
                return new MessageHeaders(new HashMap<>());
            }
        });
        return "send ok";
    }

    @StreamListener("input")
    public void listener(String msg){
        System.out.println("cloud bus:"+msg);
    }
}
