package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.BorrowerDetailsDto;
import com.example.Orr.service.qualitative.BorrowerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrower")
public class BorrowerDetailsController {

    @Autowired
    private BorrowerDetailsService borrowerDetailsService;


    @GetMapping("/all")
    public ResponseEntity<List<BorrowerDetailsDto>> getAllBorrowers() {
        List<BorrowerDetailsDto> borrowers = borrowerDetailsService.findAll();
        return ResponseEntity.ok(borrowers);
    }

    @GetMapping("/{borrowerId}")
    public ResponseEntity<BorrowerDetailsDto> getBorrowerById(@PathVariable Integer borrowerId) {
        BorrowerDetailsDto borrower = borrowerDetailsService.findByBorrowerId(borrowerId);
        if (borrower != null) {
            return ResponseEntity.ok(borrower);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<BorrowerDetailsDto> createBorrower(@RequestBody BorrowerDetailsDto borrowerDto) {
        BorrowerDetailsDto createdBorrower = borrowerDetailsService.create(borrowerDto);
        return ResponseEntity.ok(createdBorrower);
    }

    @PutMapping("/update/{borrowerId}")
    public ResponseEntity<BorrowerDetailsDto> updateBorrower(
            @PathVariable Integer borrowerId,
            @RequestBody BorrowerDetailsDto borrowerDto) {
        BorrowerDetailsDto updatedBorrower = borrowerDetailsService.updateByBorrowerId(borrowerId, borrowerDto);
        if (updatedBorrower != null) {
            return ResponseEntity.ok(updatedBorrower);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{borrowerId}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Integer borrowerId) {
        borrowerDetailsService.deleteByBorrowerId(borrowerId);
        return ResponseEntity.noContent().build();
    }
}
