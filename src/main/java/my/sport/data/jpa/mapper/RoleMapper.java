package my.sport.data.jpa.mapper;

import lombok.AllArgsConstructor;
import my.sport.data.jpa.entity.JpaRole;
import my.sport.domain.entity.Role;

@AllArgsConstructor
public class RoleMapper {

    public JpaRole map(Role source) {
        JpaRole target = new JpaRole();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }

    public Role map(JpaRole source) {
        return Role.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
