package internsathi.javaAssignment.security.manager;

import internsathi.javaAssignment.security.provider.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomAuthenticationProvider authenticationProvider;

    public CustomAuthenticationManager(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass())) {
            return authenticationProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Username or password incorrect");
    }
}
