package com.gautam.hospital.service;

import com.gautam.hospital.entity.Patient;

import java.util.List;

public interface PatientService {

    public Patient savePatient(Patient patient);

    public List<Patient> fetchPatientList();

    public Patient fetchPatientById(Long patientId);

    public void deletePatientById(Long patientId);
    public Patient updatePatient(Long patientId, Patient patient);

    public Patient fetchPatientByName(String patientName);

}
