package com.emranhss.ModernHospitalSystemManagement.controller;

import com.emranhss.ModernHospitalSystemManagement.entity.policeStation;
import com.emranhss.ModernHospitalSystemManagement.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policeStation/")
public class PoliceStationController {

    @Autowired
    private PoliceStationService stationService;

    @PostMapping
    public void save(@RequestBody policeStation p){

        stationService.SaveORUpdate(p);
    }
}
