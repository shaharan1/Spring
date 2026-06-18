package com.emranhss.ModernHospitalSystemManagement.serviceimp;


import com.emranhss.ModernHospitalSystemManagement.dto.PatientDTO;
import com.emranhss.ModernHospitalSystemManagement.entity.Patient;
import com.emranhss.ModernHospitalSystemManagement.exception.ResourceNotFoundException;
import com.emranhss.ModernHospitalSystemManagement.repository.PatientRepository;
import com.emranhss.ModernHospitalSystemManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {


    private final PatientRepository patientRepository;



    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        Patient savedPatient = patientRepository.save(patient);

        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(savedPatient, responseDTO);
        return responseDTO;
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    @Override
    public PatientDTO getPatientByCode(String patientCode) {
        Patient patient = patientRepository.findByPatientCode(patientCode)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with code: " + patientCode));
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> {
            PatientDTO dto = new PatientDTO();
            BeanUtils.copyProperties(patient, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        BeanUtils.copyProperties(patientDTO, patient, "id");
        Patient updatedPatient = patientRepository.save(patient);

        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(updatedPatient, responseDTO);
        return responseDTO;
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }
}
