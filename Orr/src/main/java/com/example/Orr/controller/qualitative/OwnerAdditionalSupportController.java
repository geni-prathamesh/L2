package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.OwnerAdditionalSupportDto;
import com.example.Orr.entity.qualitative.OwnerAdditionalSupport;
import com.example.Orr.service.qualitative.OwnerAdditionalSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner-add-support")
public class OwnerAdditionalSupportController {

    @Autowired
    private OwnerAdditionalSupportService ownerAdditionalSupportService;

    @GetMapping("/all")
    public ResponseEntity<List<OwnerAdditionalSupportDto>> getAll() {
        List<OwnerAdditionalSupportDto> list = ownerAdditionalSupportService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerAdditionalSupportDto> getById(@PathVariable Integer id) {
        OwnerAdditionalSupportDto dto = ownerAdditionalSupportService.findById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OwnerAdditionalSupportDto> create(@RequestBody OwnerAdditionalSupportDto dto) {
        OwnerAdditionalSupportDto created = ownerAdditionalSupportService.createOwnerAdditionalSupport(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OwnerAdditionalSupportDto> update(
            @PathVariable Integer id,
            @RequestBody OwnerAdditionalSupport dto) {
        OwnerAdditionalSupportDto updated = ownerAdditionalSupportService.updateById(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ownerAdditionalSupportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
