package org.will.delivery.api.exeptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCodeIfs;
import org.willd.delivery.common.exception.ApiException;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public Mono<ResponseEntity<Api<Object>>> apiException(ApiException apiException) {
        log.info("", apiException);
        ErrorCodeIfs errorCodeIfs = apiException.getErrorCodeIfs();
        String errorDescription = apiException.getErrorDescription();
        return Mono.just(ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs, errorDescription)));
    }
}
