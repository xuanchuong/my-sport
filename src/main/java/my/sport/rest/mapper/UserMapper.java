package my.sport.rest.mapper;

import my.sport.domain.entity.Player;
import my.sport.rest.dto.UserOutDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    UserOutDTO map(Player source);

}
