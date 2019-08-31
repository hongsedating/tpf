package com.tpf.framework.config;

import com.tpf.framework.bean.DynamicRoutingDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
/**
 * 该类只在学习使用，考虑数据源用配置的方式还是比较方便。
 * */
public class DynamicDataSourceConfig {
    private static Log logger = LogFactory.getLog(DynamicDataSourceConfig.class);
    @Bean
    public DataSource dataSource(){
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        logger.info("----->加载主数据源");
        Map<Object, Object> dataSources = new HashMap<>();
        //至少要初始化一个数据源
        DriverManagerDataSource mainDataSource = new DriverManagerDataSource();
        mainDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        mainDataSource.setUsername("root");
        mainDataSource.setPassword("123456");
        mainDataSource.setUrl("jdbc:mysql://192.168.3.178:3306/mybatis-spring?useUnicode=true&characterEncoding=utf8");
        dataSources.put("mybatis-spring", mainDataSource);
        //将初始化好的数据源添加到数据源路由器中
        dataSource.setTargetDataSources(dataSources);
        return dataSource;
    }
}
