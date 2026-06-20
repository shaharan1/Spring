package emranhss.com.Modern_Hospital_Management_System.serviceimp;

import emranhss.com.Modern_Hospital_Management_System.dto.mapper.PrescriptionMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.PrescriptionRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.PrescriptionResponse; // Changed from PrescriptionItemResponse
import emranhss.com.Modern_Hospital_Management_System.entity.*;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.AppointmentRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.DoctorRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.PatientRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.PrescriptionRepository;
import emranhss.com.Modern_Hospital_Management_System.service.PrescriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImp implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionMapper prescriptionMapper;

    @Override
    @Transactional
    // FIX: Changed return type from PrescriptionItemResponse to PrescriptionResponse
    public PrescriptionResponse createPrescription(PrescriptionRequest request) {


        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + request.getAppointmentId()));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setChiefComplaints(request.getChiefComplaints());
        prescription.setSymptoms(request.getSymptoms());
        prescription.setBloodPressure(request.getBloodPressure());
        prescription.setPulseRate(request.getPulseRate());
        prescription.setBodyTemperature(request.getBodyTemperature());
        prescription.setWeight(request.getWeight());
        prescription.setNotes(request.getNotes());
        prescription.setNextFollowUpDate(request.getNextFollowUpDate());

        if (request.getPrescriptionItems() != null) {
            List<PrescriptionItem> items = request.getPrescriptionItems().stream().map(itemDto -> {
                PrescriptionItem item = new PrescriptionItem();
                item.setPrescription(prescription);
                item.setMedicineType(itemDto.getMedicineType());
                item.setMedicineName(itemDto.getMedicineName());
                item.setDosage(itemDto.getDosage());
                item.setDuration(itemDto.getDuration());
                item.setInstruction(itemDto.getInstruction());
                return item;
            }).collect(Collectors.toList());
            prescription.setPrescriptionItems(items);
        }

        return prescriptionMapper.toResponse(prescriptionRepository.save(prescription));
    }

    @Override
    public PrescriptionResponse getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + id));
        return prescriptionMapper.toResponse(prescription);
    }

    @Override
    public List<PrescriptionResponse> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId).stream()
                .map(prescriptionMapper::toResponse)
                .collect(Collectors.toList());
    }
}
