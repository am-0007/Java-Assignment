package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.model.UserSecurity;
import internsathi.javaAssignment.security.token.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RequestMapping("/internsathi/user")
@Controller
@Slf4j
public class HomeDashboardController {

    private final JwtTokenService jwtTokenService;

    public HomeDashboardController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication, Principal principal) {
        if (authentication.isAuthenticated()) {
            String loggedInUser = (String) authentication.getPrincipal();
            log.info("...{}", loggedInUser);
            String token;
            try {
                token = jwtTokenService.generateToken(loggedInUser);
            } catch (Exception e) {
                token = "1234";
                throw new RuntimeException(e);
            }
            model.addAttribute("token", token);
            log.info("token {}", token);
        }
        model.addAttribute("principal", principal.getName());
        return "home";
    }

}
