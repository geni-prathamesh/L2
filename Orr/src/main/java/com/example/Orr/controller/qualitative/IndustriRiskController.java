package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.IndustriRiskDto;
import com.example.Orr.entity.qualitative.IndustriRisk;
import com.example.Orr.service.qualitative.IndustriRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industri-risk")
public class IndustriRiskController {

    @Autowired
    private IndustriRiskService industriRiskService;

    @GetMapping("/all")
    public ResponseEntity<List<IndustriRiskDto>> getAllIndustriRisks() {
        List<IndustriRiskDto> list = industriRiskService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustriRiskDto> getIndustriRiskById(@PathVariable Integer id) {
        IndustriRiskDto dto = industriRiskService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<IndustriRiskDto> createIndustriRisk(@RequestBody IndustriRiskDto industriRiskDto) {
        IndustriRiskDto created = industriRiskService.createIndustriRisk(industriRiskDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<IndustriRiskDto> updateIndustriRisk(@PathVariable Integer id, @RequestBody IndustriRisk industriRisk) {
        IndustriRiskDto updated = industriRiskService.updateById(id, industriRisk);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIndustriRisk(@PathVariable Integer id) {
        industriRiskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
