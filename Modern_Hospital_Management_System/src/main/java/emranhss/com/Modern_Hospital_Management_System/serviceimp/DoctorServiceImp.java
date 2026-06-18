package emranhss.com.Modern_Hospital_Management_System.serviceimp;


import emranhss.com.Modern_Hospital_Management_System.dto.mapper.DoctorMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.DoctorRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.DoctorResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Doctor;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.DoctorRepository;
import emranhss.com.Modern_Hospital_Management_System.service.DoctorService;
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
    public DoctorResponse createDoctor(DoctorRequest request) {
        Doctor doctor = doctorMapper.toEntity(request);
        return doctorMapper.toResponse(doctorRepository.save(doctor));
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
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
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorMapper.updateEntityFromRequest(request, doctor);
        return doctorMapper.toResponse(doctorRepository.save(doctor));
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }
}
