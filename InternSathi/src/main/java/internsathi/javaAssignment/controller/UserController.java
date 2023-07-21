package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.Enum.Role;
import internsathi.javaAssignment.dto.LoginDto;
import internsathi.javaAssignment.dto.ResetPasswordDto;
import internsathi.javaAssignment.security.token.JwtTokenService;
import internsathi.javaAssignment.service.EmailService;
import internsathi.javaAssignment.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
@Controller
@RequestMapping("/internsathi/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final EmailService emailService;

    public UserController(UserService userService, JwtTokenService jwtTokenService, EmailService emailService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.emailService = emailService;
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
    public String loginPage(Model model, @RequestParam(defaultValue = "false", value = "error") boolean error, @RequestParam(value = "isOtpVerified", defaultValue = "false") boolean isOtpVerified) {
        model.addAttribute("login", new LoginDto());
        if (error) {
            model.addAttribute("error", true);
        }
        if (isOtpVerified) {
            model.addAttribute("isOtpVerified", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, Authentication authentication) {
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        System.out.println("...." + authorities.contains(Role.ADMIN.name()));
        if (authentication.isAuthenticated()) {
            return "redirect:/internsathi/user/home";
        }
        return "redirect:/internsathi/user/login?error=true";
    }

    @GetMapping("/resetPassword")
    public String resetPasswordPage(Model model, @RequestParam(value = "error", defaultValue = "false") boolean error) {
        model.addAttribute("resetPassword", new ResetPasswordDto());
        model.addAttribute("error", error);
        return "forgetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(Model model, @ModelAttribute ResetPasswordDto resetPassword) {
        /*ResetPasswordDto resetPassword = (ResetPasswordDto) model.getAttribute("resetPassword");
        assert resetPassword != null;*/
        boolean doesUsernameAndEmailExists = userService.doesEmailAndUsernameExits(resetPassword.getUsername(), resetPassword.getEmail());
        if (!doesUsernameAndEmailExists) {
            model.addAttribute("error", true);
            return "redirect:/internsathi/user/resetPassword?error=true";
        } else {
            model.addAttribute("success", true);
            return "redirect:/internsathi/user/otpVerification?username=" + resetPassword.getUsername();
        }
    }

    @GetMapping("/otpVerification")
    public String OtpVerificationPage(Model model,
                                      @RequestParam(value = "error", defaultValue = "false") boolean error,
                                      @RequestParam("username") String username) {
        model.addAttribute("username", username);
        model.addAttribute("otpKey", String.class);
        model.addAttribute("error", error);
        return "otpVerification";
    }

    @PostMapping("/otpVerification")
    public String verifyOtp(Model model,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            String otpKey) {
        log.info("username: {}", username);
        System.out.println(otpKey);
        boolean isOtpVerified = emailService.verifyOtp(otpKey, username);
        if (isOtpVerified) {
            userService.updatePassword(username, password);
            return "redirect:/internsathi/user/login?isOtpVerified=true";
        }
        return "redirect:/internsathi/user/otpVerification?username=" + username +"&error=true";
    }

}
