package mapper;

import entity.Player;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS
)
public interface PlayerMapper {

    Player map(domain.entity.Player source);

    domain.entity.Player map(Player source);
}
