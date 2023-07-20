/*
package internsathi.javaAssignment.security.manager;

import internsathi.javaAssignment.security.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Configuration
@AllArgsConstructor
public class CustomAuthenticationManger implements AuthenticationManager {

    private final CustomAuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass())) {
            authenticationProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Username or Password incorrect. Please Try again");
    }
}
*/
