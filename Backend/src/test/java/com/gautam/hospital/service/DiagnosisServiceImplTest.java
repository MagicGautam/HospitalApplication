package com.gautam.hospital.service;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.repository.DiagnosisRepository;
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
class DiagnosisServiceImplTest {
@Autowired
private DiagnosisService diagnosisService;

@MockBean
private DiagnosisRepository diagnosisRepository;

    @BeforeEach
    void setUp() {
        Diagnosis diagnosis= Diagnosis.builder()
                .disease("Cancer")
                .description("Cancer is a disease involving abnormal cell growth with the potential to invade or spread to other parts of the body.")
                .patient(new Patient("Arpit Bala", "Uspe to ghar hai9", 1113123333)).build();

    Mockito.when(diagnosisRepository.findByDisease("Cancer"))
            .thenReturn(diagnosis);
    }

@Test
    public void whenValidDiseaseName_ThenDiagnosisShouldFound(){
String diseaseName="Cancer";
Diagnosis found=diagnosisService.fetchDiagnosisByName(diseaseName);
assertEquals(diseaseName, found.getDisease());
}
}

