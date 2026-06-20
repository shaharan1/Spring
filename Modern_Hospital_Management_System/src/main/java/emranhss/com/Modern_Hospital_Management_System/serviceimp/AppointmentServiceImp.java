package emranhss.com.Modern_Hospital_Management_System.serviceimp;

import emranhss.com.Modern_Hospital_Management_System.dto.mapper.AppointmentMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.AppointmentRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.AppointmentResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Appointment;
import emranhss.com.Modern_Hospital_Management_System.entity.Doctor;
import emranhss.com.Modern_Hospital_Management_System.entity.Patient;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.AppointmentRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.DoctorRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.PatientRepository;
import emranhss.com.Modern_Hospital_Management_System.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    @Transactional
    public AppointmentResponse bookAppointment(AppointmentRequest request) {

        // 1. Double-Booking Validation Check
        boolean slotTaken = appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                request.getDoctorId(), request.getAppointmentDate(), request.getAppointmentTime()
        );
        if (slotTaken) {
            throw new IllegalStateException("This time slot is already booked for this doctor. Please pick another time.");
        }

        Patient patient;

        // 2. Resolve Patient Profile (Registered vs Public Normal Person)
        if (request.getPatientId() != null) {
            patient = patientRepository.findById(request.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));
        } else {
            if (request.getMobileNumber() == null || request.getPatientName() == null) {
                throw new IllegalArgumentException("Patient name and mobile number are required for public bookings.");
            }

            patient = patientRepository.findByPhone(request.getMobileNumber())
                    .orElseGet(() -> {
                        Patient newPatient = new Patient();

                        String fullName = request.getPatientName().trim();
                        int spaceIndex = fullName.lastIndexOf(' ');
                        if (spaceIndex >= 0) {
                            newPatient.setFirstName(fullName.substring(0, spaceIndex).trim());
                            newPatient.setLastName(fullName.substring(spaceIndex).trim());
                        } else {
                            newPatient.setFirstName(fullName);
                            newPatient.setLastName("");
                        }

                        newPatient.setPhone(request.getMobileNumber());
                        newPatient.setActive(true);

                        String randomCode = "PAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                        newPatient.setPatientCode(randomCode);

                        return patientRepository.save(newPatient);
                    });
        }

        // 3. Resolve Doctor Profile
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));

        // 4. Construct and Save Appointment Details
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus("CONFIRMED");

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    @Override
    @Transactional
    public AppointmentResponse cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));

        appointment.setStatus("CANCELLED");
        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    @Override
    @Transactional(readOnly = true) // Fix: Prevents LazyInitializationException for patient/doctor loading
    public List<AppointmentResponse> viewSchedule(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date).stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true) // Fix: Prevents LazyInitializationException for patient/doctor loading
    public List<AppointmentResponse> getDoctorWiseAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
