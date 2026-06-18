package com.emranhss.ModernHospitalSystemManagement.repository;


import com.emranhss.ModernHospitalSystemManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {



    Optional<Patient> findByPatientCode(String patientCode);
    Optional<Patient> findByPhone(String phone);
    Optional<Patient> findByEmail(String email);
}
