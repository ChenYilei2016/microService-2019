package com.chenyilei.testm;

import com.chenyilei.testm.controller.MController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/19- 14:14
 */

@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
public class Mtest implements InitializingBean , ApplicationContextAware {
    private ApplicationContext context;



    public static void main(String[] args) {
        new SpringApplicationBuilder(Mtest.class)
                .run(args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AnnotationConfigServletWebServerApplicationContext context = (AnnotationConfigServletWebServerApplicationContext) this.context;
        context.register(MController.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context  = applicationContext;
    }
}
