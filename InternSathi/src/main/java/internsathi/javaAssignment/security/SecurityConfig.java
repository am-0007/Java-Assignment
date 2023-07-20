package internsathi.javaAssignment.security;

//import internsathi.javaAssignment.security.filter.CustomAuthenticationFilter;
//import internsathi.javaAssignment.security.manager.CustomAuthenticationManger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    /*private final CustomAuthenticationManger authenticationManger;

    public SecurityConfig(CustomAuthenticationManger authenticationManger) {
        this.authenticationManger = authenticationManger;
    }*/

    @Bean
    public SecurityFilterChain appConfig(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                //.addFilterAt(new CustomAuthenticationFilter(authenticationManger), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/internsathi/user/login").permitAll()
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
