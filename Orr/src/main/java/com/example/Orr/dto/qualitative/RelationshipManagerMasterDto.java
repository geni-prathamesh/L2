package com.example.Orr.dto.qualitative;

import lombok.Data;

@Data
public class RelationshipManagerMasterDto {
    private String id;

    private String rmCode;      // RM001, RM002
    private String rmName;
    private String email;
    private String mobile;
    private String branch;
    private Boolean active;
}
