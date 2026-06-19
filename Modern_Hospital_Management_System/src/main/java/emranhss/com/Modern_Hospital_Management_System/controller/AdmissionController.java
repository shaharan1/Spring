package emranhss.com.Modern_Hospital_Management_System.controller;


import emranhss.com.Modern_Hospital_Management_System.dto.request.AdmissionRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.AdmissionResponse;
import emranhss.com.Modern_Hospital_Management_System.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdmissionController {



    private final AdmissionService admissionService;

    @PostMapping("/admit")
    public ResponseEntity<AdmissionResponse> admitPatient(@RequestBody AdmissionRequest request) {
        return new ResponseEntity<>(admissionService.admitPatient(request), HttpStatus.CREATED);
    }

    @PutMapping("/discharge/{id}")
    public ResponseEntity<AdmissionResponse> dischargePatient(@PathVariable Long id) {
        return ResponseEntity.ok(admissionService.dischargePatient(id));
    }

}
