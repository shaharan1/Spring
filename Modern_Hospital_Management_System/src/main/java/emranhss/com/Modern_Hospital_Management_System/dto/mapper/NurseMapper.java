package emranhss.com.Modern_Hospital_Management_System.dto.mapper;

import emranhss.com.Modern_Hospital_Management_System.dto.request.NurseRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.NurseResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Nurse;
import org.springframework.stereotype.Component;

@Component
public class NurseMapper {

    public Nurse toEntity(NurseRequest request) {
        if (request == null) return null;
        Nurse nurse = new Nurse();
        nurse.setFullName(request.getFullName());
        nurse.setEmail(request.getEmail());
        nurse.setPhone(request.getPhone());
        nurse.setAddress(request.getAddress());
        nurse.setGender(request.getGender());
        nurse.setJoinDate(request.getJoinDate());
        nurse.setPhoto(request.getPhoto());
        nurse.setNurseType(request.getNurseType());
        nurse.setQualification(request.getQualification());
        nurse.setRegistrationNumber(request.getRegistrationNumber());
        nurse.setExperienceYears(request.getExperienceYears());
        nurse.setShift(request.getShift());
        nurse.setWorkingHours(request.getWorkingHours());
        nurse.setOnDuty(request.getOnDuty() != null ? request.getOnDuty() : false);
        nurse.setAssignedWard(request.getAssignedWard());
        nurse.setRemarks(request.getRemarks());
        nurse.setActive(true);
        return nurse;
    }

    public NurseResponse toResponse(Nurse nurse) {
        if (nurse == null) return null;
        return NurseResponse.builder()
                .id(nurse.getId())
                .userId(nurse.getUser() != null ? nurse.getUser().getId() : 0)
                .fullName(nurse.getFullName())
                .email(nurse.getEmail())
                .phone(nurse.getPhone())
                .address(nurse.getAddress())
                .gender(nurse.getGender())
                .joinDate(nurse.getJoinDate())
                .photo(nurse.getPhoto())
                .nurseType(nurse.getNurseType())
                .qualification(nurse.getQualification())
                .registrationNumber(nurse.getRegistrationNumber())
                .experienceYears(nurse.getExperienceYears())
                .shift(nurse.getShift())
                .workingHours(nurse.getWorkingHours())
                .onDuty(nurse.getOnDuty())
                .assignedWard(nurse.getAssignedWard())
                .remarks(nurse.getRemarks())
                .active(nurse.getActive())
                .build();
    }
}
