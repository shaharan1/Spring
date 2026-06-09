package com.emranhss.ModernHospitalSystemManagement.repository;


import com.emranhss.ModernHospitalSystemManagement.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository {



    List<Division> findByCountryId(Long countryId);

    List<Division> findByCountryName(String countryName);

}
