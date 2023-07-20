package internsathi.javaAssignment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
public class UserRegistrationDto {

    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String role;

}
