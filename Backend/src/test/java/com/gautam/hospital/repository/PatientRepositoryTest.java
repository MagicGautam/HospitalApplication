package com.gautam.hospital.repository;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Diagnosis D1 = Diagnosis.builder().disease("Piles").description("Subject ko gupt rog hai").build();
        Diagnosis D2 = Diagnosis.builder().disease("Haryana").description("Subject ko Haryana hai").build();
        List<Diagnosis> DL = new ArrayList<Diagnosis>();
        DL.add(D1);
        DL.add(D2);
        Patient patient = Patient.builder().patientName("Arpit Bala").patientAddress("Uspe to ghar hai9").patientNumber(1113123333).diagnosisList(DL).build();
        entityManager.persist(patient);
    }

    @Test
    @DisplayName("Test based on getpatientID")
    public void whenFindByPatientIdThenReturnPatient() {
        Patient patient = patientRepository.findByPatientId(1L);
        assertEquals(patient.getPatientName(), "Arpit Bala");

    }
}