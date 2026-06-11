package com.shaharan.Courier.Management.controller;


import com.shaharan.Courier.Management.entity.Country;
import com.shaharan.Courier.Management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/country/")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<Country> save (@RequestBody Country c){

      Country savedCountry= countryService.save(c);
      return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

public ResponseEntity<List<Country>> getAll(){

        List<Country> list=countryService.findAll();
        return ResponseEntity.ok(list);
}
}
