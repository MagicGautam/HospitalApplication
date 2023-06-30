package com.gautam.hospital.service;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceImplTest {
@Autowired
private PatientService patientService;
@MockBean
private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        Diagnosis D1 = Diagnosis.builder().disease("Genius").description("Subject is genius level Musician").build();
        List<Diagnosis> DL = new ArrayList<Diagnosis>();
        DL.add(D1);
        Patient patient=Patient.builder().patientName("Steve Wilson").patientAddress("Porcupine Tree").patientNumber(1112312312).diagnosisList(DL).build();

        Diagnosis D2 = Diagnosis.builder().disease("Depression").description("Subject is suffering from Depression").build();
        List<Diagnosis> DL2 = new ArrayList<Diagnosis>();
        DL2.add(D1);
        Patient patient2=Patient.builder().patientName("Chris Cornell").patientAddress("SoundGarden").patientNumber(1112322312).diagnosisList(DL2).build();

        Mockito.when(patientRepository.findByPatientName("Chris Cornell"))
                .thenReturn(patient2);
    }
    @Test
    public void whenValidPatientName_ThenPatientShouldFound(){
        String patientName="Steve Wilson";
        Patient found=patientService.fetchPatientByName(patientName);
        assertEquals(patientName, found.getPatientName());
    }




}