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
import java.util.Optional;
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
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));

        // Check if the patient exists by phone number
        Optional<Patient> existingPatient = patientRepository.findByPhone(request.getPhone());
        Patient patient;
        double chargeFee;

        if (existingPatient.isPresent()) {
            patient = existingPatient.get();
            chargeFee = doctor.getFollowUpFee(); // Returning visit fee
        } else {
            patient = new Patient();
            patient.setFirstName(request.getName());
            patient.setPhone(request.getPhone());
            patient.setActive(true);
            patient.setPatientCode("PAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            patient = patientRepository.save(patient);

            chargeFee = doctor.getConsultationFee(); // First visit fee
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setProblemDescription(request.getProblemDescription());
        appointment.setFeeCharged(chargeFee);
        appointment.setPaymentMethod(request.getPaymentMethod());

        // Process payment method
        if ("Cash".equalsIgnoreCase(request.getPaymentMethod())) {
            appointment.setStatus("CONFIRMED");
            sendSmsNotification(patient.getPhone(), "Your booking is confirmed via Cash payment.");
        } else {
            // bKash, Bank options
            if (request.getTransactionId() != null && !request.getTransactionId().isBlank()) {
                appointment.setTransactionId(request.getTransactionId());
                appointment.setStatus("CONFIRMED");
                sendSmsNotification(patient.getPhone(), "Payment verified. Your booking is CONFIRMED. TxID: " + request.getTransactionId());
            } else {
                appointment.setStatus("PENDING_PAYMENT");
            }
        }

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    @Override
    public Double calculateFeeForPhone(String phone, Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        return isReturningPatient(phone) ? doctor.getFollowUpFee() : doctor.getConsultationFee();
    }

    @Override
    public Boolean isReturningPatient(String phone) {
        return patientRepository.findByPhone(phone).isPresent();
    }

    private void sendSmsNotification(String targetPhone, String messageBody) {
        // Concrete deployment hook for your SMS provider
        System.out.println("SMS sent to " + targetPhone + " Content: " + messageBody);
    }

    @Override
    public AppointmentResponse cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        appointment.setStatus("CANCELLED");
        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentResponse> viewSchedule(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date).stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getDoctorWiseAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
