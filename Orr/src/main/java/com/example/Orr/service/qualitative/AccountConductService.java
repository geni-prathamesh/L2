package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountConductDto;

import java.util.List;

public interface AccountConductService {
    List<AccountConductDto> findAll();
    AccountConductDto findById(Integer id);
    AccountConductDto create(AccountConductDto dto);
    AccountConductDto updateById(Integer id, AccountConductDto dto);
    void deleteById(Integer id);
}
