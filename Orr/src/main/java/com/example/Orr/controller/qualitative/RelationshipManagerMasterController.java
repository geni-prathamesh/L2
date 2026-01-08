package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.RelationshipManagerMasterDto;
import com.example.Orr.service.qualitative.RelationshipManagerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationship-manager")
public class RelationshipManagerMasterController {

    @Autowired
    private RelationshipManagerMasterService service;

    @PostMapping("/create")
    public ResponseEntity<RelationshipManagerMasterDto> create(
            @RequestBody RelationshipManagerMasterDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RelationshipManagerMasterDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelationshipManagerMasterDto> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RelationshipManagerMasterDto> update(
            @PathVariable String id,
            @RequestBody RelationshipManagerMasterDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

