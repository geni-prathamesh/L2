package com.example.Orr.dto.qualitative;

import lombok.Data;

@Data
public class CurrencyMasterDto {
    private String id;
    private String code;
    private String name;
    private String symbol;
    private boolean active;
}
