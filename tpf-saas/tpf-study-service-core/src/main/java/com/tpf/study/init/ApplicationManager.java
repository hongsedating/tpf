package com.tpf.study.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationManager {
    private static Logger logger = Logger.getLogger(ApplicationManager.class);
    public static ApplicationContext ac = null;

    public static void startService() {
        logger.info("----->服务启动start");
        //加载配置
        ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        //加载saas数据源
        SaasLoader saasLoader = ac.getBean(SaasLoader.class);
        saasLoader.load(ac);

        //启动thrift服务
        SocketServer server = ac.getBean(SocketServer.class);
        server.start();


        logger.info("----->服务启动end");
    }

    public static ApplicationContext getApplicationContext(){
        return ac;
    }

}
