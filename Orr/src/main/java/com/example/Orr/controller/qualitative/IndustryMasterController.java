package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.IndustryMasterDto;
import com.example.Orr.service.qualitative.IndustryMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/industry")
public class IndustryMasterController {

    @Autowired
    private IndustryMasterService service;

    @PostMapping("/create")
    public ResponseEntity<IndustryMasterDto> create(@RequestBody IndustryMasterDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<IndustryMasterDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<IndustryMasterDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<IndustryMasterDto> update(
            @PathVariable String code,
            @RequestBody IndustryMasterDto dto) {
        return ResponseEntity.ok(service.update(code, dto));
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> deactivate(@PathVariable String code) {
        service.deactivate(code);
        return ResponseEntity.noContent().build();
    }
}

