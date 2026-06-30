package emranhss.com.Modern_Hospital_Management_System.repository;

import emranhss.com.Modern_Hospital_Management_System.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {

    // Dynamically matches slots by date, time, and doctor reference
    Optional<ScheduleSlot> findByDoctorIdAndDateAndStartTime(Long doctorId, LocalDate date, LocalTime startTime);

    List<ScheduleSlot> findByDoctorIdAndDateAndIsBookedFalse(Long doctorId, LocalDate date, Boolean isBooked);

    Boolean isBooked(Boolean isBooked);
}
