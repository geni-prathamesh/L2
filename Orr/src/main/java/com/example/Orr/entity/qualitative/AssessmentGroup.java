package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentGroup {

    private String subgroupId;   // e.g. OASA_01, SOAA_02
    private String title;         // Table name
    private List<AssessmentOption> options;
}
