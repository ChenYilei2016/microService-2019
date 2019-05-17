package com.chenyilei.cloud.discovery.myfeign;

import com.chenyilei.cloud.discovery.myfeign.ext.FeignClientMethodInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

/**
 * factoryBean 为代理bean之类的类,可以放入spring容器的一个包装
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/09- 14:25
 */
public class MyFeignRegistry implements ImportBeanDefinitionRegistrar, BeanFactoryAware, ApplicationContextAware {

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    //将我们标注MyFeignClient的接口变为代理
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata
            , BeanDefinitionRegistry registry) {
        registerBeanByClients(importingClassMetadata, registry);

    }

    private void registerBeanByClients(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableMyFeign.class.getName());
        Class[] clients = (Class[]) annotationAttributes.get("clients");

        //enable -> import_registry -> FeignClient
        //识别MyFeignClient &&interface && MyFeignClient ==> 动态代理
        Stream.of(clients)
                .filter(x -> {
                    //是一个接口
                    return x.isInterface();
                })
                .filter(x -> {
                    //是需要代理的接口
                    return AnnotationUtils.findAnnotation(x, MyFeignClient.class) != null;
                })
                .forEach(clazz -> {
                    //对每一个 @MyFeignClient标注的接口进行代理,用restTemplate进行调用
                    MyFeignClient myfeignClient = AnnotationUtils.findAnnotation(clazz, MyFeignClient.class);
                    String serviceName = myfeignClient.value();
                    Object MyProxyObject = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}
                            , new FeignClientMethodInvocationHandler(serviceName, beanFactory));
                    //注册生成的代理类
                    registryByFactoryBean(MyProxyObject, "feign" + serviceName, registry);
                });
    }

    private void registerBeanByScanPackages(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    }

    private void registryByFactoryBean(Object obj, String beanName, BeanDefinitionRegistry registry) {
        FactoryBean factoryBean = new AbstractFactoryBean<Object>() {
            @Override
            public Class<?> getObjectType() {
                return obj.getClass();
            }

            @Override
            protected Object createInstance() throws Exception {
                return obj;
            }
        };
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(FactoryBean.class, () -> {
            return factoryBean;
        }).getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void registryBySingleton(Object obj, BeanDefinitionRegistry registry) {
        if (registry instanceof SingletonBeanRegistry) {
            SingletonBeanRegistry singleR = (SingletonBeanRegistry) registry;
            //   singleR.registerSingleton();
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
