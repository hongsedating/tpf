package com.tpf.framework.ms;


import java.util.HashMap;
import java.util.Map;

/**
 * 本类参考mybatis TypeAliasRegistry
 * 请求映射的注册表
 * */
public class MSRequestMappingRegistry {
    /**
     * key 为请求全路径
     * value 为请求映射包装器
     * */
    private static final Map<String, MSHandlerHolder> requestMappings = new HashMap<String, MSHandlerHolder>();

    public static void registerRequestMapping(MSHandlerHolder holder){
        if(holder == null){
            return;
        }
        if(holder.getPath() == null || holder.getPath().length == 0){
            return;
        }
        for (String path : holder.getPath()) {
            requestMappings.put(path, holder);
        }
    }
    public static MSHandlerHolder getHandler(String path){
        if(!requestMappings.containsKey(path)){
            throw new RuntimeException("there is no handler for "+path);
        }
        return requestMappings.get(path);
    }
}
