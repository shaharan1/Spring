package emranhss.com.Modern_Hospital_Management_System.repository;

import emranhss.com.Modern_Hospital_Management_System.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository {



    Optional<Patient> findByPatientCode(String patientCode);
    Optional<Patient> findByPhone(String phone);
    Optional<Patient> findByEmail(String email);
}
