package com.shaharan.Courier.Management.serviceimp;


import com.shaharan.Courier.Management.dto.DivisionDTO;
import com.shaharan.Courier.Management.dto.mapper.DivisionMapper;
import com.shaharan.Courier.Management.dto.response.DivisionResponseDTO;
import com.shaharan.Courier.Management.entity.Country;
import com.shaharan.Courier.Management.entity.Division;
import com.shaharan.Courier.Management.repository.CountryRepository;
import com.shaharan.Courier.Management.repository.DivisionRepository;
import com.shaharan.Courier.Management.service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DivisionServiceImp implements DivisionService {


    private final DivisionRepository divisionRepository;

    private final CountryRepository countryRepository;

    @Override
    public Division save(Division d) {

        Long countryId = d.getCountry().getId();
        Country c= countryRepository.findById(countryId)
                .orElseThrow(()-> new RuntimeException("Country not found with this ID"));
        d.setCountry(c);
        return divisionRepository.save(d);
    }

    @Override
    public List<Division> findAll() {
        return List.of();
    }

    @Override
    public Optional<Division> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DivisionResponseDTO> getDivisionsByCountryId(Long countryId) {
        return List.of();
    }

    @Override
    public List<DivisionResponseDTO> getDivisionsByCountryName(String countryName) {
        return List.of();
    }
}