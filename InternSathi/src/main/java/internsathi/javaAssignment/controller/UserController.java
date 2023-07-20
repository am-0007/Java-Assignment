package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internsathi/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDetail) {
        try {
            return new ResponseEntity<>(userService.registerUser(userRegistrationDetail), HttpStatus.CREATED);
        } catch (Exception e) {
            UserRegistrationResponseDto userRegistrationFailedResponse = UserRegistrationResponseDto.builder()
                    .message("User registration Failed")
                    .status(HttpStatus.BAD_REQUEST)
                    .username(userRegistrationDetail.getUsername())
                    .build();
            return new ResponseEntity<>(userRegistrationFailedResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
