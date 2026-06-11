package com.shaharan.Courier.Management.repository;

import com.shaharan.Courier.Management.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
