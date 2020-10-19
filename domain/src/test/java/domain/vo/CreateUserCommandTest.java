package domain.vo;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserCommandTest {

    @Test
    public void isValid_should_return_true_if_all_fields_are_correct() {
        // Given
        CreateUserCommand testObject = CreateUserCommand.builder()
                .email("email")
                .firstName("first_name")
                .lastName("last_name")
                .password("password")
                .build();
        // Then
        assertTrue(testObject.isValid());
    }

    @Test
    public void isValid_should_return_false_if_any_field_is_missing() {
        // Given
        CreateUserCommand testObject = CreateUserCommand.builder()
                .email("email")
                .firstName("first_name")
                .lastName("last_name")
                .build();
        // Then
        assertFalse(testObject.isValid());
    }

    @Test
    public void isValid_should_return_false_if_any_field_is_null() {
        // Given
        CreateUserCommand testObject = CreateUserCommand.builder()
                .email("email")
                .firstName("first_name")
                .lastName("last_name")
                .password(null)
                .build();
        // Then
        assertFalse(testObject.isValid());
    }
}
