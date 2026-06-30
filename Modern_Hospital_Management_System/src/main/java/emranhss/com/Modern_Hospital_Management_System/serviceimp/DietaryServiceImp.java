package emranhss.com.Modern_Hospital_Management_System.serviceimp;


import emranhss.com.Modern_Hospital_Management_System.dto.request.MealServeRequest;
import emranhss.com.Modern_Hospital_Management_System.entity.Meal;
import emranhss.com.Modern_Hospital_Management_System.entity.MealMaster;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.MealRepository;
import emranhss.com.Modern_Hospital_Management_System.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietaryServiceImp implements MealService {


    private final MealRepository mealRepository;
    private final DietaryMapper dietaryMapper;

    @Override
    @Transactional
    public MealServeResponse logMealDelivery(MealServeRequest request) {
        MealMaster mealMaster = mealRepository.findById(request.getMealMasterId())
                .orElseThrow(() -> new ResourceNotFoundException("Meal configuration option not found")).getMealMaster();

        Meal meal = new Meal();
        meal.setMealMaster(mealMaster);
        meal.setMealCost(mealMaster.getPrice());
        meal.setServedAt(LocalDateTime.now());
        meal.setBillingStatus("PENDING");

        // Note: Real implementations should fetch complete managed entities from
        // AdmittedPatientRepository and BedBookingRepository to support deep mapping!

        Meal saved = mealRepository.save(meal);
        return dietaryMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MealServeResponse> getUnbilledPatientMeals(Long admittedPatientId) {
        return mealRepository.findByAdmittedPatientIdAndBillingStatus(admittedPatientId, "PENDING")
                .stream()
                .map(dietaryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public double calculatePendingMealBalance(Long admittedPatientId) {
        return mealRepository.findByAdmittedPatientIdAndBillingStatus(admittedPatientId, "PENDING")
                .stream()
                .mapToDouble(Meal::getMealCost)
                .sum();
    }

    @Override
    @Transactional
    public void processDischargeBilling(Long admittedPatientId) {
        List<Meal> pendingMeals = mealRepository.findByAdmittedPatientIdAndBillingStatus(admittedPatientId, "PENDING");
        for (Meal meal : pendingMeals) {
            meal.setBillingStatus("BILLED");
        }
        mealRepository.saveAll(pendingMeals);
    }
}
