package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.AccountStatusDto;
import com.example.Orr.service.qualitative.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-status")
public class AccountStatusController {

    @Autowired
    private AccountStatusService accountStatusService;

    @GetMapping
    public ResponseEntity<List<AccountStatusDto>> getAll() {
        List<AccountStatusDto> list = accountStatusService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountStatusDto> getById(@PathVariable Integer id) {
        AccountStatusDto dto = accountStatusService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AccountStatusDto> create(@RequestBody AccountStatusDto dto) {
        AccountStatusDto created = accountStatusService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountStatusDto> update(@PathVariable Integer id,
                                                   @RequestBody AccountStatusDto dto) {
        AccountStatusDto updated = accountStatusService.updateById(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
