package com.chenyilei.cloud.discovery;

import com.chenyilei.cloud.discovery.config.MyLoadBalanceAutoConifg;
import com.chenyilei.cloud.discovery.controller.InvokingServerService;
import com.chenyilei.cloud.discovery.myfeign.EnableMyFeign;
import com.chenyilei.cloud.discovery.myfeign.MyFeignRegistry;
import com.chenyilei.cloud.discovery.myfeign.MyRestInterface;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.zookeeper.ZookeeperAutoConfiguration;
import org.springframework.cloud.zookeeper.discovery.ZookeeperDiscoveryAutoConfiguration;
import org.springframework.cloud.zookeeper.discovery.ZookeeperDiscoveryClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 描述自己的媒体类型{@link org.springframework.http.MediaType} text/html
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/29- 13:48
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(clients = InvokingServerService.class)
/**
 * 实现feign流程,导入引导类
 */
@EnableMyFeign(clients = MyRestInterface.class)
@EnableBinding(Source.class)
public class DiscoveryApp implements ApplicationContextAware {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(DiscoveryApp.class)
                .run(args);
//        String[] split1 = StringUtils.split("/dfa/dasd/", "/");
//        String[] split2 = "/dfa/dasd/".split("/");
//        System.out.println(Arrays.asList(split1));
//        System.out.println(Arrays.asList(split2));
//        URL u = new URL("http://www.baidu.com:8080/say/o?msg=ok&msg2=haha");
//        System.out.println(u.getProtocol());
//        System.out.println(u.getPath());
//        System.out.println(u.getQuery());
//
//
//        URI uri = u.toURI();
//        System.out.println(uri.getHost() );
//        System.out.println(uri.getPath() );
//        System.out.println(uri.getPort() );
//        System.out.println(uri.getQuery() );
//
//
//        HttpClient client = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .followRedirects(HttpClient.Redirect.NORMAL)
//                .connectTimeout(Duration.ofSeconds(20))
//                //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
//                //.authenticator(Authenticator.getDefault())
//                .build();
//        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
//        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        //((AnnotationConfigServletWebServerApplicationContext) context).getBeanDefinition("myRestTemplate")

//        BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getBeanFactory(),RestTemplate.class,"haha");
//
//        InvokingServerService.class.getMethod("test", String.class).getAnnotationsByType(RequestParam.class);
//        InvokingServerService.class.getMethod("test", String.class).getParameters()[0].getDeclaredAnnotations();
//
//        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
//        String[] tests = parameterNameDiscoverer.getParameterNames(MyRestInterface.class.getMethod("test", String.class));
//        Stream.of(tests).forEach(System.out::println);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("!");
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    @MyLoadBalanceAutoConifg.MyLoadBalanced_
    public RestTemplate myRestTemplate() {
        return new RestTemplate();
    }
}
