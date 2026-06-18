package emranhss.com.Modern_Hospital_Management_System.repository;

import emranhss.com.Modern_Hospital_Management_System.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {



    Optional<Patient> findByPatientCode(String patientCode);
    Optional<Patient> findByPhone(String phone);
    Optional<Patient> findByEmail(String email);
}
