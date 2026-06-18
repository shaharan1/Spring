package com.emranhss.ModernHospitalSystemManagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String name;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String gender;
    private String status;              // Active, Inactive, On Leave, Suspended
    private String study;               // Educational degrees (e.g., MBBS, FCPS, MD)

    // --- Essential New Fields ---
    @Column(nullable = false)
    private String specialization;      // Field of expertise (e.g., Cardiology)

    private String designation;         // Job title (e.g., Consultant, Associate Professor)

    @Column(unique = true)
    private String registrationNumber;  // Government medical council license identifier

    private Integer experienceYears;    // Total years of medical experience

    private Double consultationFee;     // First-time or regular visit fee
    private Double followUpFee;         // Post-visit report viewing fee

    private String availableDays;       // Weekly availability (e.g., Saturday - Wednesday)
    private String dutyHours;           // Daily time slots (e.g., 04:00 PM - 09:00 PM)
    // ----------------------------

    private String chamber;

    // Preferred over legacy java.util.Date for modern Java applications
    private LocalDate joinDate;

    private String photo;               // Stores file path, name, or image URL bucket link

}
