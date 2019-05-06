package com.chenyilei.mvcspring.controller;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.validation.OverridesAttribute;
import java.lang.annotation.*;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/22- 19:05
 */


@MTest.MyAno2("我草")
@Component
public class MTest {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface MyAno{
        String value() default "nihao111";
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @MyAno
    static @interface MyAno2{
        @AliasFor(annotation = MyAno.class,attribute = "value")
        String value() default "";

    }

    public static void main(String[] args) {
        MTest mTest = new MTest();
        MyAno annotation = AnnotationUtils.findAnnotation(mTest.getClass(), MyAno.class);

        System.out.println(annotation);

        Annotation[] annotations = MTest.class.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(i+":"+annotations[i]);
        }
    }
}
