package com.chenyilei.cloud.discovery.event;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/17- 15:40
 */
@RestController
public class EventController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    //接收到消息,进行发放消息
    @GetMapping("/receiveEvent")
    public String receiveEvent(@RequestParam Map data){
        Object msg = data.get("msg");
        MyRemoteEvent myRemoteEvent = new MyRemoteEvent("server的传播事件","server",false);
        System.err.println(Thread.currentThread().getName());

        eventPublisher.publishEvent(myRemoteEvent);
        return JSONObject.toJSONString(data);
    }

    @Component
    static class RemoteEventListenr implements ApplicationListener<MyRemoteEvent> {
        @Override
        @Async
        public void onApplicationEvent(MyRemoteEvent event) {
            //
            System.err.println(Thread.currentThread().getName()+"服务端接收到客户端事件!"+event.getSource());
        }
    }

}
