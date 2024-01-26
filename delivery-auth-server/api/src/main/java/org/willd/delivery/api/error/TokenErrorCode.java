package org.willd.delivery.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.willd.delivery.common.error.ErrorCodeIfs;

@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeIfs {
    INTERNAL_TOKEN_ERROR(400, 2000, "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2001, "인증 헤더 토큰이 없음"),
    INVALID_TOKEN(400, 2002, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 2003, "만료된 토큰");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
