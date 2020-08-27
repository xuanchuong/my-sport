package my.sport.data.rest;

import lombok.AllArgsConstructor;
import my.sport.domain.repository.EmailRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
public class EmailRepositoryAdapter implements EmailRepository {

    private final JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@mysport.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    @Override
    public void sendResetPasswordMessage(String to, String token) {
        final SimpleMailMessage email = constructResetTokenEmail("http://localhost:4200/", token, to);
        emailSender.send(email);
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, String token, String email) {
        String url = contextPath + "/account/changePassword?token=" + token;
        String message = "Please click this link below to reset your password";
        return constructEmail("Reset Password", message + " \r\n" + url, email);
    }

    private SimpleMailMessage constructEmail(String subject, String body, String to) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(to);
        email.setFrom("noreply@mysport.com");
        return email;
    }


}
