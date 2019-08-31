package com.tpf.study.demo.controller;

import com.tpf.framework.core.annotation.NotNull;
import com.tpf.framework.ms.annotation.MSController;
import com.tpf.framework.ms.annotation.MSRequestMapping;
import org.springframework.stereotype.Component;

@Component
@MSController
public class DemoController {
    @MSRequestMapping("/demo/hello")
    public String hello(@NotNull String name){
        System.out.println("----->调用add user方法");
        return "hello "+ name;
    }
}
