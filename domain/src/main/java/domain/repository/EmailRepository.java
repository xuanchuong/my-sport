package domain.repository;

public interface EmailRepository {

    void sendResetPasswordMessage(String to, String token);
}
