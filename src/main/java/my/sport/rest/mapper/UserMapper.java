package my.sport.rest.mapper;

import my.sport.domain.entity.Player;
import my.sport.rest.dto.UserOutDTO;

public class UserMapper {

    public UserOutDTO map(Player source) {
        UserOutDTO userOutDTO = new UserOutDTO();
        userOutDTO.setId(source.getId());
        userOutDTO.setFirstName(source.getFirstName());
        userOutDTO.setLastName(source.getLastName());
        userOutDTO.setEmail(source.getEmail());
        userOutDTO.setPhoneNumber(source.getPhoneNumber());
        return userOutDTO;
    }
}
