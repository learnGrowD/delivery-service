package org.will.delivery.web.exeptionhandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.exception.ApiException;

@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class WebClientResponseExceptionHandler {

    @ExceptionHandler(value = WebClientResponseException.class)
    public ResponseEntity<Api<Object>> webClientResponseException(WebClientResponseException webClientResponseException) {
        String jsonString = webClientResponseException.getResponseBodyAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Api<Object> result = objectMapper.readValue(jsonString, new TypeReference<Api<Object>>() {});
            return ResponseEntity
                    .status(500)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(Api.ERROR(ErrorCode.INTERNAL_SERVER_ERROR, e));
        }
    }
}
