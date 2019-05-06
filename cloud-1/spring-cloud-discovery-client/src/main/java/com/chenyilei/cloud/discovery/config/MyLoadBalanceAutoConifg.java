package com.chenyilei.cloud.discovery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/02- 22:11
 */
@Configuration
public class MyLoadBalanceAutoConifg {

    @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @Qualifier
    public @interface MyLoadBalanced_{

    }

    public MyLoadBalanceAutoConifg(@Autowired @MyLoadBalanced_ List<RestTemplate> restTemplates){
        System.out.println(restTemplates);
    }

    @Autowired
    @MyLoadBalanced_
    public RestTemplate restTemplate;


    @Autowired
    MyLoadInterceptor myLoadInterceptor;

    @PostConstruct
    public void init() {
        new AtomicInteger();
        restTemplate.setInterceptors(Arrays.asList(myLoadInterceptor));
    }

}


