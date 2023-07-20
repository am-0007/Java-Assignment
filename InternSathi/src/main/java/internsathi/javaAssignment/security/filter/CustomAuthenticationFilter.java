/*
package internsathi.javaAssignment.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import internsathi.javaAssignment.dto.LoginDto;
import internsathi.javaAssignment.security.manager.CustomAuthenticationManger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;

@Configuration
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManger authenticationManger;

    public CustomAuthenticationFilter(CustomAuthenticationManger authenticationManger) {
        super(authenticationManger);
        this.authenticationManger = authenticationManger;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDto loginAttemptUser;
        try {
            loginAttemptUser = new ObjectMapper().readValue(request.getRequestURI(), LoginDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginAttemptUser.getUsername(),
                loginAttemptUser.getPassword(),
                new ArrayList<>()
        );
        return authenticationManger.authenticate(authenticationToken);
    }
}
*/
