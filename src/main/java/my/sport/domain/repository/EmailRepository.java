package my.sport.domain.repository;

public interface EmailRepository {

    void sendSimpleMessage(String to, String subject, String text);

    void sendResetPasswordMessage(String to, String token);
}
