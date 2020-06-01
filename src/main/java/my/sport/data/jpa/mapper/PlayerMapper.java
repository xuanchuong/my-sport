package my.sport.data.jpa.mapper;

import my.sport.data.jpa.entity.JpaPlayer;
import my.sport.data.jpa.entity.JpaRole;
import my.sport.domain.entity.Player;
import my.sport.domain.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class PlayerMapper {

    public JpaPlayer map(Player source) {
        JpaPlayer jpaPlayer = new JpaPlayer();
        jpaPlayer.setId(source.getId());
        jpaPlayer.setDescription(source.getDescription());
        jpaPlayer.setEmail(source.getEmail());
        jpaPlayer.setFirstName(source.getFirstName());
        jpaPlayer.setLastName(source.getLastName());
        jpaPlayer.setPassword(source.getPassword());
        List<JpaRole> roles = new ArrayList<>();
        jpaPlayer.setRoles(roles);
        return jpaPlayer;
    }

    public Player map(JpaPlayer source) {
        Player player = new Player();
        player.setId(source.getId());
        player.setDescription(source.getDescription());
        player.setEmail(source.getEmail());
        player.setFirstName(source.getFirstName());
        player.setLastName(source.getLastName());
        player.setPassword(source.getPassword());
        List<Role> roles = new ArrayList<>();
        player.setRoles(roles);
        return player;
    }
}
