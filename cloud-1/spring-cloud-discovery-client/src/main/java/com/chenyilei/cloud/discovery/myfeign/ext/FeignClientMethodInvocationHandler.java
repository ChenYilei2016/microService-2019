package com.chenyilei.cloud.discovery.myfeign.ext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 将接口的方法映射成远程调用
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/09- 20:15
 */
public class FeignClientMethodInvocationHandler implements InvocationHandler {

    private BeanFactory beanFactory;
    private String serviceName;
    private static final ParameterNameDiscoverer parameterNameDiscoverer;

    static {
        parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    }

    public FeignClientMethodInvocationHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1 取得调用的restTemplate
        RestTemplate myRestTemplate = (RestTemplate) beanFactory.getBean("myRestTemplate");
        StringBuilder invokeUrl = new StringBuilder();
        invokeUrl.append("http://");
        invokeUrl.append(serviceName);
        //2 解析method,对参数,返回值等处理,拼接成url,请求.
        //@requestMapping/@getMapping ;
        RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
        if (null != requestMapping) {
            String requestPath = requestMapping.value()[0];//TODO: 硬编码取 请求的一个地址
            invokeUrl.append(requestPath);
            invokeUrl.append("/?");

            //对参数解析 @requestParam,拼串
            Parameter[] parameters = method.getParameters();
            StringBuilder parametersQueryBuilding = new StringBuilder();
            if (null != parameters) {
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    //有无@requestParam
                    RequestParam requestParam = parameter.getDeclaredAnnotation(RequestParam.class);
                    parametersQueryBuilding.append("&");
                    if (null == requestParam) {
                        //形参名?
                        parametersQueryBuilding.append(parameterNameDiscoverer.getParameterNames(method)[i]);
                    } else {
                        parametersQueryBuilding.append(requestParam.value());
                    }
                    parametersQueryBuilding.append("=");
                    parametersQueryBuilding.append(args[i]);
                }
            }
            invokeUrl.append(parametersQueryBuilding.toString());
            return myRestTemplate.getForObject(invokeUrl.toString(), method.getReturnType());
        }
        return method.invoke(args);
    }

}
