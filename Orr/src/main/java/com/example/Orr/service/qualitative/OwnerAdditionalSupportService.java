package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.OwnerAdditionalSupportDto;
import com.example.Orr.entity.qualitative.OwnerAdditionalSupport;

import java.util.List;

public interface OwnerAdditionalSupportService {
    List<OwnerAdditionalSupportDto> findAll();
    OwnerAdditionalSupportDto findById(Integer id);
    OwnerAdditionalSupportDto createOwnerAdditionalSupport(OwnerAdditionalSupportDto ownerAdditionalSupportDto);
    OwnerAdditionalSupportDto updateById(Integer id, OwnerAdditionalSupport ownerAdditionalSupport);
    void deleteById(Integer id);
}
