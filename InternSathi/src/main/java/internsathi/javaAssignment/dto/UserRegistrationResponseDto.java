package internsathi.javaAssignment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponseDto {

    private String status;
    private String message;
    private String username;
    private String userId;

}
