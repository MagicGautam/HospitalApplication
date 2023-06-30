package com.gautam.hospital.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long patientId;
    private String patientName;
    private String patientAddress;
    private long patientNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Diagnosis> diagnosisList= new ArrayList<>();

    public Patient(String patientName, String patientAddress, long patientNumber) {
    }
}
