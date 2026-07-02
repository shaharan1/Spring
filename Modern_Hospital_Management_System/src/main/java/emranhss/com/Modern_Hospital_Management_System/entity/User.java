package emranhss.com.Modern_Hospital_Management_System.entity;


import emranhss.com.Modern_Hospital_Management_System.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;


    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active;


}
