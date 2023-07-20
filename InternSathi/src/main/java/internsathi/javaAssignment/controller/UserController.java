package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/internsathi/user")
public class UserController {

    @GetMapping("/registerUser")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDetail) {
        return null;
    }
}
