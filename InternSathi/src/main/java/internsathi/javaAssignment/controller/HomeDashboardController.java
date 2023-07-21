package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.Enum.Role;
import internsathi.javaAssignment.entity.User;
import internsathi.javaAssignment.security.token.JwtTokenService;
import internsathi.javaAssignment.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/internsathi")
@Controller
@Slf4j
public class HomeDashboardController {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    public HomeDashboardController(JwtTokenService jwtTokenService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    @GetMapping("/user/home")
    public String userHomePage(Model model, Authentication authentication, Principal principal, HttpServletResponse response) {
        if (authentication.isAuthenticated()) {

            List<String> authorities = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            String loggedInUser = (String) authentication.getPrincipal();
            log.info("logged In: .......{}", loggedInUser);
            String token;
            try {

                token = jwtTokenService.generateToken(loggedInUser);
                response.addHeader("Authorization", "Bearer " + token);
                model.addAttribute("token", token);
                model.addAttribute("principal", principal.getName());

                log.info("token {}", token);
            } catch (Exception e) {
                token = "1234";
                throw new RuntimeException(e);
            }

            log.info("is Admin?...." + authorities.contains(Role.ADMIN.name()));
            if (authorities.contains(Role.ADMIN.name())) {
                List<User> userList = getAllUser();
                model.addAttribute("admin", true);
                model.addAttribute("userList", userList);
            }
        }
        return "userHome";
    }

    private List<User> getAllUser() {
        return userService.getAllUser();
    }
}
