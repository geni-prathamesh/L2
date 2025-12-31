package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "qualitative_input_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualitativeInput {

    @Id
    private Integer id;



}
