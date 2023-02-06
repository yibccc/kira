package com.kira.dto;

import com.kira.domain.Employee;
import com.kira.domain.Preference;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2023-02-0617:48
 */
@Data
public class EmployeeDto extends Employee {
    private List<Preference> preferences = new ArrayList<>();


}
