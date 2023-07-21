package internsathi.javaAssignment.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    private String username;
    private String email;
    private String password;
}
