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
    private String name;               // Full name from landing page
    private String phone;              // Track visit frequency
    private String problemDescription; // Patient problem statement
    private Long doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String paymentMethod;      // bKash, Cash, Bank
    private String transactionId;      // Required for digital gateways
}
