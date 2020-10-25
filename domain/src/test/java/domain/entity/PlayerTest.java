package domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final Player player = Player.builder().id(123L)
            .email("email.com")
            .firstName("firstName")
            .lastName("lastName")
            .password("password")
            .phoneNumber("0987654321")
            .description("description")
            .roles(new ArrayList<>())
            .build();

    @ParameterizedTest
    @MethodSource("invalidPlayers")
    void testEquals(Object invalidPlayer) {
        assertNotEquals(player, invalidPlayer);
    }

    private static Object[] invalidPlayers() {
        return new Object[] {
                null,
                Boolean.TRUE,
                Player.builder().firstName("chuong").build(),
                Player.builder().email("gmail.com").build()
        };
    }

    private static Object[] validPlayers() {
        return new Object[] {
                Player.builder().email("email.com").firstName("chuong").build(),
                Player.builder().email("email.com").build(),
        };
    }
}