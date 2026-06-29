package emranhss.com.Modern_Hospital_Management_System.service;

import emranhss.com.Modern_Hospital_Management_System.dto.request.MealServeRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.MealServeResponse;
import java.util.List;

public interface MealService {
    MealServeResponse logMealDelivery(MealServeRequest request);
    List<MealServeResponse> getUnbilledPatientMeals(Long admittedPatientId);
    double calculatePendingMealBalance(Long admittedPatientId);
    void processDischargeBilling(Long admittedPatientId);
}
