package domain.vo;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CreateFootballMatchCommandTest {

    @Test
    public void isValid_should_return_true_if_all_fields_are_valid() {
        // Given
        CreateFootballMatchCommand command = CreateFootballMatchCommand.builder()
                .description("description")
                .location("location")
                .title("title")
                .numberOfPlayers(10)
                .startDate(LocalDateTime.now())
                .build();
        // Then
        assertTrue(command.isValid());
    }

    @Test
    public void isValid_should_return_false_if_any_field_is_missing() {
        // Given
        CreateFootballMatchCommand command = CreateFootballMatchCommand.builder()
                .location("location")
                .title("title")
                .numberOfPlayers(10)
                .startDate(LocalDateTime.now())
                .build();
        // Then
        assertFalse(command.isValid());
    }

    @Test
    public void isValid_should_return_false_if_any_field_is_null() {
        // Given
        CreateFootballMatchCommand command = CreateFootballMatchCommand.builder()
                .description("description")
                .location("location")
                .title("title")
                .numberOfPlayers(10)
                .startDate(null)
                .build();
        // Then
        assertFalse(command.isValid());
    }

    @Test
    public void isValid_should_return_false_if_the_number_of_player_is_invalid() {
        // Given
        CreateFootballMatchCommand command = CreateFootballMatchCommand.builder()
                .description("description")
                .location("location")
                .title("title")
                .numberOfPlayers(1)
                .startDate(LocalDateTime.now())
                .build();
        // Then
        assertFalse(command.isValid());
    }

}
