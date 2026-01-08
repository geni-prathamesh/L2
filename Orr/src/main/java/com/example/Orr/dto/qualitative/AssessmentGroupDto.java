package com.example.Orr.dto.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentGroupDto {

    private String subgroupId;
    private String title;
    private List<AssessmentOptionDto> options;
}

