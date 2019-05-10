package com.chenyilei.cloud.discovery.controller;

import com.chenyilei.cloud.discovery.config.MyLoadBalanceAutoConifg;
import com.chenyilei.cloud.discovery.myfeign.MyRestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/29- 15:14
 */
@RestController
public class ClientServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    @MyLoadBalanceAutoConifg.MyLoadBalanced_
    RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    RestTemplate ribbonRestTemplate;

    @Autowired
    InvokingServerService feignClient_;

    @Autowired
    MyRestInterface myRestInterface;

    private final static Random RANDOM = new Random();

    //    private volatile Set<String> set = new ConcurrentSkipListSet<>();
//    private Set set2 = new CopyOnWriteArraySet(); //应付并发
//    Set oldSet = set;
//    Set newSet = new HashSet();
//    newSet.addAll(discoveryClient.getServices());
//    set = newSet;
//    oldSet.clear();

    //调用这个服务: spring-cloud-discovery-provider
    //http://127.0.0.1:8080/invoke/spring-cloud-discovery-provider
    @GetMapping(value = "/invoke/{serviceName}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object testLoadBalance(@PathVariable("serviceName")String serviceName){
        //访问注册中心的服务
//        Set<String> instances = serviceName_Urls_Map.get(serviceName);
//        String targetUrl = instances.parallelStream().findAny().get()+"/test";
//        String forObject = restTemplate.getForObject(targetUrl, String.class);
        String forObject = restTemplate.getForObject("http://"+serviceName+"/test?msg=ok", String.class);
        return forObject;
    }

    @GetMapping(value = "/feign")
    @ResponseBody
    public Object feignInvoke(){
        return feignClient_.test("ok1");
    }

    /**
     * 调用我自己实现feign类
     * @return
     */
    @GetMapping(value = "/myfeign")
    public Object myFeignInvoke(){
        return myRestInterface.test("ok1");
    }

    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getRibbonMsg(){
        //return loadBalancerClient.choose(discoveryClient.getServices().get(0));
        return ribbonRestTemplate.getForObject("http://spring-cloud-discovery-provider/test?msg=ok", String.class);
        //return "调用了client的方法port:8080";
    }

}
