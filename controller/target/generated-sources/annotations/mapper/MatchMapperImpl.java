package mapper;

import domain.entity.FootballMatch;
import domain.entity.FootballMatch.FootballMatchBuilder;
import domain.entity.Player;
import domain.vo.CreateFootballMatchCommand;
import domain.vo.CreateFootballMatchCommand.CreateFootballMatchCommandBuilder;
import dto.CreateFootballMatchCommandDTO;
import dto.FootballMatchDTO;
import dto.PlayerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-18T11:43:17+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Autowired
    private PlayerControllerMapper playerControllerMapper;

    @Override
    public CreateFootballMatchCommand map(CreateFootballMatchCommandDTO source) {
        if ( source == null ) {
            return null;
        }

        CreateFootballMatchCommandBuilder createFootballMatchCommand = CreateFootballMatchCommand.builder();

        if ( source.getStartDate() != null ) {
            createFootballMatchCommand.startDate( source.getStartDate() );
        }
        if ( source.getLocation() != null ) {
            createFootballMatchCommand.location( source.getLocation() );
        }
        if ( source.getTitle() != null ) {
            createFootballMatchCommand.title( source.getTitle() );
        }
        if ( source.getDescription() != null ) {
            createFootballMatchCommand.description( source.getDescription() );
        }
        createFootballMatchCommand.numberOfPlayers( source.getNumberOfPlayers() );

        return createFootballMatchCommand.build();
    }

    @Override
    public FootballMatch map(FootballMatchDTO source) {
        if ( source == null ) {
            return null;
        }

        FootballMatchBuilder footballMatch = FootballMatch.builder();

        if ( source.getId() != null ) {
            footballMatch.id( source.getId() );
        }
        if ( source.getOwner() != null ) {
            footballMatch.owner( playerControllerMapper.map( source.getOwner() ) );
        }
        if ( source.getStartDate() != null ) {
            footballMatch.startDate( source.getStartDate() );
        }
        if ( source.getLocation() != null ) {
            footballMatch.location( source.getLocation() );
        }
        if ( source.getTitle() != null ) {
            footballMatch.title( source.getTitle() );
        }
        if ( source.getDescription() != null ) {
            footballMatch.description( source.getDescription() );
        }
        footballMatch.numberOfPlayers( source.getNumberOfPlayers() );
        if ( source.getMatchStatus() != null ) {
            footballMatch.matchStatus( source.getMatchStatus() );
        }
        List<Player> list = playerDTOListToPlayerList( source.getParticipants() );
        if ( list != null ) {
            footballMatch.participants( list );
        }

        return footballMatch.build();
    }

    protected List<Player> playerDTOListToPlayerList(List<PlayerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Player> list1 = new ArrayList<Player>( list.size() );
        for ( PlayerDTO playerDTO : list ) {
            list1.add( playerControllerMapper.map( playerDTO ) );
        }

        return list1;
    }
}
