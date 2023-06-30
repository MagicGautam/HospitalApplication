package com.gautam.hospital.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientTest {
    @BeforeEach
    void setUp() {

    }
    @Test
    public void getdiagnosislisttest(){
        Patient patient=new Patient();
        List<Diagnosis> diagnosisList = new ArrayList<>();

        // Set the diagnosisList of the patient
        patient.setDiagnosisList(diagnosisList);

        // Get the diagnosisList from the patient
        List<Diagnosis> retrievedDiagnosisList = patient.getDiagnosisList();

        // Assert that the retrieved diagnosisList is the same as the original diagnosisList
        Assertions.assertEquals(diagnosisList, retrievedDiagnosisList);
    }
}