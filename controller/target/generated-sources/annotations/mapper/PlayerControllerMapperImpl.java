package mapper;

import domain.entity.Player;
import domain.entity.Player.PlayerBuilder;
import domain.entity.Role;
import domain.vo.CreateUserCommand;
import domain.vo.CreateUserCommand.CreateUserCommandBuilder;
import dto.CreateUserCommandDTO;
import dto.PlayerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-18T11:43:17+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class PlayerControllerMapperImpl implements PlayerControllerMapper {

    @Override
    public CreateUserCommand map(CreateUserCommandDTO source) {
        if ( source == null ) {
            return null;
        }

        CreateUserCommandBuilder createUserCommand = CreateUserCommand.builder();

        if ( source.getFirstName() != null ) {
            createUserCommand.firstName( source.getFirstName() );
        }
        if ( source.getLastName() != null ) {
            createUserCommand.lastName( source.getLastName() );
        }
        if ( source.getPassword() != null ) {
            createUserCommand.password( source.getPassword() );
        }
        if ( source.getEmail() != null ) {
            createUserCommand.email( source.getEmail() );
        }

        return createUserCommand.build();
    }

    @Override
    public Player map(PlayerDTO source) {
        if ( source == null ) {
            return null;
        }

        PlayerBuilder player = Player.builder();

        if ( source.getId() != null ) {
            player.id( source.getId() );
        }
        if ( source.getFirstName() != null ) {
            player.firstName( source.getFirstName() );
        }
        if ( source.getLastName() != null ) {
            player.lastName( source.getLastName() );
        }
        if ( source.getEmail() != null ) {
            player.email( source.getEmail() );
        }
        if ( source.getPhoneNumber() != null ) {
            player.phoneNumber( source.getPhoneNumber() );
        }
        if ( source.getDescription() != null ) {
            player.description( source.getDescription() );
        }
        if ( source.getPassword() != null ) {
            player.password( source.getPassword() );
        }
        List<Role> list = source.getRoles();
        if ( list != null ) {
            player.roles( new ArrayList<Role>( list ) );
        }

        return player.build();
    }
}
