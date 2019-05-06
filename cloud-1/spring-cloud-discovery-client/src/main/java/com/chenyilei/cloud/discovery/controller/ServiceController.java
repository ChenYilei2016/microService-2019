package com.chenyilei.cloud.discovery.controller;

import com.chenyilei.cloud.discovery.config.MyLoadBalanceAutoConifg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/29- 15:14
 */
@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    @MyLoadBalanceAutoConifg.MyLoadBalanced_
    RestTemplate restTemplate;

    private final static Random RANDOM = new Random();

    //    private volatile Set<String> set = new ConcurrentSkipListSet<>();
//    private Set set2 = new CopyOnWriteArraySet(); //应付并发
//    Set oldSet = set;
//    Set newSet = new HashSet();
//    newSet.addAll(discoveryClient.getServices());
//    set = newSet;
//    oldSet.clear();

    //调用这个服务: spring-cloud-discovery-provider
    @GetMapping(value = "/invoke/{serviceName}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object testLoadBalance(@PathVariable("serviceName")String serviceName){
        //访问注册中心的服务
//        Set<String> instances = serviceName_Urls_Map.get(serviceName);
//        String targetUrl = instances.parallelStream().findAny().get()+"/test";
//        String forObject = restTemplate.getForObject(targetUrl, String.class);
        String forObject = restTemplate.getForObject(serviceName, String.class);
        return forObject;
    }

    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object get(){
        //return loadBalancerClient.choose(discoveryClient.getServices().get(0));
        return "调用了client的方法port:8080";
    }

}
