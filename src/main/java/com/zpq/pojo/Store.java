package com.zpq.pojo;

import java.sql.Time;

public class Store {
    private int id;
    private String name;
    private String address;
    private double size;
    private Time prepTime;
    private int eachManageArea;
    private float eachServe;
    private int dutyNum;
    private Time closureTime;
    private int eachCleanArea;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Time getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Time prepTime) {
        this.prepTime = prepTime;
    }

    public int getEachManageArea() {
        return eachManageArea;
    }

    public void setEachManageArea(int eachManageArea) {
        this.eachManageArea = eachManageArea;
    }

    public float getEachServe() {
        return eachServe;
    }

    public void setEachServe(float eachServe) {
        this.eachServe = eachServe;
    }

    public int getDutyNum() {
        return dutyNum;
    }

    public void setDutyNum(int dutyNum) {
        this.dutyNum = dutyNum;
    }

    public Time getClosureTime() {
        return closureTime;
    }

    public void setClosureTime(Time closureTime) {
        this.closureTime = closureTime;
    }

    public int getEachCleanArea() {
        return eachCleanArea;
    }

    public void setEachCleanArea(int eachCleanArea) {
        this.eachCleanArea = eachCleanArea;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", size=" + size +
                ", prepTime=" + prepTime +
                ", eachManageArea=" + eachManageArea +
                ", eachServe=" + eachServe +
                ", dutyNum=" + dutyNum +
                ", closureTime=" + closureTime +
                ", eachCleanArea=" + eachCleanArea +
                '}';
    }
}
