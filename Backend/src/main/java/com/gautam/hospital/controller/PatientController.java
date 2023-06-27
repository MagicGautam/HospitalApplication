package com.gautam.hospital.controller;

import com.gautam.hospital.entity.Diagnosis;
import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.service.DiagnosisService;
import com.gautam.hospital.service.DiagnosisServiceImpl;
import com.gautam.hospital.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/patients")
    public class PatientController {
        private final PatientService patientService;
        private final DiagnosisService diagnosisService;

        public PatientController(PatientService patientService, DiagnosisService diagnosisService) {
            this.patientService = patientService;
            this.diagnosisService = diagnosisService;
        }
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        // Save the Patient entity in the database
        Patient createdPatient = patientService.savePatient(patient);

        // Process the diagnosisList
        List<Diagnosis> diagnosisList = patient.getDiagnosisList();
        if (diagnosisList != null && !diagnosisList.isEmpty()) {
            // Iterate over the diagnosisList and create/update Diagnosis entities
            for (Diagnosis diagnosis : diagnosisList) {
                // Set the patient attribute of the Diagnosis entity
                diagnosis.setPatient(createdPatient);

                // Save the Diagnosis entity in the database
                diagnosisService.saveDiagnosis(diagnosis);
            }
        }

        return ResponseEntity.ok(createdPatient);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable long patientId) {
        // Delete the Patient entity from the database
        patientService.deletePatientById(patientId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable long patientId) {
        // Retrieve the Patient entity from the database
        Patient patient = patientService.fetchPatientById(patientId);

        return ResponseEntity.ok(patient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        // Retrieve all the Patient entities from the database
        List<Patient> patients = patientService.fetchPatientList();

        return ResponseEntity.ok(patients);
    }

        @PutMapping("/{patientId}")
        public ResponseEntity<Patient> updatePatient(@PathVariable long patientId, @RequestBody Patient patient) {
            // Map the Patient JSON object to the Patient entity
            Patient existingPatient = patientService.fetchPatientById(patientId);
            existingPatient.setPatientName(patient.getPatientName());
            existingPatient.setPatientAddress(patient.getPatientAddress());
            existingPatient.setPatientNumber(patient.getPatientNumber());

            // Update the Patient entity in the database
            Patient updatedPatient = patientService.savePatient(existingPatient);

            // Process the diagnosisList
            List<Diagnosis> diagnosisList = patient.getDiagnosisList();
            if (diagnosisList != null && !diagnosisList.isEmpty()) {
                // Clear the existing diagnosisList for the patient
                existingPatient.getDiagnosisList().clear();

                // Iterate over the diagnosisList and create/update Diagnosis entities
                for (Diagnosis diagnosis : diagnosisList) {
                    // Set the patient attribute of the Diagnosis entity
                    diagnosis.setPatient(existingPatient);

                    // Save the Diagnosis entity in the database
                    diagnosisService.saveDiagnosis(diagnosis);
                }
            }

            return ResponseEntity.ok(updatedPatient);
        }

        // Other endpoints for retrieving, deleting patients, etc.
    }

