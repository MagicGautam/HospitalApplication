package com.gautam.hospital.service;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.repository.DiagnosisRepository;
import com.gautam.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;


    public PatientServiceImpl(PatientRepository patientRepository, DiagnosisRepository diagnosisRepository) {
        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
    }
    @Override
    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> fetchPatientList() {
        return patientRepository.findAll();
    }

    @Override
    public Patient fetchPatientById(Long patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    @Override
    public void deletePatientById(Long patientId) {
    patientRepository.deleteById(patientId);
    }

    @Override
    public Patient updatePatient(Long patientId, Patient patient) {
        Patient patDB=patientRepository.findByPatientId(patientId);
        if(Objects.nonNull(patient.getPatientName()))
        {
            patDB.setPatientName(patient.getPatientName());
        }
        if(Objects.nonNull(patient.getPatientAddress()) && !"".equalsIgnoreCase(patient.getPatientAddress()))
        {
            patDB.setPatientAddress(patient.getPatientAddress());
        }
        if(Objects.nonNull(patient.getPatientNumber()))
        {
            patDB.setPatientNumber(patient.getPatientNumber());
        }
        return patientRepository.save(patDB);
    }
    @Override
    public List<Diagnosis> getDiagnosesByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            return patient.getDiagnosisList();
        }
        return Collections.emptyList();
    }

    @Override
    public Patient fetchPatientByName(String patientName) {
        return patientRepository.findByPatientName(patientName);
    }
}