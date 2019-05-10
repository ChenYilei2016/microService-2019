package com.chenyilei.cloud;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.config.ConfigServerAutoConfiguration;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.MultipleJGitEnvironmentProperties;
import org.springframework.cloud.config.server.environment.MultipleJGitEnvironmentRepository;
import org.springframework.cloud.config.server.environment.MultipleJGitEnvironmentRepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * git : 复杂的版本更新!
 * {@link EnableConfigServer}
 * {@link ConfigServerAutoConfiguration}
 * => {@link org.springframework.cloud.config.server.environment.EnvironmentController}
 * => {@link EnvironmentRepository}进行远程配置的获取操作
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/27- 16:23
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerApp.class)
                .run(args);
        String a= "f";
        System.out.println(String.class == a.getClass());
    }

    @Component
    @Endpoint(id="myEnd")
    static class MyEndPoint{
        @ReadOperation
        public String myEnd(){
            return "123";
        }
    }

    //, search = SearchStrategy.CURRENT
    @Configuration
    //@see EnableConfigServer
//    @ConditionalOnMissingBean(value = EnvironmentRepository.class)
    static class MyRepositoryConfiguration {
        @Bean
        public EnvironmentRepository myEnvironmentRepository(
                MultipleJGitEnvironmentRepositoryFactory gitEnvironmentRepositoryFactory,
                MultipleJGitEnvironmentProperties environmentProperties) throws Exception {
            return new EnvironmentRepository() {
                @Override
                public Environment findOne(String application, String profile, String label) {
                    Environment environment = new Environment(application,new String[]{profile},label,null,null);
                    HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                    PropertySource propertySource = new PropertySource("mySource",objectObjectHashMap);
                    environment.add(propertySource);
                    objectObjectHashMap.put("name","自己配置资源");
                    environment.setLabel("master");
                    environment.setProfiles(new String[]{"dev"});
                    environment.setName("config");
                    environment.setState("stateOK");
                    environment.setVersion("1.0.0");
                    return environment;
                }
            };
        }
    }
}
