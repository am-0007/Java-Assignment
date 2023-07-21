package internsathi.javaAssignment.serviceImplementation;

import internsathi.javaAssignment.entity.EmailMessage;
import internsathi.javaAssignment.entity.Otp;
import internsathi.javaAssignment.repository.OtpRepo;
import internsathi.javaAssignment.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class EmailServiceImplementation implements EmailService {

    private final JavaMailSender javaMailSender;
    private final OtpRepo otpRepo;

    public EmailServiceImplementation(JavaMailSender javaMailSender, OtpRepo otpRepo) {
        this.javaMailSender = javaMailSender;
        this.otpRepo = otpRepo;
    }

    @Override
    public void sendEmail(EmailMessage emailMessage, String username) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String otpKey = generateOtpKey();
        Otp otp = new Otp(username, otpKey);
        otpRepo.save(otp);

        simpleMailMessage.setFrom("ajaymaharjan0007@gmail.com");
        simpleMailMessage.setTo(emailMessage.getMailTo());
        simpleMailMessage.setSubject(EmailMessage.EMAIL_SUBJECT);
        simpleMailMessage.setText(emailMessage.getMessage() + otpKey);

        this.javaMailSender.send(simpleMailMessage);
    }

    @Override
    public boolean verifyOtp(String otpKey, String username) {
        Optional<Otp> otp = otpRepo.findOtpByUsernameAndOtpKey(otpKey, username);
        return otp.isPresent();
    }

    private String generateOtpKey() {
        String digits = "0123456789";
        int lengthOfOtp = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < lengthOfOtp; i++) {
            int index = random.nextInt(digits.length());
            otp.append(digits.charAt(index));
        }
        return otp.toString();
    }
}
