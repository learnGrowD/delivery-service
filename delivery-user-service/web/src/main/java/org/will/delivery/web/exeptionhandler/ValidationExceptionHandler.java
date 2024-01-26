package org.will.delivery.web.exeptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.will.delivery.web.exeptionhandler.model.ValidationExceptionResponse;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(value = 0)
public class ValidationExceptionHandler {

    @ExceptionHandler(value = WebExchangeBindException.class)
    public Mono<ResponseEntity<Api<Object>>> webExchangeBindException(
            WebExchangeBindException webExchangeBindException
    ) {
        log.error("", webExchangeBindException);

        List<ValidationExceptionResponse> codes = webExchangeBindException.getFieldErrors().stream()
                .map(fieldError -> {
                    return ValidationExceptionResponse.builder()
                            .field(fieldError.getField())
                            .code(fieldError.getCode())
                            .defaultMessage(fieldError.getDefaultMessage())
                            .build();
                })
                .toList();

        return Mono.just(ResponseEntity
                .status(ErrorCode.VALIDATION.getHttpStatusCode())
                .body(Api.ERROR(ErrorCode.VALIDATION, codes)));
    }
}
