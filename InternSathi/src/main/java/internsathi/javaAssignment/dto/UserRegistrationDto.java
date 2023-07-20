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

    public UserRegistrationDto(String name, String username, String password, String email, String phoneNumber, String address, LocalDate dateOfBirth, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String name, String username, String password, String email, String phoneNumber, String address, LocalDate dateOfBirth) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
