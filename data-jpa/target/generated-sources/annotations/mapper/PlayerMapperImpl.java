package mapper;

import domain.entity.Player;
import domain.entity.Player.PlayerBuilder;
import domain.entity.Role;
import domain.entity.Role.RoleBuilder;
import entity.JpaPlayer;
import entity.JpaRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-18T11:43:14+0700",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public JpaPlayer map(Player source) {
        if ( source == null ) {
            return null;
        }

        JpaPlayer jpaPlayer = new JpaPlayer();

        if ( source.getId() != null ) {
            jpaPlayer.setId( source.getId() );
        }
        if ( source.getPhoneNumber() != null ) {
            jpaPlayer.setPhoneNumber( source.getPhoneNumber() );
        }
        if ( source.getFirstName() != null ) {
            jpaPlayer.setFirstName( source.getFirstName() );
        }
        if ( source.getLastName() != null ) {
            jpaPlayer.setLastName( source.getLastName() );
        }
        if ( source.getEmail() != null ) {
            jpaPlayer.setEmail( source.getEmail() );
        }
        if ( source.getDescription() != null ) {
            jpaPlayer.setDescription( source.getDescription() );
        }
        if ( source.getPassword() != null ) {
            jpaPlayer.setPassword( source.getPassword() );
        }
        List<JpaRole> list = roleListToJpaRoleList( source.getRoles() );
        if ( list != null ) {
            jpaPlayer.setRoles( list );
        }

        return jpaPlayer;
    }

    @Override
    public Player map(JpaPlayer source) {
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
        List<Role> list = jpaRoleListToRoleList( source.getRoles() );
        if ( list != null ) {
            player.roles( list );
        }

        return player.build();
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

    protected Role jpaRoleToRole(JpaRole jpaRole) {
        if ( jpaRole == null ) {
            return null;
        }

        RoleBuilder role = Role.builder();

        if ( jpaRole.getId() != null ) {
            role.id( jpaRole.getId() );
        }
        if ( jpaRole.getName() != null ) {
            role.name( jpaRole.getName() );
        }

        return role.build();
    }

    protected List<Role> jpaRoleListToRoleList(List<JpaRole> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( JpaRole jpaRole : list ) {
            list1.add( jpaRoleToRole( jpaRole ) );
        }

        return list1;
    }
}
