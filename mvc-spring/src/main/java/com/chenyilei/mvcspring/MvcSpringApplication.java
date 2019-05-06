package com.chenyilei.mvcspring;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link org.springframework.http.HttpStatus} 状态码
 *
 * {@link org.springframework.http.HttpHeaders} 多值map,一个key对应多个value
 *
 * {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}
   {@link org.springframework.http.MediaType}
 */

@ComponentScan
@EnableAutoConfiguration
public class MvcSpringApplication {

    public static void main(String[] args) {
        //
        ConfigurableApplicationContext app =
        new SpringApplicationBuilder(MvcSpringApplication.class)
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

    }

}
