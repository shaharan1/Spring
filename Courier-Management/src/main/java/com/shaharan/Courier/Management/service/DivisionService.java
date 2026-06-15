package com.shaharan.Courier.Management.service;

import com.shaharan.Courier.Management.dto.response.DivisionResponseDTO;
import com.shaharan.Courier.Management.entity.Division;

import java.util.List;
import java.util.Optional;

public interface DivisionService {

    Division save (Division d);
    List<Division> findAll();
    Optional<Division> getById(Long id);
    void delete(Long id);

List<DivisionResponseDTO> getDivisionsByCountryId(Long countryId);

    List<Division> getDivisionsByCountryId(String countryId);

    List<DivisionResponseDTO> getDivisionsByCountryName(String countryName);
}
