package org.will.delivery.api.exeptionhandler.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationExceptionResponse {
    private String field;
    private String code;
    private String defaultMessage;
}
