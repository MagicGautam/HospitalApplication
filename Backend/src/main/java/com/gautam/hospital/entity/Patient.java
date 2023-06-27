package com.gautam.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Diagnosis> diagnosisList;

}
