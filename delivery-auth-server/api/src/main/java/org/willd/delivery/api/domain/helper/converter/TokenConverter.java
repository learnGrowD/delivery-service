package org.willd.delivery.api.domain.helper.converter;

import lombok.RequiredArgsConstructor;
import org.willd.delivery.api.domain.helper.TokenDto;
import org.willd.delivery.api.domain.helper.controller.model.IssuedTokenResponse;
import org.willd.delivery.common.annotation.Converter;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public IssuedTokenResponse toIssuedTokenResponse(
            TokenDto accessTokenDto,
            TokenDto refreshTokenDto
    ) {
        return IssuedTokenResponse.builder()
                .accessToken(accessTokenDto.getToken())
                .accessTokenExpiredAt(accessTokenDto.getExpiredAt())
                .refreshToken(refreshTokenDto.getToken())
                .refreshTokenExpiredAt(refreshTokenDto.getExpiredAt())
                .build();
    }
}
