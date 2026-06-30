package emranhss.com.Modern_Hospital_Management_System.controller;

import emranhss.com.Modern_Hospital_Management_System.dto.request.MealServeRequest;
import emranhss.com.Modern_Hospital_Management_System.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dietary")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping("/serve")
    public ResponseEntity<MealServeResponse> logMealDelivery(@RequestBody MealServeRequest request) {
        MealServeResponse response = mealService.logMealDelivery(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/patient/{admittedPatientId}/unbilled")
    public ResponseEntity<List<MealServeResponse>> getUnbilledPatientMeals(
            @PathVariable Long admittedPatientId) {
        List<MealServeResponse> response = mealService.getUnbilledPatientMeals(admittedPatientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{admittedPatientId}/pending-balance")
    public ResponseEntity<Double> calculatePendingMealBalance(
            @PathVariable Long admittedPatientId) {
        double balance = mealService.calculatePendingMealBalance(admittedPatientId);
        return ResponseEntity.ok(balance);
    }

    @PutMapping("/patient/{admittedPatientId}/discharge-billing")
    public ResponseEntity<Void> processDischargeBilling(
            @PathVariable Long admittedPatientId) {
        mealService.processDischargeBilling(admittedPatientId);
        return ResponseEntity.noContent().build();
    }
}
