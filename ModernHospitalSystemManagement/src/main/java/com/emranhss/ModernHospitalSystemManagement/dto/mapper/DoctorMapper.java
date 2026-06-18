package com.emranhss.ModernHospitalSystemManagement.dto.mapper;


import com.emranhss.ModernHospitalSystemManagement.dto.request.DoctorRequest;
import com.emranhss.ModernHospitalSystemManagement.dto.response.DoctorResponse;
import com.emranhss.ModernHospitalSystemManagement.entity.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {


    // Convert Request DTO to Database Entity
    public Doctor toEntity(DoctorRequest request) {
        if (request == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(request, doctor);
        return doctor;
    }

    // Convert Database Entity to Response DTO
    public DoctorResponse toResponse(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        DoctorResponse response = new DoctorResponse();
        BeanUtils.copyProperties(doctor, response);
        return response;
    }

    // Update existing Entity from Request DTO details
    public void updateEntityFromRequest(DoctorRequest request, Doctor doctor) {
        if (request == null || doctor == null) {
            return;
        }
        BeanUtils.copyProperties(request, doctor, "id");
    }
}
