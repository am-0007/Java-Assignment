package internsathi.javaAssignment.security;

import internsathi.javaAssignment.security.filter.JwtAuthenticationTokenFilter;
import internsathi.javaAssignment.security.manager.CustomAuthenticationManager;
import internsathi.javaAssignment.security.provider.CustomAuthenticationProvider;
import internsathi.javaAssignment.security.token.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationManager authenticationManger;
    private final UserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;

    public SecurityConfig(CustomAuthenticationManager authenticationManger, UserDetailsService userDetailsService, JwtTokenService jwtTokenService) {
        this.authenticationManger = authenticationManger;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public SecurityFilterChain appConfig(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(new JwtAuthenticationTokenFilter(userDetailsService, new JwtTokenService(userDetailsService)), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers(
                                        "/internsathi/user/login",
                                        "/internsathi/user/registerUser",
                                        "/internsathi/user/resetPassword",
                                        "/internsathi/user/otpVerification",
                                        "/internsathi/rc/login").permitAll()
                                .requestMatchers("/internsathi/user/registerUser").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                                login
                                        .loginPage("/internsathi/user/login")
                                        .defaultSuccessUrl("/internsathi/user/home")
                                        .failureUrl("/internsathi/user/login?error=true")
                )
                .build();

    }
}
