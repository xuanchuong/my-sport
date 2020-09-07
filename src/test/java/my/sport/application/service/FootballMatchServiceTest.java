package my.sport.application.service;

import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FootballMatchServiceTest {

    @InjectMocks
    private FootballMatchService footballMatchService;

    @Test
    public void hasUserJoinedTheMatch_there_is_empty_participants_should_return_false() {
        // Given
        FootballMatch footballMatch = FootballMatch.builder()
                .participants(Collections.emptyList()).build();
        Player player = Player.builder().id(1234L).build();
        // Then
        assertThat(footballMatchService.hasUserJoinedTheMatch(footballMatch, player)).isFalse();
    }

    @Test
    public void hasUserJoinedTheMatch_user_has_joined_should_return_true() {
        // Given
        Player player = Player.builder().id(1234L).build();
        FootballMatch footballMatch = FootballMatch.builder()
                .participants(Collections.singletonList(player))
                .build();
        // Then
        assertThat(footballMatchService.hasUserJoinedTheMatch(footballMatch, player)).isTrue();
    }

    @Test
    public void hasUserJoinedTheMatch_user_has_not_joined_should_return_false() {
        // Given
        Player player = Player.builder().id(1234L).build();
        Player anotherPlayer = Player.builder().id(4567L).build();
        FootballMatch footballMatch = FootballMatch.builder()
                .participants(Collections.singletonList(anotherPlayer))
                .build();
        // Then
        assertThat(footballMatchService.hasUserJoinedTheMatch(footballMatch, player)).isFalse();
    }
}
