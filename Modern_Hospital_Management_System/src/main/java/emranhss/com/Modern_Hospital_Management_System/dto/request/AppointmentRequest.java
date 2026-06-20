package emranhss.com.Modern_Hospital_Management_System.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

    // Populated if a pre-registered/logged-in patient is booking (Optional)
    private Long patientId;

    // Populated when a normal/guest person fills out the public form
    private String patientName;
    private String mobileNumber;
    private String specialization;

    // Booking parameters
    private Long doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
