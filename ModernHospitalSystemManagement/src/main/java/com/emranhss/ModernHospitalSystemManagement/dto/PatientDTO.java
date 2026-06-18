package com.emranhss.ModernHospitalSystemManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String patientCode;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private String maritalStatus;
    private String phone;
    private String alternatePhone;
    private String email;
    private String nationalId;
    private String address;
    private String city;
    private String district;
    private String postalCode;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String relationship;
    private Boolean active;
}
