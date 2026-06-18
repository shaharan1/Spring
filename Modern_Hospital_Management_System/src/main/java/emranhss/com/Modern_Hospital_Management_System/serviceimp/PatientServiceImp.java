package emranhss.com.Modern_Hospital_Management_System.serviceimp;


import emranhss.com.Modern_Hospital_Management_System.dto.mapper.PatientMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.PatientRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.PatientResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Patient;
import emranhss.com.Modern_Hospital_Management_System.repository.PatientRepository;
import emranhss.com.Modern_Hospital_Management_System.service.PatientService;
import emranhss.com.Modern_Hospital_Management_System.util.PatientCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientCodeGenerator codeGenerator;




    @Override
    public PatientResponse createPatient(PatientRequest request) {
        Patient patient = patientMapper.toEntity(request);
        patient.setPatientCode(codeGenerator.generateCode());
        return patientMapper.toResponse(patientRepository.save(patient));
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return patientMapper.toResponse(patient);
    }

    @Override
    public PatientResponse getPatientByCode(String code) {
        Patient patient = patientRepository.findByPatientCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with code: " + code));
        return patientMapper.toResponse(patient);
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientMapper.updateEntityFromRequest(request, patient);
        return patientMapper.toResponse(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }
}
