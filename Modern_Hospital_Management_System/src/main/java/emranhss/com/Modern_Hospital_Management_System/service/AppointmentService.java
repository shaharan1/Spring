package emranhss.com.Modern_Hospital_Management_System.service;


import emranhss.com.Modern_Hospital_Management_System.dto.request.AppointmentRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.AppointmentResponse;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {


    AppointmentResponse bookAppointment(AppointmentRequest request);
    AppointmentResponse cancelAppointment(Long id);
    List<AppointmentResponse> viewSchedule(LocalDate date);
    List<AppointmentResponse> getDoctorWiseAppointments(Long doctorId);
}
