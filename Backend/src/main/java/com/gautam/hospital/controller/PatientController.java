package com.gautam.hospital.controller;

import com.gautam.hospital.DTO.PatientDTO;
import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.service.DiagnosisService;
import com.gautam.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/patients")
    public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DiagnosisService diagnosisService;
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody Patient patient) {
        // Save the Patient entity in the database
        Patient createdPatient = patientService.savePatient(patient);

        List<Diagnosis> diagnosisList = patient.getDiagnosisList();
        for (Diagnosis diagnosis : diagnosisList) {
            diagnosis.setPatient(createdPatient);
            diagnosisService.saveDiagnosis(diagnosis);
        }
        // Create the PatientResponse object and set the properties
        PatientDTO patientDTO = PatientDTO.builder()
                .patientId(createdPatient.getPatientId())
                .patientName(createdPatient.getPatientName())
                .patientAddress(createdPatient.getPatientAddress())
                .patientNumber(createdPatient.getPatientNumber())
                .diagnosisList(createdPatient.getDiagnosisList())
                .build();

        return ResponseEntity.ok(patientDTO);
    }
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable long patientId) {
        // Delete the Patient entity from the database
        patientService.deletePatientById(patientId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable long patientId) {
        Patient patient = patientService.fetchPatientById(patientId);
        PatientDTO patientDTO = patientService.convertToDTO(patient);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        // Retrieve all the Patient entities from the database
        List<Patient> patients = patientService.fetchPatientList();

        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long patientId, @RequestBody Patient updatedPatient) {
        Patient existingPatient = patientService.fetchPatientById(patientId);
        existingPatient.setPatientName(updatedPatient.getPatientName());
        existingPatient.setPatientAddress(updatedPatient.getPatientAddress());
        existingPatient.setPatientNumber(updatedPatient.getPatientNumber());

        List<Diagnosis> updatedDiagnosisList = updatedPatient.getDiagnosisList();
        if (updatedDiagnosisList != null) {
            // Clear the existing diagnosis list for the patient
            existingPatient.getDiagnosisList().clear();

            diagnosisService.deleteDiagnosisByPatientId(patientId);
            // Iterate over the updated diagnosis list and create/update Diagnosis entities
            for (Diagnosis updatedDiagnosis : updatedDiagnosisList) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setDescription(updatedDiagnosis.getDescription());
                diagnosis.setDisease(updatedDiagnosis.getDisease());
                diagnosis.setPatient(existingPatient);

                // Save the Diagnosis entity in the database
                diagnosisService.saveDiagnosis(diagnosis);
            }

            // Delete the older diagnosis entries from the database
        }

        // Update the Patient entity in the database
        Patient savedPatient = patientService.savePatient(existingPatient);

        return ResponseEntity.ok(savedPatient);
    }

        // Other endpoints for retrieving, deleting patients, etc.
    }

