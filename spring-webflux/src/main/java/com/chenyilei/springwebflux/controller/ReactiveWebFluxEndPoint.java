package com.chenyilei.springwebflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/12- 08:33
 */
@Configuration
public class ReactiveWebFluxEndPoint {

    @Bean
    public RouterFunction<ServerResponse> helloWorld(){
        return route( GET("test"),request -> {
            return ServerResponse.ok().body(Mono.just("hello world"),String.class);
        } );
    }
}
