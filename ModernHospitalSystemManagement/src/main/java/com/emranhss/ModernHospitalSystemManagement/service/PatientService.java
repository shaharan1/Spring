package com.emranhss.ModernHospitalSystemManagement.service;


import com.emranhss.ModernHospitalSystemManagement.dto.PatientDTO;

import java.util.List;

public interface PatientService {


    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO getPatientById(Long id);
    PatientDTO getPatientByCode(String patientCode);
    List<PatientDTO> getAllPatients();
    PatientDTO updatePatient(Long id, PatientDTO patientDTO);
    void deletePatient(Long id);
}
