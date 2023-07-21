package internsathi.javaAssignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", allocationSize = 1, initialValue = 1001)
    private Long id;

    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String role;
}
