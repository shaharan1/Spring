package com.emranhss.ModernHospitalSystemManagement.service;

import com.emranhss.ModernHospitalSystemManagement.entity.policeStation;
import com.emranhss.ModernHospitalSystemManagement.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private PoliceStationRepository stationRepository;

    public List <policeStation> getAll(){

        return stationRepository.findAll();
    }

    public void SaveORUpdate(policeStation p){
        stationRepository.save(p);
    }
    public Optional<policeStation> getByIdPoliceStation(long id){

        return stationRepository.findById(id);
    }

}
