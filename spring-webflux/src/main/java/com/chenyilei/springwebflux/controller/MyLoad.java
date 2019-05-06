package com.chenyilei.springwebflux.controller;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/25- 18:36
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface MyLoad {
}
