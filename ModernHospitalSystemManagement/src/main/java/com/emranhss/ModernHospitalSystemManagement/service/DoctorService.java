package com.emranhss.ModernHospitalSystemManagement.service;


import com.emranhss.ModernHospitalSystemManagement.dto.request.DoctorRequest;
import com.emranhss.ModernHospitalSystemManagement.dto.response.DoctorResponse;

import java.util.List;

public interface DoctorService {



    DoctorResponse createDoctor(DoctorRequest doctorRequest);
    DoctorResponse getDoctorById(Long id);
    List<DoctorResponse> getAllDoctors();
    List<DoctorResponse> getDoctorsBySpecialization(String specialization);
    DoctorResponse updateDoctor(Long id, DoctorRequest doctorRequest);
    void deleteDoctor(Long id);
}
