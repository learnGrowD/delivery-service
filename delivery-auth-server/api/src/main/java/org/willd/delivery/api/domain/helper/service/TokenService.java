package org.willd.delivery.api.domain.helper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.willd.delivery.api.domain.helper.TokenDto;
import org.willd.delivery.api.domain.helper.controller.model.IssuedTokenRequest;
import org.willd.delivery.api.domain.helper.helper.TokenHelperIfs;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.exception.ApiException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public Mono<TokenDto> issueAccessToken(IssuedTokenRequest issuedTokenRequest) {
        return Mono.just(issuedTokenRequest)
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)))
                .map(request -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("userId", request.getUserId());
                    return data;
                })
                .map(data -> {
                    return tokenHelperIfs.issuedAccessToken(data);
                });
    }

    public Mono<TokenDto> issueRefreshToken(IssuedTokenRequest issuedTokenRequest) {
        return Mono.just(issuedTokenRequest)
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)))
                .map(request -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("userId", request.getUserId());
                    return data;
                })
                .map(data -> {
                    return tokenHelperIfs.issuedRefreshToken(data);
                });
    }

    public Mono<Long> validationTokenForUserId(String token) {
        return Mono.just(token)
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)))
                .map(tk -> {
                    return tokenHelperIfs.validationToken(tk).get("userId");
                })
                .map(it -> {
                    return Long.parseLong(it.toString());
                });
    }

}
