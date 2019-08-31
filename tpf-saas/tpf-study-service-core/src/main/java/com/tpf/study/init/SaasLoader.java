package com.tpf.study.init;

import com.tpf.framework.bean.DynamicRoutingDataSource;
import com.tpf.framework.common.ThreadParameter;
import com.tpf.study.bean.SaasBean;
import com.tpf.study.mapper.SaasMapper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class SaasLoader implements Loader{
    private static Logger logger = Logger.getLogger(SaasLoader.class);
    private ApplicationContext ac;
    @Autowired
    private SaasMapper saasMapper;
    @Override
    public boolean load(ApplicationContext ac) {
        this.ac = ac;
        ThreadParameter.set(DynamicRoutingDataSource.THREAD_LOCAL_KEY_DS, "CORE");
        List<SaasBean> saasList = saasMapper.getAllSaas();
        logger.info("----->saas size="+saasList.size());
        for (SaasBean saas : saasList) {
            logger.info("----->加载数据源："+saas.getSaasName());
            addDataSource(saas.getSaasName(), JSONObject.fromObject(saas.getSaasDBConfig()));
        }
        return true;
    }
    /**
     * 动态添加数据源
     * */
    private void addDataSource(String dsName, JSONObject dsConfig){
        Object dataSource = ac.getBean("dataSource");
        if(dataSource != null){
            DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource)dataSource;
            BasicDataSource source = new BasicDataSource();
            source.setDriverClassName(MapUtils.getString(dsConfig, "db.driverClassName"));
            source.setUsername(MapUtils.getString(dsConfig, "db.username"));
            source.setPassword(MapUtils.getString(dsConfig, "db.password"));
            source.setUrl(MapUtils.getString(dsConfig, "db.url"));
            source.setMaxWait(MapUtils.getLong(dsConfig,"db.maxWait", 3000l));
            source.setMaxActive(MapUtils.getInteger(dsConfig,"db.maxActive", 300));
            source.setInitialSize(MapUtils.getInteger(dsConfig,"db.initialSize", 5));
            source.setMaxIdle(MapUtils.getInteger(dsConfig,"db.maxIdle", 10));
            source.setMinIdle(MapUtils.getInteger(dsConfig,"db.minIdle", 5));
            source.setValidationQuery(" SELECT 1 ");
            source.setTestOnBorrow(true);
            source.setRemoveAbandoned(true);
            source.setRemoveAbandonedTimeout(180);
            dynamicRoutingDataSource.registerDataSource(dsName, source);
        }
    }
}
