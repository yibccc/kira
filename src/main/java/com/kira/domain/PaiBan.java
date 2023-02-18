package com.kira.domain;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;

/**
 * @author Kira
 * @create 2023-02-11 17:11
 */
@Data
public class PaiBan implements Serializable {
    private Integer id;
    private Integer employeeId;
    private Integer storeId;
    private String startTime;
    private String endTime;
    private String date;
    private Integer jobId;
}
