package com.kira.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kira
 * @create 2023-01-2620:46
 */
@Data
public class Store implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private double size;
    private String prepTime;
    private Integer eachManageArea;
    private float eachServe;
    private Integer dutyNum;
    private String closureTime;
    private Integer eachCleanArea;
}
