package org.will.delivery.web.exeptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception exception) {
        log.info("", exception);
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getErrorCode())
                .body(Api.ERROR(ErrorCode.INTERNAL_SERVER_ERROR, exception));
    }
}
