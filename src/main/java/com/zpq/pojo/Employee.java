package com.zpq.pojo;

public class Employee {
    private int id;
    private String name;
    private String phone;
    private String email;
    private int status;
    private int jobId;
    private int storeId;
    private Integer checkDuration;
    private String checkWorkDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Integer getCheckDuration() {
        return checkDuration;
    }

    public void setCheckDuration(Integer checkDuration) {
        this.checkDuration = checkDuration;
    }

    public String getCheckWorkDay() {
        return checkWorkDay;
    }

    public void setCheckWorkDay(String checkWorkDay) {
        this.checkWorkDay = checkWorkDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", jobId=" + jobId +
                ", storeId=" + storeId +
                ", checkDuration=" + checkDuration +
                ", checkWorkDay=" + checkWorkDay +
                '}';
    }
}
