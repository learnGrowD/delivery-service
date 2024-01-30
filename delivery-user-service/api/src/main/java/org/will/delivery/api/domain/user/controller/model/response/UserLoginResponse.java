package org.will.delivery.api.domain.user.controller.model.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserLoginResponse {
    private String accessToken;
    private LocalDateTime accessTokenExpiredAt;

    private String refreshToken;
    private LocalDateTime refreshTokenExpiredAt;
}
