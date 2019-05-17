package com.chenyilei.cloud.discovery.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * JAVA_CLIENT -> eventListener ->|  MQ  |->  MQListener -> eventListener
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/15- 15:37
 */
@RestController
public class MyEventListener_Controller implements ApplicationEventPublisherAware , ApplicationContextAware {
    @Autowired
    DiscoveryClient discoveryClient;
    /**
     * 事件 event
     */
    protected ApplicationEventPublisher publisher;

    //spring-cloud-discovery-provider
    @GetMapping("/send/remote/event/{serviceName}")
    public String event(@PathVariable("serviceName") String serviceName){
        MyRemoteEvent myRemoteEvent = new MyRemoteEvent("nihao",serviceName,true);
        publisher.publishEvent(myRemoteEvent);
        return "nihao";
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext instanceof AnnotationConfigServletWebServerApplicationContext){
            AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) applicationContext;
            ctx.addApplicationListener(new MyRemoteEventListener());
        }
    }
}
