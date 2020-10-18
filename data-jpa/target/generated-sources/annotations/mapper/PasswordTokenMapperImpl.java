package mapper;

import domain.entity.PasswordResetToken;
import domain.entity.Player;
import domain.entity.Role;
import entity.JpaPlayer;
import entity.JpaRole;
import entity.PasswordResetTokenJpa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-18T11:43:13+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class PasswordTokenMapperImpl implements PasswordTokenMapper {

    @Override
    public PasswordResetTokenJpa map(PasswordResetToken source) {
        if ( source == null ) {
            return null;
        }

        PasswordResetTokenJpa passwordResetTokenJpa = new PasswordResetTokenJpa();

        if ( source.getToken() != null ) {
            passwordResetTokenJpa.setToken( source.getToken() );
        }
        if ( source.getPlayer() != null ) {
            passwordResetTokenJpa.setPlayer( playerToJpaPlayer( source.getPlayer() ) );
        }
        if ( source.getExpiryDate() != null ) {
            passwordResetTokenJpa.setExpiryDate( source.getExpiryDate() );
        }

        return passwordResetTokenJpa;
    }

    protected JpaRole roleToJpaRole(Role role) {
        if ( role == null ) {
            return null;
        }

        JpaRole jpaRole = new JpaRole();

        if ( role.getId() != null ) {
            jpaRole.setId( role.getId() );
        }
        if ( role.getName() != null ) {
            jpaRole.setName( role.getName() );
        }

        return jpaRole;
    }

    protected List<JpaRole> roleListToJpaRoleList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<JpaRole> list1 = new ArrayList<JpaRole>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToJpaRole( role ) );
        }

        return list1;
    }

    protected JpaPlayer playerToJpaPlayer(Player player) {
        if ( player == null ) {
            return null;
        }

        JpaPlayer jpaPlayer = new JpaPlayer();

        if ( player.getId() != null ) {
            jpaPlayer.setId( player.getId() );
        }
        if ( player.getPhoneNumber() != null ) {
            jpaPlayer.setPhoneNumber( player.getPhoneNumber() );
        }
        if ( player.getFirstName() != null ) {
            jpaPlayer.setFirstName( player.getFirstName() );
        }
        if ( player.getLastName() != null ) {
            jpaPlayer.setLastName( player.getLastName() );
        }
        if ( player.getEmail() != null ) {
            jpaPlayer.setEmail( player.getEmail() );
        }
        if ( player.getDescription() != null ) {
            jpaPlayer.setDescription( player.getDescription() );
        }
        if ( player.getPassword() != null ) {
            jpaPlayer.setPassword( player.getPassword() );
        }
        List<JpaRole> list = roleListToJpaRoleList( player.getRoles() );
        if ( list != null ) {
            jpaPlayer.setRoles( list );
        }

        return jpaPlayer;
    }
}
