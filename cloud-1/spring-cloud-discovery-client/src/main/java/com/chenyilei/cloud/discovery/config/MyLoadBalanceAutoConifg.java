package com.chenyilei.cloud.discovery.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.annotation.*;
import java.nio.charset.Charset;
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
    @LoadBalanced
    public RestTemplate restTemplate2;

    @Autowired
    MyLoadInterceptor myLoadInterceptor;

    @PostConstruct
    public void init() {
        new AtomicInteger();
        restTemplate.setInterceptors(Arrays.asList(myLoadInterceptor));
    }

}


