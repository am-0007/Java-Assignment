package internsathi.javaAssignment.repository;

import internsathi.javaAssignment.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtpRepo extends JpaRepository<Otp, Long> {

    @Query("select o from Otp o where o.username = ?2 and o.otpKey = ?1")
    Optional<Otp> findOtpByUsernameAndOtpKey(String otpKey, String username);
}
