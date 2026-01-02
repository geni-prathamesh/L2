package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.ClassificationMasterDto;
import com.example.Orr.service.qualitative.ClassificationMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/classification")
public class ClassificationMasterController {

    @Autowired
    private ClassificationMasterService service;

    @PostMapping("/create")
    public ResponseEntity<ClassificationMasterDto> create(
            @RequestBody ClassificationMasterDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClassificationMasterDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ClassificationMasterDto> getByCode(
            @PathVariable String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<ClassificationMasterDto> update(
            @PathVariable String code,
            @RequestBody ClassificationMasterDto dto) {
        return ResponseEntity.ok(service.update(code, dto));
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Void> deactivate(@PathVariable String code) {
        service.deactivate(code);
        return ResponseEntity.noContent().build();
    }
}
