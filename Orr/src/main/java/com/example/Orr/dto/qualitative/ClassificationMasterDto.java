package com.example.Orr.dto.qualitative;

import lombok.Data;

@Data
public class ClassificationMasterDto {

    private String id;
    private String classificationCode;
    private String classificationName;
    private Integer riskWeight;
    private boolean active;

}

