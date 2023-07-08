package com.gautam.hospital.controller;

import com.gautam.hospital.entity.Patient;
import com.gautam.hospital.service.DiagnosisService;
import com.gautam.hospital.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;
    @MockBean
    private DiagnosisService diagnosisService;

    @Test
    public void shouldReturnAllUsersForUnauthenticatedUsers() throws Exception {
        when(patientService.fetchPatientList())
                .thenReturn(List.of(new Patient("Gautam", "Pune", 1234567890L),
                        new Patient("Khushi", "Bangalore", 1234222142L)));
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientName").value("Gautam"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientAddress").value("Pune"));
    }
    @Test
    public void shouldReturn404WhenUserIsNotFound() throws Exception {
        when(patientService.fetchPatientByName("Khushis"))
                .thenThrow(new RuntimeException("Patient Does Not Exists"));

        this.mockMvc.perform(get("/patient/Khushis"))
                   .andExpect(status().isNotFound());
    }
}