package com.gautam.hospital.service;

import com.gautam.hospital.entity.Medicine;
import com.gautam.hospital.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;


    @Override
    public List<Medicine> getMedicinesByDisease(String disease) {
        return medicineRepository.findByDiseaseName(disease);
    }

    @Override
    public List<Medicine> getMedicinesByMedicineName(String medicineName) {
        return medicineRepository.findByMedicineName(medicineName);
    }

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
}
