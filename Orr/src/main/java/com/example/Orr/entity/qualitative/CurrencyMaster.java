package com.example.Orr.entity.qualitative;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "currency_master")
@Data
public class CurrencyMaster {

    @Id
    private String id;
    @Indexed(unique = true)
    private String code;
    private String name;
    private String symbol;
    private boolean active = true;


    public CurrencyMaster() {
    }

    public CurrencyMaster(String code, String name, String symbol, boolean active) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
        this.active = active;
    }

}

