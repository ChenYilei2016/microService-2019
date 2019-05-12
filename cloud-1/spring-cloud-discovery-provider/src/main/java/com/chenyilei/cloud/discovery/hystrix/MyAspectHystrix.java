package com.chenyilei.cloud.discovery.hystrix;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * AOP 环绕拦截，进行超时熔断
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/05- 20:41
 */
@Aspect
@Component
public class MyAspectHystrix {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    private volatile Semaphore semaphore = null;

    @Around("execution(* com.chenyilei.cloud.discovery.controller..*..*(..))&&" +
            "@annotation(myHystrixCommond)")
    public Object checkTimeOut(ProceedingJoinPoint proceedingJoinPoint
            , MyHystrixCommond myHystrixCommond) throws Exception {

        //可以用信号量 作限流锁
        if (semaphore == null) {
            synchronized (MyHystrixCommond.class) {
                if (semaphore == null) {
                    semaphore = new Semaphore(1);
                    boolean b = semaphore.tryAcquire(1000, TimeUnit.MILLISECONDS);//根据b来熔断
                    semaphore.release();
                }
            }
        }


        System.out.println("checkTimeOut拦截" + myHystrixCommond);
        if (proceedingJoinPoint instanceof MethodInvocationProceedingJoinPoint) {
            MethodInvocationProceedingJoinPoint joinPoint =
                    (MethodInvocationProceedingJoinPoint) proceedingJoinPoint;
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            MyHystrixCommond annotation = method.getAnnotation(MyHystrixCommond.class);
        }

        Future<?> submit = executorService.submit(() -> {
            Object returnValue = null;
            try {
                returnValue = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return returnValue;
        });

        Object returnValue = null;
        try {
            returnValue = submit.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            submit.cancel(true);
            returnValue = "超时了";
        }
        return returnValue;
    }

    @PreDestroy
    public void destory() {
        executorService.shutdown();
    }
}
