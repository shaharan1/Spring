package emranhss.com.Modern_Hospital_Management_System.entity;


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
    private String status;
    private String study;

    @Column(nullable = false)
    private String specialization;

    private String designation;

    @Column(unique = true)
    private String registrationNumber;

    private Integer experienceYears;
    private Double consultationFee;
    private Double followUpFee;
    private String availableDays;
    private String dutyHours;
    private String chamber;
    private LocalDate joinDate;
    private String photo;
}
