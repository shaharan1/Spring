package com.shaharan.Courier.Management.serviceimp;


import com.shaharan.Courier.Management.dto.response.DistrictResponseDTO;
import com.shaharan.Courier.Management.entity.District;
import com.shaharan.Courier.Management.entity.Division;
import com.shaharan.Courier.Management.repository.DistrictRepository;
import com.shaharan.Courier.Management.repository.DivisionRepository;
import com.shaharan.Courier.Management.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImp implements DistrictService {

private final DistrictRepository districtRepository;


private final DivisionRepository divisionRepository;


    @Override
    public District save(District d) {

        Long divisionId=d.getDivision().getId();
        Division dvi=divisionRepository.findById(divisionId)
                .orElseThrow(()-> new RuntimeException("Division Not found With this id"));

        d.setDivision(dvi);
        return districtRepository.save(d);
    }

    @Override
    public List<District> findAll() {
        return List.of();
    }

    @Override
    public Optional<District> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DistrictResponseDTO> findByDivisionId(Long divisionId) {
        return List.of();
    }

    @Override
    public List<DistrictResponseDTO> findByDivisionName(String divisionName) {
        return List.of();
    }
}
