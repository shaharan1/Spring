package com.shaharan.Courier.Management.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponseDTO {


    private Long districtId;
    private  String districtName;
    private Long divisionId;
    private String divisionName;
    private  Long countryId;
    private  String countryName;
    private String countryCode;
}
