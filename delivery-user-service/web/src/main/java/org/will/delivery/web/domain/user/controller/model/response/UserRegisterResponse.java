package org.will.delivery.web.domain.user.controller.model.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRegisterResponse {
    private Long id;

    private String accessToken;
    private LocalDateTime accessTokenExpiredAt;

    private String refreshToken;
    private LocalDateTime refreshTokenExpiredAt;
}
