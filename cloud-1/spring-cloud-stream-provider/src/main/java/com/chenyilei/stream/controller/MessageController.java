package com.chenyilei.stream.controller;

import com.chenyilei.stream.ext.MyOutput;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/13- 18:40
 */
@RestController
public class MessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Autowired
    MyOutput myOutput;

    @GetMapping("/test")
    public void test(){
        Message message = new Message("1".getBytes(),new MessageProperties());
        rabbitTemplate.send("cyl.test","test",message);

    }
    @GetMapping("/stream/send")
    public Object streamSend(){
        boolean nihao = myOutput.gupao().send(new GenericMessage<>("nihao"));
        return nihao;
    }

}
