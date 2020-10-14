package my.sport.application.service;

import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.domain.vo.CreateFootballMatchCommand;
import my.sport.domain.vo.MatchStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FootballMatchServiceTest {

    @InjectMocks
    private FootballMatchService footballMatchService;
    @Mock
    private PlayerService playerService;
    @Mock
    private FootballMatchRepository footballMatchRepository;

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

    @Test
    public void createNewMatch_should_not_allow_to_create_invalid_match() {
        // Given
        CreateFootballMatchCommand createFootballMatchCommand = mock(CreateFootballMatchCommand.class);
        when(createFootballMatchCommand.isValid()).thenReturn(false);
        // When
        Throwable throwable = catchThrowable(() -> footballMatchService.createNewMatch(createFootballMatchCommand));
        // Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage()).isEqualTo("matchDto's fields are missing");
    }

    @Test
    public void createNewMatch_should_create_successfully() {
        // Given
        LocalDateTime startDate = LocalDateTime.now();
        CreateFootballMatchCommand createFootballMatchCommand = CreateFootballMatchCommand.builder()
                .description("description")
                .location("location")
                .numberOfPlayers(10)
                .startDate(startDate)
                .title("title")
                .build();
        Player sessionPlayer = mock(Player.class);
        when(playerService.getSessionPlayer()).thenReturn(sessionPlayer);
        ArgumentCaptor<FootballMatch> argumentCaptor = ArgumentCaptor.forClass(FootballMatch.class);
        // When
        footballMatchService.createNewMatch(createFootballMatchCommand);
        // Then
        verify(footballMatchRepository).save(argumentCaptor.capture());
        FootballMatch result = argumentCaptor.getValue();
        assertThat(result.getDescription()).isEqualTo("description");
        assertThat(result.getLocation()).isEqualTo("location");
        assertThat(result.getTitle()).isEqualTo("title");
        assertThat(result.getNumberOfPlayers()).isEqualTo(10);
        assertThat(result.getMatchStatus()).isEqualTo(MatchStatus.READY);
        assertThat(result.getOwner()).isEqualTo(sessionPlayer);
        assertThat(result.getParticipants()).containsOnly(sessionPlayer);
        assertThat(result.getStartDate()).isEqualTo(startDate);
    }
}
