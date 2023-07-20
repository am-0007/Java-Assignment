package internsathi.javaAssignment.service;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;

public interface UserService {

    public UserRegistrationResponseDto registerUser(UserRegistrationDto userRegistrationDetails);
}
