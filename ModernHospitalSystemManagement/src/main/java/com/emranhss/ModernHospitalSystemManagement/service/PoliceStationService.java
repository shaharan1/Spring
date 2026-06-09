package com.emranhss.ModernHospitalSystemManagement.service;

import com.emranhss.ModernHospitalSystemManagement.entity.PoliceStation;
import com.emranhss.ModernHospitalSystemManagement.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private PoliceStationRepository stationRepository;

    public List <PoliceStation> getAll(){

        return stationRepository.findAll();
    }

    public PoliceStation SaveORUpdate(PoliceStation p){
       return stationRepository.save(p);
    }
    public Optional<PoliceStation> getByIdPoliceStation(long id){

        return stationRepository.findById(id);
    }

    public void delete(long id){

        stationRepository.deleteById(id);
    }


}
