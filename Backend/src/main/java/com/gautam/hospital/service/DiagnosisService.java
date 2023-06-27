package com.gautam.hospital.service;

import com.gautam.hospital.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    public Diagnosis saveDiagnosis(Diagnosis diagnosis);

    public List<Diagnosis> fetchDiagnosisList();

    public Diagnosis fetchDiagnosisById(Long diagnosisId);

    public void deleteDiagnosisById(Long diagnosisId);
    public Diagnosis updateDiagnosis(Long diagnosisId, Diagnosis diagnosis);

    public Diagnosis fetchDiagnosisByName(String disease);
}
