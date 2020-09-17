package com.itheima.entity;

import java.io.Serializable;

public class Message implements Serializable {

    private String audit;

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }
}
