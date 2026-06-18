package com.emranhss.ModernHospitalSystemManagement.controller;


import com.emranhss.ModernHospitalSystemManagement.dto.request.DoctorRequest;
import com.emranhss.ModernHospitalSystemManagement.dto.response.DoctorResponse;
import com.emranhss.ModernHospitalSystemManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class DoctorController {


    private final DoctorService doctorService;



    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody DoctorRequest doctorRequest) {
        DoctorResponse createdDoctor = doctorService.createDoctor(doctorRequest);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        DoctorResponse doctorResponse = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorResponse);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<DoctorResponse> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialization(@PathVariable String specialization) {
        List<DoctorResponse> doctors = doctorService.getDoctorsBySpecialization(specialization);
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequest doctorRequest) {
        DoctorResponse updatedDoctor = doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }
}
