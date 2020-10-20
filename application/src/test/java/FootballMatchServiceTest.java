import domain.entity.FootballMatch;
import domain.entity.Player;
import domain.repository.FootballMatchRepository;
import domain.vo.CreateFootballMatchCommand;
import domain.vo.MatchStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.FootballMatchService;
import service.PlayerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Test
    public void joinTheMatch_should_join_successfully() {
        // Given
        FootballMatch footballMatch = mock(FootballMatch.class);
        Player player = mock(Player.class);
        when(footballMatch.getParticipants()).thenReturn(new ArrayList<>());
        when(footballMatch.getPendingPlayer()).thenReturn(new ArrayList<>());
        when(footballMatchRepository.save(footballMatch)).thenReturn(footballMatch);
        // When
        footballMatchService.joinTheMatch(footballMatch, player);
        // Then
        ArgumentCaptor<FootballMatch> savedFootballMatch = ArgumentCaptor.forClass(FootballMatch.class);
        verify(footballMatchRepository).save(savedFootballMatch.capture());
        assertThat(savedFootballMatch.getValue().getPendingPlayer()).containsOnly(player);
    }

    @Test
    public void joinTheMatch_should_throw_IllegalArgumentException_if_player_already_joined_the_match() {
        // Given
        FootballMatch footballMatch = mock(FootballMatch.class);
        Player player = mock(Player.class);
        when(footballMatch.getParticipants()).thenReturn(Collections.singletonList(player));
        // When
        Throwable throwable = catchThrowable(() -> footballMatchService.joinTheMatch(footballMatch, player));
        // Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage()).isEqualTo("the user already joined the match");
        verify(footballMatchRepository, never()).save(any(FootballMatch.class));
    }

    @Test
    public void joinTheMatch_should_throw_IllegalArgumentException_if_player_already_requested_the_match() {
        // Given
        FootballMatch footballMatch = mock(FootballMatch.class);
        Player player = mock(Player.class);
        when(footballMatch.getPendingPlayer()).thenReturn(Collections.singletonList(player));
        when(footballMatch.getParticipants()).thenReturn(new ArrayList<>());
        // When
        Throwable throwable = catchThrowable(() -> footballMatchService.joinTheMatch(footballMatch, player));
        // Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage()).isEqualTo("the user already requested to join the match");
        verify(footballMatchRepository, never()).save(any(FootballMatch.class));
    }
}
