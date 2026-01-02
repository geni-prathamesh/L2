package com.example.Orr.service.qualitative;


import com.example.Orr.dto.qualitative.IndustryMasterDto;
import java.util.List;

public interface IndustryMasterService {

    IndustryMasterDto create(IndustryMasterDto dto);
    List<IndustryMasterDto> findAll();
    IndustryMasterDto findByCode(String industryCode);
    IndustryMasterDto update(String industryCode, IndustryMasterDto dto);
    void deactivate(String industryCode);
}
