package com.kira.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.IntrospectionException;
import java.time.LocalDateTime;

/**
 * @author shkstart
 * @create 2023-01-2620:46
 */
@Data
public class Employee {
    private Integer id;
    private String name;
    private String email;
    private String jobId;
    private String storeId;
    private String phone;
    private Integer status;
    private Integer checkDuration;
    private String startTime;
    private String endTime;
    private String checkWorkday;
}
