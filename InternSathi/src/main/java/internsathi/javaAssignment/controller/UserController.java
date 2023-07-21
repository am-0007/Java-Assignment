package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.LoginDto;
import internsathi.javaAssignment.model.UserSecurity;
import internsathi.javaAssignment.security.token.JwtTokenService;
import internsathi.javaAssignment.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

//@RestController
@Controller
@RequestMapping("/internsathi/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public UserController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
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
        public String login(Model model, Authentication authentication) {
            if (authentication.isAuthenticated()) {
                /*UserSecurity loggedInUser = (UserSecurity) authentication.getPrincipal();
                log.info("...{}", loggedInUser.user());
                String token;
                try {
                    token = jwtTokenService.generateToken(loggedInUser.getUsername());
                } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                    token = "1234";
                    throw new RuntimeException(e);
                }
                model.addAttribute("token", token);
                log.info("token {}", token);*/
                return "redirect:/internsathi/user/home";
            }
            System.out.println(authentication.isAuthenticated());
            return "redirect:/internsathi/user/login?error=true";
        }

}
