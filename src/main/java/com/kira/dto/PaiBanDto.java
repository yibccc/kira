package com.kira.dto;

import com.kira.domain.PaiBan;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Kira
 * @create 2023-02-12 16:29
 */
@Data
public class PaiBanDto extends PaiBan implements Serializable{
    private String employeeName;
    private String jobName;
}
