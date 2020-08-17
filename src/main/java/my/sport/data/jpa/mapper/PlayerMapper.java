package my.sport.data.jpa.mapper;

import my.sport.data.jpa.entity.JpaPlayer;
import my.sport.domain.entity.Player;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS
)
public interface PlayerMapper {

    JpaPlayer map(Player source);

    Player map(JpaPlayer source);
}
