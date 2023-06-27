package com.gautam.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long diagnosisId;
    private String disease;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}