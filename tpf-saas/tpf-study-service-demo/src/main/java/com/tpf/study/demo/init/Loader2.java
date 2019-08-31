package com.tpf.study.demo.init;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("loader1")
public class Loader2 {
    private static Logger logger = Logger.getLogger(Loader2.class);
    @PostConstruct
    private void init(){
        logger.info("启动加载资源 2222222");
        try {
            logger.info("2222222 开始睡觉");
            Thread.sleep(2000);
            logger.info("2222222 醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
