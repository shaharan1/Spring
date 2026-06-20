package emranhss.com.Modern_Hospital_Management_System.service;

import emranhss.com.Modern_Hospital_Management_System.dto.request.PrescriptionRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.PrescriptionItemResponse;
import emranhss.com.Modern_Hospital_Management_System.dto.response.PrescriptionResponse;

import java.util.List;

public interface PrescriptionService {

    PrescriptionResponse createPrescription(PrescriptionRequest request);
    PrescriptionResponse getPrescriptionById(Long id);
    List<PrescriptionResponse> getPrescriptionsByPatientId(Long patientId);
}
