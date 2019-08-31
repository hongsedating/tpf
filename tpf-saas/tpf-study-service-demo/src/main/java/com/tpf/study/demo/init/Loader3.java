package com.tpf.study.demo.init;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("loader2")
public class Loader3 {
    private static Logger logger = Logger.getLogger(Loader3.class);
    @PostConstruct
    private void init(){
        logger.info(">启动加载资源 333333");
    }
}
