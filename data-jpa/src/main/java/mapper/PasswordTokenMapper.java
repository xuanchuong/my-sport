package mapper;

import domain.entity.PasswordResetToken;
import entity.PasswordResetTokenJpa;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS
)
public interface PasswordTokenMapper {

    PasswordResetTokenJpa map(PasswordResetToken source);
}
