package internsathi.javaAssignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailMessage {

    private String mailTo;
    public static final String EMAIL_SUBJECT = "Reset password";
    private final String message = "Your 6-digit Otp key is sent to " + this.mailTo
            + ". Enter your pin within a minute.\n"
            + "Otp key: ";

    public EmailMessage(String mailTo) {
        this.mailTo = mailTo;
    }
}
