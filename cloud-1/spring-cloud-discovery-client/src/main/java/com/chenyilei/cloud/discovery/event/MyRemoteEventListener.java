package com.chenyilei.cloud.discovery.event;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link SmartApplicationListener} 监听多个事件
 * 为了单一应用, context 可以从事件中取 {@link org.springframework.context.event.ContextRefreshedEvent}
 * ,因为可能不是一个springBean
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/15- 19:30
 */
public class MyRemoteEventListener implements SmartApplicationListener{
    private RestTemplate restTemplate = new RestTemplate();
    private DiscoveryClient discoveryClient ;
    private String myAppName;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return MyRemoteEvent.class.isAssignableFrom(eventType)||
                ContextRefreshedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof  MyRemoteEvent){
            onMyRemoteEvent(event);
        }else if(event instanceof  ContextRefreshedEvent){
            onContextRefreshedEvent(event);
        }
    }

    private void onContextRefreshedEvent(ApplicationEvent event) {
        ContextRefreshedEvent contextRefreshedEvent = (ContextRefreshedEvent) event;
        discoveryClient = contextRefreshedEvent.getApplicationContext().getBean(DiscoveryClient.class);
        //获得自己的spring.application.name
        myAppName = "my-client";//contextRefreshedEvent.getApplicationContext().getEnvironment().getProperty("spring.application.name");
    }

    private void onMyRemoteEvent(ApplicationEvent event) {
        MyRemoteEvent myRemoteEvent = (MyRemoteEvent) event;
        List<ServiceInstance> instances = discoveryClient.getInstances(myRemoteEvent.getAppName());
        ServiceInstance serviceInstance = null;

        if(instances != null && instances.size() > 0)
            serviceInstance = instances.get(0);
        else{
            //假数据
            serviceInstance = new DefaultServiceInstance(myRemoteEvent.getAppName(),"127.0.0.1",9090,false);
        }

        //向服务端发送一个事件!
        String requestUrl =
                serviceInstance.isSecure() ? "https" : "http"
                + "://"
                + serviceInstance.getHost()
                + ":" + serviceInstance.getPort()
                + "/receiveEvent?msg={msg}";
//                + serviceInstance.getUri().getPath()
//                + "?"
//                + serviceInstance.getUri().getQuery();
        Map data = new HashMap();
        data.put("msg","hello world");
        String forObject = restTemplate.getForObject(requestUrl, String.class, data);

        System.err.println("发送事件成功接收返回!:"+forObject);
    }


}
