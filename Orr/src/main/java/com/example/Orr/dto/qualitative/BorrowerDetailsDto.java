package com.example.Orr.dto.qualitative;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerDetailsDto {
    private String id;
    private String uUId;
    private String borrowerId;
    private String name;
    private String currencyCode;
    private String currencyName;
    private String currencySymbol;
    private String classificationCode;
    private String classificationName;
    private Integer riskWeight;
    private LocalDate assessmentDate;
    private String rmCode;      // RM001, RM002
    private String rmName;
    private String email;
    private String mobile;
    private String branch;
    private String industryCode;
    private String industryName;
}
