package internsathi.javaAssignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", allocationSize = 1, initialValue = 1001)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String role;
}
