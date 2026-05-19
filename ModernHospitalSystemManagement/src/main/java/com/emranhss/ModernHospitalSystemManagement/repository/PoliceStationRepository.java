package com.emranhss.ModernHospitalSystemManagement.repository;

import com.emranhss.ModernHospitalSystemManagement.entity.policeStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationRepository extends JpaRepository<policeStation,Long> {

}
