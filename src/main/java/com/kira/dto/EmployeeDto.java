package com.kira.dto;

import com.kira.domain.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2023-02-0617:48
 */
@Data
public class EmployeeDto extends Employee {
//    private List<Employee> employees = new ArrayList<>();

    private String jobName;

    private String StoreName;


}
