package com.chenyilei.microparent.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/20- 15:56
 */
@MyComponent(text = "10")
@MyComponent(text ="20")
@Configuration
public class My implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("setsetsetset");
    }

    public static void main(String[] args) {
        MyComponents annotation = My.class.getAnnotation(MyComponents.class);
        System.out.println(annotation);
    }

    @Bean
    public String a(){
        System.err.println("adf");
        return "123";
    }
}
