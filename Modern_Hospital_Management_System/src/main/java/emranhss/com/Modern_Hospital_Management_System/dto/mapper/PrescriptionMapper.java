package emranhss.com.Modern_Hospital_Management_System.dto.mapper;


import emranhss.com.Modern_Hospital_Management_System.dto.response.PrescriptionItemResponse;
import emranhss.com.Modern_Hospital_Management_System.dto.response.PrescriptionResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;

@Component
public class PrescriptionMapper {



    public PrescriptionResponse toResponse(Prescription prescription) {
        if (prescription == null) return null;

        PrescriptionResponse response = new PrescriptionResponse();
        response.setId(prescription.getId());

        if (prescription.getAppointment() != null) {
            response.setAppointmentId(prescription.getAppointment().getId());
        }

        if (prescription.getDoctor() != null) {
            response.setDoctorName(prescription.getDoctor().getName());
            response.setDoctorSpecialization(prescription.getDoctor().getSpecialization());
        }

        if (prescription.getPatient() != null) {
            response.setPatientName(prescription.getPatient().getFirstName() + " " + prescription.getPatient().getLastName());
            if (prescription.getPatient().getDateOfBirth() != null) {
                int age = Period.between(prescription.getPatient().getDateOfBirth(), LocalDate.now()).getYears();
                response.setPatientAge(age + " Years");
            }
        }

        response.setDiagnosis(prescription.getDiagnosis());
        response.setChiefComplaints(prescription.getChiefComplaints());
        response.setSymptoms(prescription.getSymptoms());
        response.setBloodPressure(prescription.getBloodPressure());
        response.setPulseRate(prescription.getPulseRate());
        response.setBodyTemperature(prescription.getBodyTemperature());
        response.setWeight(prescription.getWeight());
        response.setNotes(prescription.getNotes());
        response.setNextFollowUpDate(prescription.getNextFollowUpDate());
        response.setCreatedDate(prescription.getCreatedDate());

        if (prescription.getPrescriptionItems() != null) {
            response.setPrescriptionItems(prescription.getPrescriptionItems().stream().map(item -> {
                PrescriptionItemResponse itemDto = new PrescriptionItemResponse();
                itemDto.setId(item.getId());
                itemDto.setMedicineType(item.getMedicineType());
                itemDto.setMedicineName(item.getMedicineName());
                itemDto.setDosage(item.getDosage());
                itemDto.setDuration(item.getDuration());
                itemDto.setInstruction(item.getInstruction());
                return itemDto;
            }).collect(Collectors.toList()));
        }

        return response;
    }
}
