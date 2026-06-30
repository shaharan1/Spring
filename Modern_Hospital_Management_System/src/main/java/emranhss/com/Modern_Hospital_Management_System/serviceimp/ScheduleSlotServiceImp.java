package emranhss.com.Modern_Hospital_Management_System.serviceimp;


import emranhss.com.Modern_Hospital_Management_System.dto.mapper.ScheduleSlotMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.ScheduleSlotRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.ScheduleSlotResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Doctor;
import emranhss.com.Modern_Hospital_Management_System.entity.ScheduleSlot;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.DoctorRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.ScheduleSlotRepository;
import emranhss.com.Modern_Hospital_Management_System.service.ScheduleSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleSlotServiceImp implements ScheduleSlotService {

    private final ScheduleSlotRepository scheduleSlotRepository;
    private final DoctorRepository doctorRepository;
    private final ScheduleSlotMapper mapper;



    @Override
    @Transactional
    public ScheduleSlotResponse createSlot(ScheduleSlotRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor record missing with ID: " + request.getDoctorId()));

        ScheduleSlot slot = new ScheduleSlot();
        slot.setDoctor(doctor);
        slot.setDate(request.getDate());
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setIsBooked(false);

        return mapper.toResponse(scheduleSlotRepository.save(slot));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleSlotResponse> getAvailableSlotsByDoctor(Long doctorId, LocalDate date) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor record missing with ID: " + doctorId);
        }
        return scheduleSlotRepository.findByDoctorIdAndDateAndIsBookedFalse(doctorId, date,true).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ScheduleSlotResponse toggleSlotBookingStatus(Long id, Boolean isBooked) {
        ScheduleSlot slot = scheduleSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule slot missing with ID: " + id));
        slot.setIsBooked(isBooked);
        return mapper.toResponse(scheduleSlotRepository.save(slot));
    }
}
