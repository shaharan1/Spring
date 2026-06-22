package emranhss.com.Modern_Hospital_Management_System.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String medicineName;

    private String genericName;

    private String dosage; // e.g. "500mg", "1 tablet"

    private String frequency; // e.g. "3 times a day", "twice daily"

    private String route; // e.g. "Oral", "Topical", "Injection"

    private String duration; // Optional: how long (e.g. 7 days)

    private String applyWay;

    private Integer quantity; // total units to dispense

    private LocalDate startDate;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(nullable = false)
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}
