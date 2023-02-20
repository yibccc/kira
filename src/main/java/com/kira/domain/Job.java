package com.kira.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kira
 * @create 2023-01-2620:46
 */
@Data
public class Job implements Serializable {
    private Integer id;
    private String name;
}
