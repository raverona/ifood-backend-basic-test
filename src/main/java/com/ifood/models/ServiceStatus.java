package com.ifood.models;

import java.util.Date;

public class ServiceStatus {
    private Date lastCheck;
    private String status;
    private Date nextCheck;

    public ServiceStatus(Date lastCheck, String status) {
        this.lastCheck = lastCheck;
        this.status = status;
    }

    public ServiceStatus(Date lastCheck, String status, Date nextCheck) {
        this.lastCheck = lastCheck;
        this.status = status;
        this.nextCheck = nextCheck;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNextCheck() {
        return nextCheck;
    }

    public void setNextCheck(Date nextCheck) {
        this.nextCheck = nextCheck;
    }

    @Override
    public String toString() {
        return "ServiceStatus{" +
                "lastCheck=" + lastCheck +
                ", status='" + status + '\'' +
                ", nextCheck='" + nextCheck + '\'' +
                '}';
    }
}
