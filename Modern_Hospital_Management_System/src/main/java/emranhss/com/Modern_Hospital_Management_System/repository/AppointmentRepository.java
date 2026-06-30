package emranhss.com.Modern_Hospital_Management_System.repository;

import emranhss.com.Modern_Hospital_Management_System.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByAppointmentDate(LocalDate date);

    List<Appointment> findByDoctorId(Long doctorId);

    boolean existsByPhoneAndDoctorIdAndStatus(String phone, Long doctorId, String status);

    boolean existsByPhoneAndStatus(String phone, String status);
}
