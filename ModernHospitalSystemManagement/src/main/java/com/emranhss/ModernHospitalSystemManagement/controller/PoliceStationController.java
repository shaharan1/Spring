package com.emranhss.ModernHospitalSystemManagement.controller;

import com.emranhss.ModernHospitalSystemManagement.entity.PoliceStation;
import com.emranhss.ModernHospitalSystemManagement.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policeStation/")
public class PoliceStationController {

    @Autowired
    private PoliceStationService stationService;

    @PostMapping
    public ResponseEntity<PoliceStation> save(@RequestBody PoliceStation p){

       PoliceStation savedpoliceStation= stationService.SaveORUpdate(p);
       return new ResponseEntity<>(savedpoliceStation, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PoliceStation>>  getAll() {
        List<PoliceStation> list=stationService.getAll();
        return ResponseEntity.ok(list);
    }


}
