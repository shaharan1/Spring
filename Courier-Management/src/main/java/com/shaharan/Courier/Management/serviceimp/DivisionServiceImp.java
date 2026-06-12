package com.shaharan.Courier.Management.serviceimp;


import com.shaharan.Courier.Management.entity.Country;
import com.shaharan.Courier.Management.entity.Division;
import com.shaharan.Courier.Management.repository.CountryRepository;
import com.shaharan.Courier.Management.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionServiceImp {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private CountryRepository countryRepository;

@Override
    public Division save(Division d)  {

        Long countryId=d.getCountry().getId();
        Country c= countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found with this ID"));

        d.setCountry(c);
return divisionRepository.save(d);
    }
}
