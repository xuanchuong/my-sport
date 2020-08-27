package my.sport.data.jpa.mapper;

import my.sport.data.jpa.entity.PasswordResetTokenJpa;
import my.sport.domain.entity.PasswordResetToken;
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
