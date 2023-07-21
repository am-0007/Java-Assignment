package internsathi.javaAssignment.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import internsathi.javaAssignment.dto.LoginDto;
import internsathi.javaAssignment.security.manager.CustomAuthenticationManager;
import internsathi.javaAssignment.security.token.JwtTokenService;
import internsathi.javaAssignment.security.token.TokenProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public CustomAuthenticationFilter(CustomAuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDto loginUser;
        try {
            loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(),
                loginUser.getPassword(),
                new ArrayList<>()
        );

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String principal = (String) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        String token = null;
        Map<String, String> accessToken = new HashMap<>();

        // Create Jwt token
        try {
            token = jwtTokenService.generateToken(principal);
            accessToken.put("access_token", token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Add Bearer token in authorization header
        response.addHeader(TokenProperties.HEADER_STRING, TokenProperties.TOKEN_PREFIX + token);


        // Send access token as response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{ \n \"access_token\" : "
                + "\"" + accessToken.get("access_token") + "\""
                + "\n}"
        );
        response.sendRedirect("/internsathi/user/home");
    }
}
