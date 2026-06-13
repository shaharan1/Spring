package com.shaharan.Courier.Management.dto.mapper;

import com.shaharan.Courier.Management.dto.request.DivisionRequestDTO;
import com.shaharan.Courier.Management.dto.response.DivisionResponseDTO;
import com.shaharan.Courier.Management.entity.Division;

public class DivisionMapper {


    public static DivisionResponseDTO toDTO(Division division) {

        DivisionResponseDTO dto = new DivisionResponseDTO();

        dto.setId(division.getId());
        dto.setName(division.getName());
        dto.setNameBn(division.getNameBn());
        dto.setActive(division.getActive());
//        dto.setTotalDistricts(
//                division.getDistricts() != null ? division.getDistricts().size() : 0
//        );

        // Country fields
        if (division.getCountry() != null) {
            dto.setCountryId(division.getCountry().getId());
            dto.setCountryName(division.getCountry().getName());
        }

        return dto;
    }

    // Request DTO → Entity (country must be set separately in service)
    public static Division toEntity(DivisionRequestDTO dto) {

        Division division = new Division();

        division.setName(dto.getName());
        division.setNameBn(dto.getNameBn());
        division.setActive(dto.getActive() != null ? dto.getActive() : true);
        // country is resolved by id in the service layer

        return division;
    }

    // Apply request DTO onto existing entity (for update)
    public static void updateEntity(Division division, DivisionRequestDTO dto) {

        if (dto.getName() != null)   division.setName(dto.getName());
        if (dto.getNameBn() != null) division.setNameBn(dto.getNameBn());
        if (dto.getActive() != null) division.setActive(dto.getActive());
        // countryId change is handled in the service layer
    }
}
