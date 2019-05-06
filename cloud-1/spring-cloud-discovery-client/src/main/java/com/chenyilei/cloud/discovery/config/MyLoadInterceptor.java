package com.chenyilei.cloud.discovery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.AbstractClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/03- 13:10
 */
//        Set<String> instances = serviceName_Urls_Map.get(serviceName);
//        String targetUrl = instances.parallelStream().findAny().get()+"/test";
//        String forObject = restTemplate.getForObject(targetUrl, String.class);
@Component
public class MyLoadInterceptor implements ClientHttpRequestInterceptor {
    @Autowired
    DiscoveryClient discoveryClient;
    //服务对应的 url列表
    static volatile Map<String, Set<String>> serviceName_Urls_Map = new ConcurrentHashMap<String,Set<String>>();
    @Scheduled(cron = "0/10 * * * * ?") //定时更新服务列表
    public void updateService(){
        Map oldMap = serviceName_Urls_Map;
        Map<String,Set<String>> newMap = new ConcurrentHashMap<String,Set<String>>();
        discoveryClient.getServices().stream()
                .forEach(serviceName->{
                    //将每一个serviceName 映射成一个服务端url地址的Set
                    Set<String> collect = discoveryClient.getInstances(serviceName).stream().map(serviceInstance -> {
                        serviceInstance.isSecure();//这个可以判断是否是http/https
                        return "http://127.0.0.1" + ":" + serviceInstance.getPort();
                    }).collect(Collectors.toSet());
                    newMap.put(serviceName,collect);
                });
        serviceName_Urls_Map = newMap;
        oldMap.clear();
        System.out.println("执行成功[updateService]:"+serviceName_Urls_Map);
    }

    //http://spring-cloud-discovery-provider/test?msg=1561 ; 对应这样的请求
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        request.getURI().getQuery()
//        得到request的Uri
        System.err.println("request:"+request);
        URI uri = request.getURI();
        uri.getHost() ;uri.getPath(); uri.getPort(); uri.getQuery();
//      www.baidu.com ; /abd/       ; 80           ; msg=ok...
        System.err.println(new String(body));
        System.err.println(uri.getHost() );
        System.err.println(uri.getPath() );
        System.err.println(uri.getPort() );
        System.err.println(uri.getQuery() );

        String targetUrl = serviceName_Urls_Map.get(uri.getPath().substring(1)).stream().findAny().get();
        String reallyUrl = targetUrl+"/test";
        System.out.println("reallyUrl = "+ reallyUrl);
        URL url = new URL(reallyUrl);
        URLConnection urlConnection = url.openConnection();

        MyResponse myResponse = new MyResponse(urlConnection);
        return myResponse;
    }
}
