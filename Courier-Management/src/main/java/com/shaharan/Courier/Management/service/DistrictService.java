package com.shaharan.Courier.Management.service;

import com.shaharan.Courier.Management.dto.response.DistrictResponseDTO;
import com.shaharan.Courier.Management.entity.District;

import java.util.List;
import java.util.Optional;

public interface DistrictService {


    District save(District district);
    List<District> findAll();
    Optional<District> getById(Long id);
    void delete (Long id);


    List<DistrictResponseDTO> findByDivisionId(Long divisionId);


    List<DistrictResponseDTO> findByDivisionName(String divisionName);
}
