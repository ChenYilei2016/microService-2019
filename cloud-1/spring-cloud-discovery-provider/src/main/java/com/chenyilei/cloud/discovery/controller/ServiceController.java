package com.chenyilei.cloud.discovery.controller;

import com.chenyilei.cloud.discovery.hystrix.MyHystrixCommond;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

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
    RestTemplate restTemplate;

    Random random = new Random();

    ExecutorService executor = Executors.newFixedThreadPool(5);

    @Value("${server.port}")
    int mport;

    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @HystrixCommand(
//            fallbackMethod = "testFailBack"
//            ,
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds"
//                            ,value = "100")
//            }
//    )
    @MyHystrixCommond
    public Object test() throws InterruptedException, TimeoutException, ExecutionException {
        int i = random.nextInt(200);
        Future<?> submit = executor.submit(() -> {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        submit.get(100,TimeUnit.MILLISECONDS);

        System.err.println("test结尾");
        return "你调用了provider的方法port:"+mport+" 时间: "+i;


    }

    //要保持相同签名 参数，返回值等
    public Object testFailBack(){
        System.err.println("调用了熔断");
        return "熔断信息";
    }
}
