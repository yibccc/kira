package com.kira.domain;

import lombok.Data;

/**
 * @author shkstart
 * @create 2023-01-2620:46
 */
@Data
public class Employee {
    private Integer id;
    private String name;
    private String email;
    private String job_id;
    private String store_id;
    private String phone;
    private Integer status;
}
