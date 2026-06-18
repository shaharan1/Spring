package com.emranhss.ModernHospitalSystemManagement.serviceimp;


import com.emranhss.ModernHospitalSystemManagement.dto.mapper.DoctorMapper;
import com.emranhss.ModernHospitalSystemManagement.dto.request.DoctorRequest;
import com.emranhss.ModernHospitalSystemManagement.dto.response.DoctorResponse;
import com.emranhss.ModernHospitalSystemManagement.entity.Doctor;
import com.emranhss.ModernHospitalSystemManagement.exception.ResourceNotFoundException;
import com.emranhss.ModernHospitalSystemManagement.repository.DoctorRepository;
import com.emranhss.ModernHospitalSystemManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {


    private final DoctorRepository doctorRepository;


    private final DoctorMapper doctorMapper;


    @Override
    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = doctorMapper.toEntity(doctorRequest);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toResponse(savedDoctor);
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = (Doctor) doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponse> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization).stream()
                .map(doctorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = (Doctor) doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        doctorMapper.updateEntityFromRequest(doctorRequest, doctor);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toResponse(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = (Doctor) doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }
}
