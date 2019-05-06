package com.chenyilei.springwebflux;

import com.chenyilei.springwebflux.controller.MyLoad;
import com.chenyilei.springwebflux.controller.R1;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * 自己总结:
 * 同步 和 异步  => 调用时机 和 返回时间 [执行就调用,并且直到执行完成返回是同步] [不知道什么时候调用,调用完成不知道什么时候返回结果是异步]
 * 阻塞 和 非阻塞 => 执行过程 [一个线程执行到底是同步] [操作系统控制间断执行是异步]
 *
 * 使用{@link HandlerInterceptor} 来处理异常
 */
@SpringBootApplication
public class SpringWebfluxApplication {

    @Bean
    @MyLoad
    public String myString(){
        return "11";
    }

    @Bean
    public String myString2(){
        return "22";
    }

    @Autowired
    private R1 r1;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(SpringWebfluxApplication.class, args);
        Field a = SpringWebfluxApplication.class.getDeclaredField("a");
        System.out.println(Arrays.asList(a.getAnnotations()));


        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
//        multicaster.setTaskExecutor(new ScheduledThreadPoolExecutor(1));

        //CompletableFuture completableFuture = new CompletableFuture();
        var result = CompletableFuture.supplyAsync(() -> {
            System.out.println("1");
            return 15;
        }).thenAccept(System.out::println);

        System.out.println(result);

        Stream.of(1,3,9,8,5,6,4)
                .filter(x->x>5)
                .sorted()
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
               // .forEach(System.out::println);
// webFlux
        Flux.just(8,9,1,3,4,6,5)
                .filter(x->x>5)
                .subscribeOn(Schedulers.newElastic("另一个elastic"))
                .subscribe(x->{
                    System.out.println(Thread.currentThread().getName()+"__"+x);
                })
                ;

        AnnotatedElementUtils.hasAnnotation(String.class, Controller.class);

    }


}
