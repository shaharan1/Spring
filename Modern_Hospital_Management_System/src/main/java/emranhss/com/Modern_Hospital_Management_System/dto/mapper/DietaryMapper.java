package emranhss.com.Modern_Hospital_Management_System.dto.mapper;


import emranhss.com.Modern_Hospital_Management_System.dto.response.MealServeResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Meal;
import org.springframework.stereotype.Component;

@Component
public class DietaryMapper {


    public MealServeResponse toResponse(Meal meal) {
        if (meal == null) return null;

        MealServeResponse response = new MealServeResponse();
        response.setId(meal.getId());
        response.setMealCost(meal.getMealCost());
        response.setServedAt(meal.getServedAt());
        response.setBillingStatus(meal.getBillingStatus());

        // Extract deep catalog definitions cleanly
        if (meal.getMealMaster() != null) {
            response.setMealName(meal.getMealMaster().getName());
            response.setMealCategory(meal.getMealMaster().getCategory());
            response.setMealType(meal.getMealMaster().getType());
        }

        // Map inpatient details without triggering recursive string loops
        if (meal.getAdmittedPatient() != null) {
            response.setAdmittedPatientId(meal.getAdmittedPatient().getId());
            if (meal.getAdmittedPatient().getPatient() != null) {
                response.setPatientName(meal.getAdmittedPatient().getPatient().getFirstName() + " " +
                        meal.getAdmittedPatient().getPatient().getLastName());
            }
        }

        // Flatten contextual bed location configurations
        if (meal.getBedBooking() != null && meal.getBedBooking().getBed() != null) {
            response.setBedNumber(meal.getBedBooking().getBed().getBedNumber());
        }

        return response;
    }
    
}
