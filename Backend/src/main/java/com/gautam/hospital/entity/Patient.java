package com.gautam.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
