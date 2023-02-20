package com.kira.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kira
 * @create 2023-01-2723:55
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
}
