package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.AccountConductDto;
import com.example.Orr.service.qualitative.AccountConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-conduct")
public class AccountConductController {

    @Autowired
    private AccountConductService accountConductService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountConductDto>> getAll() {
        List<AccountConductDto> list = accountConductService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountConductDto> getById(@PathVariable Integer id) {
        AccountConductDto dto = accountConductService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<AccountConductDto> create(@RequestBody AccountConductDto dto) {
        AccountConductDto created = accountConductService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountConductDto> update(@PathVariable Integer id, @RequestBody AccountConductDto dto) {
        AccountConductDto updated = accountConductService.updateById(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountConductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
