package com.tpf.study.demo.init;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
/**
 * 资源类如该类
 * */
public class Loader1 {
    private static Logger logger = Logger.getLogger(Loader1.class);
    @PostConstruct
    private void init(){
        logger.info("启动加载资源 1111111");
        try {
            logger.info("1111111 开始睡觉");
            Thread.sleep(1000);
            logger.info("1111111 醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
