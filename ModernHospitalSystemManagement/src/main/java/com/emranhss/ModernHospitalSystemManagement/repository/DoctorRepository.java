package com.emranhss.ModernHospitalSystemManagement.repository;


import com.emranhss.ModernHospitalSystemManagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByPhone(String phone);
    Optional<Doctor> findByRegistrationNumber(String registrationNumber);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByStatus(String status);


}
