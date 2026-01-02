package com.example.Orr.entity.qualitative;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "classification_master")
@Data
public class ClassificationMaster {

    @Id
    private String id;

    @Indexed(unique = true)
    private String classificationCode;

    private String classificationName;

    private Integer riskWeight;

    private boolean active = true;


}
