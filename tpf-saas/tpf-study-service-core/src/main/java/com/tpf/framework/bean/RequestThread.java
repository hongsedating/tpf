package com.tpf.framework.bean;

import com.tpf.framework.common.ThreadParameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")//处理请求线程类，多例
@Component
public class RequestThread extends Thread{
    private String dsName;
    public void setDsName(String dsName){
        this.dsName = dsName;
    }
    @Override
    public void run() {
        //数据源必须要在这里配置，在构造方法中不能设置局域变量
        ThreadParameter.set(DynamicRoutingDataSource.THREAD_LOCAL_KEY_DS,dsName);
        System.out.println(ThreadParameter.get(DynamicRoutingDataSource.THREAD_LOCAL_KEY_DS));
    }
}
