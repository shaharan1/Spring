package emranhss.com.Modern_Hospital_Management_System.dto.mapper;


import emranhss.com.Modern_Hospital_Management_System.dto.request.MedicineRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.MedicineResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Medicine;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper {



    public Medicine toEntity(MedicineRequest request, Prescription prescription) {
        Medicine medicine = new Medicine();
        medicine.setMedicineName(request.getMedicineName());
        medicine.setGenericName(request.getGenericName());
        medicine.setDosage(request.getDosage());
        medicine.setFrequency(request.getFrequency());
        medicine.setRoute(request.getRoute());
        medicine.setDuration(request.getDuration());
        medicine.setApplyWay(request.getApplyWay());
        medicine.setQuantity(request.getQuantity());
        medicine.setStartDate(request.getStartDate());
        medicine.setInstructions(request.getInstructions());
        medicine.setActive(request.getActive());
        medicine.setPrescription(prescription);
        return medicine;
    }

    public void updateEntity(Medicine medicine, MedicineRequest request, Prescription prescription) {
        medicine.setMedicineName(request.getMedicineName());
        medicine.setGenericName(request.getGenericName());
        medicine.setDosage(request.getDosage());
        medicine.setFrequency(request.getFrequency());
        medicine.setRoute(request.getRoute());
        medicine.setDuration(request.getDuration());
        medicine.setApplyWay(request.getApplyWay());
        medicine.setQuantity(request.getQuantity());
        medicine.setStartDate(request.getStartDate());
        medicine.setInstructions(request.getInstructions());
        medicine.setActive(request.getActive());
        medicine.setPrescription(prescription);
    }

    public MedicineResponse toResponse(Medicine medicine) {
        MedicineResponse response = new MedicineResponse();
        response.setId(medicine.getId());
        response.setMedicineName(medicine.getMedicineName());
        response.setGenericName(medicine.getGenericName());
        response.setDosage(medicine.getDosage());
        response.setFrequency(medicine.getFrequency());
        response.setRoute(medicine.getRoute());
        response.setDuration(medicine.getDuration());
        response.setApplyWay(medicine.getApplyWay());
        response.setQuantity(medicine.getQuantity());
        response.setStartDate(medicine.getStartDate());
        response.setInstructions(medicine.getInstructions());
        response.setActive(medicine.isActive());
        if (medicine.getPrescription() != null) {
            response.setPrescriptionId(medicine.getPrescription().getId());
        }
        return response;
    }
}
