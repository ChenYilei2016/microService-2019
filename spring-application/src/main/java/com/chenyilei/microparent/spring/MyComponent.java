package com.chenyilei.microparent.spring;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/20- 15:56
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyComponents.class)
public @interface MyComponent {
    String text() ;
}
