package com.chenyilei.cloudnative;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.ParentContextApplicationContextInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * SpringBoot设置父 上下文
 * <p>
 * SpringCloud为什么能远程获取配置?[实际上也创建了一个上下文,并获取properties,作为父上下文,子可以用它的Bean]
 * {@link org.springframework.cloud.bootstrap.BootstrapApplicationListener}创建bootstrap上下文
 * 设置了{@link ParentContextApplicationContextInitializer} 在子上下文进行初始化设置
 * <p>
 * 整体使用{@link Environment} 作为环境配置列表[application.yml,bootstrap.yml弄成配置源],
 * 配置源间有优先级,因此可以覆盖
 * <p>
 * {@code Spring Boot Actuator Endpoints}进行运维查看操作
 * [/actuator/env, mappings, beans, ]
 * <p>
 * {@link org.springframework.context.LifeCycle}
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/26- 15:28
 */
@SpringBootApplication
public class App1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean("123", String.class, new String("111111"));
        applicationContext.refresh();

        ConfigurableApplicationContext run = new SpringApplicationBuilder(App1.class)
                .parent(applicationContext)
//                .web(WebApplicationType.NONE)
                .run(args);

        //子可以用父
        System.err.println(run.getBean("123"));
    }
}
