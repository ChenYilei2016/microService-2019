package com.chenyilei.cloud.discovery.myfeign;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 1 importbeandefin...
 * 2 import
 * 3 importSelect
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/09- 14:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyFeignRegistry.class)
public @interface EnableMyFeign {
    Class<?>[] clients() default {}; //代理谁
    String[] scanPackages() default{}; //扫描路径
}
