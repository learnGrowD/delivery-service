package org.willd.delivery.api.domain.helper.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.willd.delivery.api.domain.helper.TokenDto;

import java.time.LocalDateTime;

@Getter
@Builder
public class IssuedTokenResponse {
    private String accessToken;
    private LocalDateTime accessTokenExpiredAt;

    private String refreshToken;
    private LocalDateTime refreshTokenExpiredAt;
}
