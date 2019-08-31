package com.tpf.framework.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("com.tpf")
@ImportResource("classpath:applicationContext.xml")//在config类中加载xml配置
/**
 * 该类只在学习使用，考虑数据源用配置的方式还是比较方便。
 * */
public class ApplicationConfig {
    private static Log logger = LogFactory.getLog(ApplicationConfig.class);
}
