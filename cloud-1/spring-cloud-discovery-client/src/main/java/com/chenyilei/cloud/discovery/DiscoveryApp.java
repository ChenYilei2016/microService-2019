package com.chenyilei.cloud.discovery;

import com.chenyilei.cloud.discovery.config.MyLoadBalanceAutoConifg;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.net.Authenticator;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;

/**
 * 描述自己的媒体类型{@link org.springframework.http.MediaType} text/html
 *
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/04/29- 13:48
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class DiscoveryApp {
    public static void main(String[] args) throws Exception{
        new SpringApplicationBuilder(DiscoveryApp.class)
                .run(args);
        String[] split1 = StringUtils.split("/dfa/dasd/", "/");
        String[] split2 = "/dfa/dasd/".split("/");
        System.out.println(Arrays.asList(split1));
        System.out.println(Arrays.asList(split2));
        URL u = new URL("http://www.baidu.com:8080/say/o?msg=ok&msg2=haha");
        System.out.println(u.getProtocol());
        System.out.println(u.getPath());
        System.out.println(u.getQuery());


        URI uri = u.toURI();
        System.out.println(uri.getHost() );
        System.out.println(uri.getPath() );
        System.out.println(uri.getPort() );
        System.out.println(uri.getQuery() );


        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
                //.authenticator(Authenticator.getDefault())
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    @MyLoadBalanceAutoConifg.MyLoadBalanced_
    public RestTemplate myRestTemplate(){
        return new RestTemplate();
    }
}
