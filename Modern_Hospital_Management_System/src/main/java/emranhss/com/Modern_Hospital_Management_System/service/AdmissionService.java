package emranhss.com.Modern_Hospital_Management_System.service;

import emranhss.com.Modern_Hospital_Management_System.dto.request.AdmissionRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.AdmissionResponse;

public interface AdmissionService {

    AdmissionResponse admitPatient(AdmissionRequest request);
    AdmissionResponse dischargePatient(Long admissionId);


}
