package emranhss.com.Modern_Hospital_Management_System.dto.mapper;


import emranhss.com.Modern_Hospital_Management_System.dto.request.DoctorRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.DoctorResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {


    public Doctor toEntity(DoctorRequest request) {
        if (request == null) return null;
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(request, doctor);
        return doctor;
    }

    public DoctorResponse toResponse(Doctor doctor) {
        if (doctor == null) return null;
        DoctorResponse response = new DoctorResponse();
        BeanUtils.copyProperties(doctor, response);
        return response;
    }

    public void updateEntityFromRequest(DoctorRequest request, Doctor doctor) {
        if (request == null || doctor == null) return;
        BeanUtils.copyProperties(request, doctor, "id");
    }
}
