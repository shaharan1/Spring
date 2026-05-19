package com.emranhss.ModernHospitalSystemManagement.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "policestations")
public class policeStation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 50)
    private String name;


}
