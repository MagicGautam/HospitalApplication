package com.gautam.hospital.repository;

import com.gautam.hospital.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByDiseaseName(String disease);
    List<Medicine> findByMedicineName(String medicineName);
}
