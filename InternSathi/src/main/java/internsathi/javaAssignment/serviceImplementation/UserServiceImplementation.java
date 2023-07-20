package internsathi.javaAssignment.serviceImplementation;

import internsathi.javaAssignment.dto.UserRegistrationDto;
import internsathi.javaAssignment.dto.UserRegistrationResponseDto;
import internsathi.javaAssignment.model.UserSecurity;
import internsathi.javaAssignment.repository.UserRepository;
import internsathi.javaAssignment.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserDetailsService, UserService {

    private final UserRepository userRepo;

    public UserServiceImplementation(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.of(userRepo.findByUsername(username).get())
                .map(UserSecurity::new)
                .orElseThrow();
    }

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationDto userRegistrationDetails) {
        return null;
    }
}
