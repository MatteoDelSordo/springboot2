package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.RefreshTokenDto;
import it.siinfo.springboot2.entity.RefreshToken;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {

    RefreshToken toRefreshToken (RefreshTokenDto refreshTokenDto);

    RefreshTokenDto toRefreshTokenDto (RefreshToken refreshToken);


    List<RefreshToken> toRefreshTokenList (List<RefreshTokenDto> rf);

    List<RefreshTokenDto> toRefreshTokenDtoList (List<RefreshToken> rf);

}
