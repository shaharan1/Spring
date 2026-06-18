package emranhss.com.Modern_Hospital_Management_System.dto.mapper;


import emranhss.com.Modern_Hospital_Management_System.dto.response.AppointmentResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {



    public AppointmentResponse toResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());

        if (appointment.getPatient() != null) {
            response.setPatientId(appointment.getPatient().getId());
            response.setPatientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
            response.setPatientPhone(appointment.getPatient().getPhone());
        }

        if (appointment.getDoctor() != null) {
            response.setDoctorId(appointment.getDoctor().getId());
            response.setDoctorName(appointment.getDoctor().getName());
            response.setDoctorSpecialization(appointment.getDoctor().getSpecialization());
        }

        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setAppointmentTime(appointment.getAppointmentTime());
        response.setStatus(appointment.getStatus());

        return response;
    }
}
