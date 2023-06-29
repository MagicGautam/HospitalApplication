package com.gautam.hospital.service;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.repository.DiagnosisRepository;
import com.gautam.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DiagnosisServiceImpl implements DiagnosisService{

@Autowired
    private DiagnosisRepository diagnosisRepository;
@Autowired
    private PatientRepository patientRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, PatientRepository patientRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<Diagnosis> fetchDiagnosisList() {
        return diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis fetchDiagnosisById(Long diagnosisId) {
        return diagnosisRepository.findByDiagnosisId(Math.toIntExact(diagnosisId));
    }

    @Override
    public void deleteDiagnosisByPatientId(Long patientId) {
        diagnosisRepository.deleteById(patientId);
    }

    @Override
    public Diagnosis updateDiagnosis(Long diagnosisId, Diagnosis diagnosis) {

        Diagnosis diagDB=diagnosisRepository.findByDiagnosisId(Math.toIntExact(diagnosisId));
        if(Objects.nonNull(diagnosis.getDisease()))
        {
            diagDB.setDisease(diagnosis.getDisease());
        }
        if(Objects.nonNull(diagnosis.getDescription()))
        {
            diagDB.setDescription(diagnosis.getDescription());
        }
        return diagnosisRepository.save(diagDB);

    }
    @Override
    public Diagnosis fetchDiagnosisByName(String disease) {
        return diagnosisRepository.findByDisease(disease);
    }
}
