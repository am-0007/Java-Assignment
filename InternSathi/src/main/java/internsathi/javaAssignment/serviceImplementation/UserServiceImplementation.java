package internsathi.javaAssignment.serviceImplementation;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.entity.EmailMessage;
import internsathi.javaAssignment.mapper.UserMapper;
import internsathi.javaAssignment.model.UserSecurity;
import internsathi.javaAssignment.repository.UserRepository;
import internsathi.javaAssignment.service.EmailService;
import internsathi.javaAssignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserDetailsService, UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserServiceImplementation(UserRepository userRepo, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username or Password Incorrect. Please Try again!"));
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

    @Override
    public boolean doesEmailAndUsernameExits(String username, String email) {
        boolean userAvailable = userRepo.findByUsernameAndEmail(username, email)
                .isPresent();
        if (userAvailable) {
            emailService.sendEmail(
                    new EmailMessage(email), username
            );
        }
        return userAvailable;
    }

    @Override
    public void updatePassword(String username, String password) {
        password = passwordEncoder.encode(password);
        userRepo.updateUserPassword(username, password);

    }
}
