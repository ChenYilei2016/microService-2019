package com.chenyilei.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * ${label}分支
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/27- 15:28
 */
@SpringBootApplication
public class ConfigClientApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ConfigClientApp.class)
                .run(args);

        System.out.println(applicationContext);
    }

}
