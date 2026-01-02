package com.example.Orr.entity.qualitative;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "industry_master")
@Data
public class IndustryMaster {

    @Id
    private String id;

    @Indexed(unique = true)
    private String industryCode;

    private String industryName;

    private boolean active = true;

}
