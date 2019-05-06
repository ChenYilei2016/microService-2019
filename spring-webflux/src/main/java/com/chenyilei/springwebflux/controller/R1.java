package com.chenyilei.springwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/24- 16:40
 */
@RestController
public class R1 {

    @Autowired
    @MyLoad
    String myString;

    @RequestMapping("/test")
    public Mono<String> test(){
        return Mono.just("nihao");
    }
}
