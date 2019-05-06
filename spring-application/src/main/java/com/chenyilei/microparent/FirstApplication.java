package com.chenyilei.microparent;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 使用注解的spring容器 @since 3.0
 * {@link org.springframework.context.annotation.AnnotationConfigApplicationContext}
 * @see {@link org.springframework.context.support.ClassPathXmlApplicationContext}
 * 都是寻找bean的定义,并加入beanFactory {@link org.springframework.beans.factory.config.BeanDefinition}
 *
 *
 * {@code org.springframework.context.annotation.ConfigurationClassParser}
 * {@link org.springframework.stereotype.Component}派生性(不能继承,逻辑上的派生)
 *
 *
 * SpringBoot 事件 {@link ApplicationListener 消费者}
 * {@link org.springframework.context.event.ContextRefreshedEvent 消息}
 * {@link org.springframework.context.event.ContextClosedEvent} ......
 * {@link org.springframework.context.PayloadApplicationEvent}乱七八糟都被这个弄成事件
 * {@link SimpleApplicationEventMulticaster 消息提供者}
 *
 *
 * 对application.properties / yml 的处理监听器
 * {@link org.springframework.boot.context.config.ConfigFileApplicationListener}
 *
 *
 * 不用JSP ? 不想用j2EE的一套
 * 在SpringMvc{@link org.springframework.web.servlet.DispatcherServlet}中渲染
 * {@link org.springframework.web.context.WebApplicationContext}
 *
 */
@Component
class MyListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.err.println("我的refresh方法调用");
    }
}
@SpringBootApplication
public class FirstApplication {
    @Bean
    public String 主类的bean(){
        System.err.println("朱磊bean");
        return "12";
    }

    public static void main(String[] args) {//
        System.out.println(System.getProperty("java.version"));
        //SpringApplication.run(MicroParentApplication.class, args);

        //fluent流畅api
        ConfigurableApplicationContext applicationContext
                = new SpringApplicationBuilder(FirstApplication.class)
                .properties("server.port=8080")
                .bannerMode(Banner.Mode.OFF)
                .listeners(new ApplicationListener<ApplicationEvent>() { //增加一个springboot的启动监听器
                    @Override
                    public void onApplicationEvent(ApplicationEvent event) {
                        System.err.println(event.getClass().getName());
                    }
                })
                .web(WebApplicationType.SERVLET) //没有启动tomcat,AnnotationConfigApplicationContext
                .run(args);

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(FirstApplication.class);
//        applicationContext.refresh();

        //可以找到带有注解的bean
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Component.class);
        System.out.println(beansWithAnnotation.get("my"));

        applicationContext.publishEvent(new ApplicationEvent("自定义事件") {});

        //applicationContext.close();

    }

}
