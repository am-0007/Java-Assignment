package internsathi.javaAssignment.controller;

import internsathi.javaAssignment.dto.LoginDto;
import internsathi.javaAssignment.security.manager.CustomAuthenticationManager;
import internsathi.javaAssignment.security.token.JwtTokenService;
import internsathi.javaAssignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/internsathi/rc")
public class RestController {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final JwtTokenService jwtTokenService;

    public RestController(CustomAuthenticationManager customAuthenticationManager, JwtTokenService jwtTokenService) {
        this.customAuthenticationManager = customAuthenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        HashMap<String, String> token = new HashMap<>();

        Authentication authentication = customAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword(),
                        new ArrayList<>()
                )
        );

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String access_token = jwtTokenService.generateToken((String) authentication.getPrincipal());
            token.put("access_token", access_token);
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
