package emranhss.com.Modern_Hospital_Management_System.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponse {


    private Long id;

    private String medicineName;

    private String genericName;

    private String dosage;

    private String frequency;

    private String route;

    private String duration;

    private String applyWay;

    private Integer quantity;

    private LocalDate startDate;

    private String instructions;

    private boolean isActive;

    private Long prescriptionId;
}
