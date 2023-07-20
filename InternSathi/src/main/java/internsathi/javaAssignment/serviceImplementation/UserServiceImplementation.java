package internsathi.javaAssignment.serviceImplementation;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.mapper.UserMapper;
import internsathi.javaAssignment.model.UserSecurity;
import internsathi.javaAssignment.repository.UserRepository;
import internsathi.javaAssignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImplementation implements UserDetailsService, UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.of(userRepo.findByUsername(username).get())
                .map(UserSecurity::new)
                .orElseThrow();
    }

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationDto userRegistrationDetails) {
        return Optional.of(UserMapper.USER_MAPPER_INSTANCE.conversionFromRegistrationDtoToUser(userRegistrationDetails))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return userRepo.save(user);
                })
                .map(user -> UserRegistrationResponseDto.builder()
                            .message("User has been registered Successfully")
                            .status(HttpStatus.CREATED)
                            .username(user.getUsername())
                            .build()
                ).orElseThrow(() -> new RuntimeException("User Registration Failed"));
    }
}
