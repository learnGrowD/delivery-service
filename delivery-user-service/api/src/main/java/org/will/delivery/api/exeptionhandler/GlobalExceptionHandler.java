package org.will.delivery.api.exeptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.error.ErrorCodeIfs;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Mono<ResponseEntity<Api<Object>>> exception(Exception e) {
        log.info("", e);
        ErrorCodeIfs errorCodeIfs = ErrorCode.INTERNAL_SERVER_ERROR;
        return Mono.just(ResponseEntity
                .status(errorCodeIfs.getErrorCode())
                .body(Api.ERROR(errorCodeIfs, e)));
    }
}
