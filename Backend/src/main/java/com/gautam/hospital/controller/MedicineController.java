package com.gautam.hospital.controller;

import com.gautam.hospital.entity.Medicine;
import com.gautam.hospital.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getMedicinesByDisease(@RequestParam String disease) {
        List<Medicine> medicines = medicineService.getMedicinesByDisease(disease);
        return ResponseEntity.ok(medicines);
    }

    @PostMapping
    public ResponseEntity<Medicine> saveMedicine(@RequestBody Medicine medicine) {
        Medicine savedMedicine = medicineService.saveMedicine(medicine);
        return ResponseEntity.ok(savedMedicine);
    }
}
