package com.example.Orr.service.qualitative;


import com.example.Orr.dto.qualitative.ClassificationMasterDto;

import java.util.List;

public interface ClassificationMasterService {

    ClassificationMasterDto create(ClassificationMasterDto dto);
    List<ClassificationMasterDto> findAll();
    ClassificationMasterDto findByCode(String classificationCode);
    ClassificationMasterDto update(String classificationCode, ClassificationMasterDto dto);
    void deactivate(String classificationCode);
}
