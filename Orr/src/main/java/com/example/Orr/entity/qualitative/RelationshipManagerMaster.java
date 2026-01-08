package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "relationship-manager-master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipManagerMaster {
    @Id
    private String id;

    @Indexed(unique = true)
    private String rmCode;      // RM001, RM002
    private String rmName;
    private String email;
    private String mobile;
    private String branch;
    private Boolean active;

    public RelationshipManagerMaster(String rmCode, String rmName, String email, String mobile, String branch, boolean active) {
        this.rmCode=rmCode;
        this.rmName=rmName;
        this.email=email;
        this.mobile=mobile;
        this.branch=branch;
        this.active=active;
    }
}
