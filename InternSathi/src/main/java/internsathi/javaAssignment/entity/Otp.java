package internsathi.javaAssignment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_otp")
@Data
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String otpKey;

    public Otp(String username, String otpKey) {
        this.username = username;
        this.otpKey = otpKey;
    }

    public Otp() {
    }
}
