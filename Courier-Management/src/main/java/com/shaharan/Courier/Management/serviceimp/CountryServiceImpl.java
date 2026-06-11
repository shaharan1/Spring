package com.shaharan.Courier.Management.serviceimp;

import com.shaharan.Courier.Management.entity.Country;
import com.shaharan.Courier.Management.repository.CountryRepository;
import com.shaharan.Courier.Management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save (Country c){

        return countryRepository.save(c);
    }

    @Override
    public List<Country> findAll(){

        return countryRepository.findAll();

    }

    @Override
    public Optional<Country> getById (Long id){

        return countryRepository.findById(id);
    }

    public void delete(Long id){

        Country country=countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country Not Found With ID:  " + id));
        countryRepository.delete(country);
    }


}
