package com.example.Orr.dto.qualitative;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerDetailsDto {
    private String id;
    private String uUId;
    private Integer borrowerId;
    private String name;
    private String currencyType;
    private String currency;
    private String classification;
    private LocalDate assessmentDate;
    private String relationshipManager;
    private String industry;
}
