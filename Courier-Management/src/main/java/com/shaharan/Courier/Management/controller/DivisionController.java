package com.shaharan.Courier.Management.controller;


import com.shaharan.Courier.Management.dto.DivisionDTO;
import com.shaharan.Courier.Management.dto.response.DivisionResponseDTO;
import com.shaharan.Courier.Management.entity.Division;
import com.shaharan.Courier.Management.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/division/")

public class DivisionController {


@Autowired
private DivisionService divisionService;


@PostMapping
public ResponseEntity<Division> save(@RequestBody Division division){

    Division savedDivision = divisionService.save(division);
    return ResponseEntity.ok(savedDivision);
}

    @GetMapping
    public  ResponseEntity<List<Division>> getAll(){

        List<Division> list = divisionService.findAll();
        return  ResponseEntity.ok(list);
    }

    // Find by Country ID
    @GetMapping("country/{id}")
    public List<DivisionResponseDTO> getByCountryId(@PathVariable Long id) {
        return divisionService.getDivisionsByCountryId(id);
    }

    // Find by Country Name
    @GetMapping("country/name/{name}")
    public List<DivisionResponseDTO> getByCountryName(@PathVariable String name) {
        return divisionService.getDivisionsByCountryName(name);
    }
}
