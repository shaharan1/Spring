package emranhss.com.Modern_Hospital_Management_System.dto.request;


import lombok.Data;

@Data
public class MealServeRequest {

    private Long mealMasterId;
    private Long admittedPatientId;
    private Long bedBookingId; // Optional property layer (e.g., fallback if patient is in triage lounge)

}
