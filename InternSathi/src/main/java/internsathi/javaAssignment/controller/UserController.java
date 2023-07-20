package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.LoginDto;
import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

//@RestController
@Controller
@RequestMapping("/internsathi/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*@PostMapping("/registerUser")
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
    }*/

        @GetMapping("/login")
        public String loginPage(Model model, @RequestParam(defaultValue = "false", value = "error") boolean error) {
            model.addAttribute("login", new LoginDto());
            if (error) {
                model.addAttribute("error", true);
            }
            return "login";
        }

        @PostMapping("/login")
        public String login(Authentication authentication) {
            if (authentication.isAuthenticated()) {
                return "redirect:/internsathi/user/home";
            }
            System.out.println(authentication.isAuthenticated());
            return "redirect:/internsathi/user/login?error=true";
        }

}
