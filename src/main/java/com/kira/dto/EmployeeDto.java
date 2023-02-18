package com.kira.dto;

import com.kira.domain.Employee;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2023-02-0617:48
 */
@Data
public class EmployeeDto extends Employee implements Serializable {

    private String jobName;

    private String storeName;

}
