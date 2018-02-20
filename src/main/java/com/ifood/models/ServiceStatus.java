package com.ifood.models;

import java.util.Date;

public class ServiceStatus {
    private Date lastCheck;
    private String status;
    private String nextCheck;

    public ServiceStatus(Date lastCheck, String status) {
        this.lastCheck = lastCheck;
        this.status = status;
        this.nextCheck = "This data was just checked with OpenWeatherMap API!";
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

    public String getNextCheck() {
        return nextCheck;
    }

    public void setNextCheck(String nextCheck) {
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
