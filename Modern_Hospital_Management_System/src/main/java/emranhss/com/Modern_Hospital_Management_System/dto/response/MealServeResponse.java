package emranhss.com.Modern_Hospital_Management_System.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MealServeResponse {

    private Long id;
    private String mealName;
    private String mealCategory;
    private String mealType;
    private double mealCost;
    private LocalDateTime servedAt;
    private Long admittedPatientId;
    private String patientName;
    private String bedNumber;
    private String billingStatus;

}
