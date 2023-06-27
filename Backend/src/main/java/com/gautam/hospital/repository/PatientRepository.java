package com.gautam.hospital.repository;

import com.gautam.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Patient findByPatientName(String patientName);
    public Patient findByPatientId(Long patientId);

}
