package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.CurrencyMasterDto;
import com.example.Orr.service.qualitative.CurrencyMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/currency")
public class CurrencyMasterController {

    @Autowired
    private CurrencyMasterService service;

    @PostMapping("/create")
    public ResponseEntity<CurrencyMasterDto> create(@RequestBody CurrencyMasterDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyMasterDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyMasterDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }
}

