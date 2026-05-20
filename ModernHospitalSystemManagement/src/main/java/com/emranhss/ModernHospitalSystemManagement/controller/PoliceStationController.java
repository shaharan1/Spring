package com.emranhss.ModernHospitalSystemManagement.controller;

import com.emranhss.ModernHospitalSystemManagement.entity.policeStation;
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
    public ResponseEntity<policeStation> save(@RequestBody policeStation p){

       policeStation savedpoliceStation= stationService.SaveORUpdate(p);
       return new ResponseEntity<>(savedpoliceStation, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<policeStation>>  getAll() {
        List<policeStation> list=stationService.getAll();
        return ResponseEntity.ok(list);
    }


}
