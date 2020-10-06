package my.sport.controller.mapper;

import my.sport.domain.vo.CreateUserCommand;
import my.sport.rest.dto.CreateUserCommandDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS
)
public interface PlayerControllerMapper {

    CreateUserCommand map(CreateUserCommandDTO source);
}
