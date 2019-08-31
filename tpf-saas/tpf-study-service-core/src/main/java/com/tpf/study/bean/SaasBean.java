package com.tpf.study.bean;

import org.springframework.stereotype.Component;

@Component
public class SaasBean {
    private int id;
    private String saasName;
    private String saasAlias;
    private String saasDBConfig;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaasName() {
        return saasName;
    }

    public void setSaasName(String saasName) {
        this.saasName = saasName;
    }

    public String getSaasAlias() {
        return saasAlias;
    }

    public void setSaasAlias(String saasAlias) {
        this.saasAlias = saasAlias;
    }

    public String getSaasDBConfig() {
        return saasDBConfig;
    }

    public void setSaasDBConfig(String saasDBConfig) {
        this.saasDBConfig = saasDBConfig;
    }
}
