package internsathi.javaAssignment.service;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.entity.User;

import java.util.List;

public interface UserService {

    UserRegistrationResponseDto registerUser(UserRegistrationDto userRegistrationDetails) throws Exception;

    boolean doesEmailAndUsernameExits(String username, String email);

    void updatePassword(String username, String password);

    List<User> getAllUser();

    void deleteUserById(Long userId);

}
