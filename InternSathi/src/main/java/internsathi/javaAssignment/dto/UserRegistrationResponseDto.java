package internsathi.javaAssignment.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
public class UserRegistrationResponseDto {

    private HttpStatus status;
    private String message;
    private String username;

}
