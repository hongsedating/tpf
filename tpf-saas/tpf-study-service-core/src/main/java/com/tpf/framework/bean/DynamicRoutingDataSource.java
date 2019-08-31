package com.tpf.framework.bean;

import com.tpf.framework.common.ThreadParameter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 动态路由数据源
 * @author lingkai
 * @version 1.0
 * */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    public static final String THREAD_LOCAL_KEY_DS = "_dsKey";
    private static Map<Object, Object> dataSources = new HashMap<>();
    private static Set<String> dataSourceKeys = new HashSet<>();
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * 从线程局域变量中提取数据源key
     * */
    @Override
    protected Object determineCurrentLookupKey() {
        Object dsKey = ThreadParameter.get(DynamicRoutingDataSource.THREAD_LOCAL_KEY_DS);
        if(dsKey != null && dataSourceKeys.contains(dsKey)){
            return dsKey;
        }else{
            return null;
        }
    }
    /**
     * 必须要重写该方法，用来动态增删改数据源
     * */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources){
        for (Object dsKey : targetDataSources.keySet()) {
            dataSourceKeys.add(dsKey.toString());
        }
        super.setTargetDataSources(targetDataSources);
    }

    /**
     * Register a datasource to the router.
     * Add one if there is no data source named dsKey's value in the router, otherwise update the data source
     * @param dsKey Add one if there is no data source named this value in the router, otherwise update the data source
     * */
    public boolean registerDataSource(String dsKey, DataSource dataSource) {
        lock.lock();
        dataSources.put(dsKey, dataSource);
        setTargetDataSources(dataSources);
        afterPropertiesSet();
        lock.unlock();
        return true;
    }

    /**
     * remove datasource from datasource router
     * @param dsKey the key of datasource in router
     * */
    public boolean removeDataSource(String dsKey){
        lock.lock();
        dataSources.remove(dsKey);
        setTargetDataSources(dataSources);
        afterPropertiesSet();
        lock.unlock();
        return true;
    }

}
