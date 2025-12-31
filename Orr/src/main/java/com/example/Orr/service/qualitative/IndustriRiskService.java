package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.IndustriRiskDto;
import com.example.Orr.entity.qualitative.IndustriRisk;

import java.util.List;

public interface IndustriRiskService {
    List<IndustriRiskDto> findAll();
    IndustriRiskDto findById(Integer id);
    IndustriRiskDto createIndustriRisk(IndustriRiskDto industriRiskDto);
    IndustriRiskDto updateById(Integer id, IndustriRisk industriRisk);
    void deleteById(Integer id);
}
