package org.will.delivery.web.client.auth.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExternalAuthIssuedTokenResponse {
    private String accessToken;
    private LocalDateTime accessTokenExpiredAt;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiredAt;
}
