package com.tpf.framework.ms;

import com.tpf.framework.ms.annotation.MSController;
import com.tpf.framework.ms.annotation.MSRequestMapping;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
/**
 * 在服务启动时会加载各Controller
 * */
public class MSControllerParser implements BeanPostProcessor {
    private static Logger logger = Logger.getLogger(MSControllerParser.class);
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        //如果是微服务Controller类
        if(beanClass.isAnnotationPresent(MSController.class)){
            Method[] methods = beanClass.getDeclaredMethods();
            for (Method method : methods) {
                if(method.isAnnotationPresent(MSRequestMapping.class)){
                    MSHandlerHolder holder = new MSHandlerHolder(method);
                    MSRequestMappingRegistry.registerRequestMapping(holder);
                }
            }
        }
        return bean;
    }
}
