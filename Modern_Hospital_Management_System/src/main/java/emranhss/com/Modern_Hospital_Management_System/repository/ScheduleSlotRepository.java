package emranhss.com.Modern_Hospital_Management_System.repository;


import emranhss.com.Modern_Hospital_Management_System.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {


    List<ScheduleSlot> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    List<ScheduleSlot> findByDoctorIdAndDateAndIsBookedFalse(Long doctorId, LocalDate date);
}
