package com.shaharan.Courier.Management.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="divisions" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private  String nameBn;
    private  Boolean active=true;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private  Country country;
}
