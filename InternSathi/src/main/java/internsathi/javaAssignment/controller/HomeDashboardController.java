package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.Enum.Role;
import internsathi.javaAssignment.entity.User;
import internsathi.javaAssignment.security.token.JwtTokenService;
import internsathi.javaAssignment.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String userHomePage(Model model,
                               @RequestHeader(value = "Authorization", defaultValue = "") String authHeader,
                               Authentication authentication, Principal principal,
                               HttpServletRequest request, HttpServletResponse response) {

        System.out.println(authHeader);
        System.out.println(request.getHeader("Authorization"));
        if (authentication.isAuthenticated()) {
            List<String> authorities = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            String token;
            String loggedInUser = (String) authentication.getPrincipal();
            if (request.getHeader("Authorization") == null) {

                log.info("logged In: .......{}", loggedInUser);
                try {

                    token = jwtTokenService.generateToken(loggedInUser);
                    model.addAttribute("token", token);
                    model.addAttribute("principal", principal.getName());
                    response.addHeader("Authorization", token);
                    log.info("token {}", token);

                } catch (Exception e) {
                    token = "1234";
                    throw new RuntimeException(e);
                }
            }
            if (request.getHeader("Authorization") != null) {
                token = request.getHeader("Authorization");
                response.addHeader("Authorization", token);
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

    @PostMapping("/admin/deleteUserById")
    public String deleteUserById(Model model, @RequestParam(value = "userId", defaultValue = "0") Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/internsathi/user/home";

    }

    private List<User> getAllUser() {
        return userService.getAllUser();
    }
}
