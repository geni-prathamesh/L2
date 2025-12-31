package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountStatusDto;

import java.util.List;

public interface AccountStatusService {

    List<AccountStatusDto> findAll();

    AccountStatusDto findById(Integer id);

    AccountStatusDto create(AccountStatusDto dto);

    AccountStatusDto updateById(Integer id, AccountStatusDto dto);

    void deleteById(Integer id);
}
