package internsathi.javaAssignment.service;

import internsathi.javaAssignment.entity.EmailMessage;

public interface EmailService {
    void sendEmail(EmailMessage emailMessage, String username);

    boolean verifyOtp(String otpKey, String username);
}
