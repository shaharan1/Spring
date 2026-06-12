package com.shaharan.Courier.Management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionDTO {


    private Long divisionId;
    private String divisionName;
    private String countryName;
    private Long countryId;
}
