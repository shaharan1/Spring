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
        return null;
    }

    @Override
    public PatientResponse getPatientByCode(String code) {
        return null;
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return List.of();
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }
}
