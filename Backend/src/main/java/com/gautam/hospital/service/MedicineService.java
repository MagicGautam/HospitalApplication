package com.gautam.hospital.service;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Medicine;

import java.util.List;

public interface MedicineService {
    public List<Medicine> getMedicinesByDisease(String disease);
    public List<Medicine> getMedicinesByMedicineName(String medicineName);

    public Medicine saveMedicine(Medicine medicine);

    public default void deleteByMedicineName(String medicineName) {

    }
}
