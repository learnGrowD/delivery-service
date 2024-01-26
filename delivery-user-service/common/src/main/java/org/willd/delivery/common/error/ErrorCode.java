package org.willd.delivery.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs {
    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 에러"),
    ENUM_NOT_CONVERTING(HttpStatus.INTERNAL_SERVER_ERROR.value(), 511, "Enum 변환 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null Point"),
    VALIDATION(HttpStatus.BAD_REQUEST.value(), 513, "잘못된 형식 요청"),
    JSON_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 514, "json parsing error");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
