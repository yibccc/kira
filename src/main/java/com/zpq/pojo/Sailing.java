package com.zpq.pojo;


import lombok.Data;

public class Sailing {
    private Integer id;
    private int employeeId;
    private int storeId;
    private int jobId;
    private String date;
    private String startTime;
    private String endTime;

    public Sailing() {
    }

    public Sailing(int employeeId, int storeId,int jobId, String date, String startTime, String endTime) {
        this.jobId = jobId;
        this.employeeId = employeeId;
        this.storeId = storeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Sailing{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", storeId=" + storeId +
                ", jobId=" + jobId +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
