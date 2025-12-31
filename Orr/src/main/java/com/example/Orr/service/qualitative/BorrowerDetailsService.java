package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.BorrowerDetailsDto;

import java.util.List;

public interface BorrowerDetailsService {
    List<BorrowerDetailsDto> findAll();
    BorrowerDetailsDto findByBorrowerId(Integer borrowerId);
    BorrowerDetailsDto create(BorrowerDetailsDto borrowerDetailsDto);
    BorrowerDetailsDto updateByBorrowerId(Integer borrowerId, BorrowerDetailsDto borrowerDetailsDto);
    void deleteByBorrowerId(Integer borrowerId);
}
