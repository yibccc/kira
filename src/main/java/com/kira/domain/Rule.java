package com.kira.domain;

import lombok.Data;

/**
 * @author Kira
 * @create 2023-02-11 16:52
 */
@Data
public class Rule {
    private Integer id;
    private String name;
    private String storeId;
    private String prepTime;
    private Integer eachManageArea;
    private float eachServe;
    private Integer dutyNum;
    private String closureTime;
    private Integer eachCleanArea;
    private Integer createUser;
}
