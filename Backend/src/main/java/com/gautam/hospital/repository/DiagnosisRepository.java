package com.gautam.hospital.repository;

import com.gautam.hospital.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
    public Diagnosis findByDisease(String disease);
    public Diagnosis findByDiagnosisId(int diagnosisId);
}
