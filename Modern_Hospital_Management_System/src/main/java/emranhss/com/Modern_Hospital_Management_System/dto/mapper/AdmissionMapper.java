package emranhss.com.Modern_Hospital_Management_System.dto.mapper;


import emranhss.com.Modern_Hospital_Management_System.dto.response.AdmissionResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.AdmittedPatient;
import emranhss.com.Modern_Hospital_Management_System.entity.BedBooking;
import org.springframework.stereotype.Component;

@Component
public class AdmissionMapper {



    public AdmissionResponse toResponse(AdmittedPatient admittedPatient, BedBooking activeBooking) {
        if (admittedPatient == null) return null;

        AdmissionResponse resp = new AdmissionResponse();
        resp.setAdmissionId(admittedPatient.getId());
        resp.setInitialDiagnosis(admittedPatient.getInitialDiagnosis());
        resp.setAdmissionDate(admittedPatient.getAdmissionDate());
        resp.setStatus(admittedPatient.getAdmissionStatus());

        if (admittedPatient.getPatient() != null) {
            resp.setPatientId(admittedPatient.getPatient().getId());
            resp.setPatientCode(admittedPatient.getPatient().getPatientCode());
            resp.setPatientName(admittedPatient.getPatient().getName() + " " + admittedPatient.getPatient());
        }

        if (admittedPatient.getPrimaryDoctor() != null) {
            resp.setDoctorName(admittedPatient.getPrimaryDoctor().getUser().getName());
        }

        if (activeBooking != null && activeBooking.getBed() != null) {
            resp.setAssignedBedNumber(activeBooking.getBed().getBedNumber());
            if (activeBooking.getBed().getWard() != null) {
                resp.setWardName(activeBooking.getBed().getWard().getName());
            }
        }

        return resp;
    }
}
