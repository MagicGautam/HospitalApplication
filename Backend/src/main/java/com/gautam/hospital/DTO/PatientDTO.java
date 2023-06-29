package com.gautam.hospital.DTO;

import com.gautam.hospital.entity.Diagnosis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDTO {
    private long patientId;
    private String patientName;
    private String patientAddress;
    private long patientNumber;
    private List<Diagnosis> diagnosisList;

}
