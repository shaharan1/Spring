package emranhss.com.Modern_Hospital_Management_System.service;

import emranhss.com.Modern_Hospital_Management_System.dto.request.ScheduleSlotRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.ScheduleSlotResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleSlotService {


    ScheduleSlotResponse createSlot(ScheduleSlotRequest request);
    List<ScheduleSlotResponse> getAvailableSlotsByDoctor(Long doctorId, LocalDate date);
    ScheduleSlotResponse toggleSlotBookingStatus(Long id, Boolean isBooked);
}
