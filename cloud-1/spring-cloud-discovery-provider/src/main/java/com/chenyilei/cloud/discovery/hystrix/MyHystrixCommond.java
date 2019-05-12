package com.chenyilei.cloud.discovery.hystrix;

import java.lang.annotation.*;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/05- 18:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyHystrixCommond {
    int timeout() default -1;

    int semphore() default 1;

    String text() default "";
}
