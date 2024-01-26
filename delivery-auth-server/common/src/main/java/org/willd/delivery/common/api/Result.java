package org.willd.delivery.common.api;

import lombok.*;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.error.ErrorCodeIfs;

@Getter
@Builder
public class Result {
    private Integer errorCode;
    private String description;

    public static Result OK() {
        return Result.builder()
                .errorCode(ErrorCode.OK.getErrorCode())
                .description(ErrorCode.OK.getDescription())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {
        return Result.builder()
                .errorCode(errorCodeIfs.getErrorCode())
                .description(errorCodeIfs.getDescription())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        return Result.builder()
                .errorCode(errorCodeIfs.getErrorCode())
                .description(errorDescription)
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx) {
        return Result.builder()
                .errorCode(errorCodeIfs.getErrorCode())
                .description(tx.getLocalizedMessage())
                .build();
    }
}
