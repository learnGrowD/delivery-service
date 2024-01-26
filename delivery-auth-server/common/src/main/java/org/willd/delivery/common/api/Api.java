package org.willd.delivery.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.willd.delivery.common.error.ErrorCodeIfs;

@Getter
public class Api<T> {
    private Result result;
    private T body;

    public static <T> Api<T> OK(T body) {
        Api<T> api = new Api<>();
        api.result = Result.OK();
        api.body = body;
        return api;
    }

    public static <T> Api<T> ERROR(ErrorCodeIfs errorCodeIfs, T body) {
        Api<T> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs);
        api.body = body;
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs);
        api.body = new Object();
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs, errorDescription);
        api.body = new Object();
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs, tx);
        api.body = new Object();
        return api;
    }
}
