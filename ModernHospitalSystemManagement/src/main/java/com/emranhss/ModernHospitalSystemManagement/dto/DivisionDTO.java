package com.emranhss.ModernHospitalSystemManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionDTO {

    private Integer divisionId;
    private  String divisionName;
    private  String countryName;
    private Integer countryId;

}
