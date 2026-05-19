package com.emranhss.ModernHospitalSystemManagement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "policestations")
public class policeStation {


    @Id
    private long id;
    private String name;


}
