package org.willd.delivery.api.domain.helper.business;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.willd.delivery.api.domain.helper.TokenDto;
import org.willd.delivery.api.domain.helper.controller.TokenController;
import org.willd.delivery.api.domain.helper.controller.model.IssuedTokenRequest;
import org.willd.delivery.api.domain.helper.controller.model.IssuedTokenResponse;
import org.willd.delivery.api.domain.helper.converter.TokenConverter;
import org.willd.delivery.api.domain.helper.service.TokenService;
import org.willd.delivery.common.annotation.Business;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;
    public Mono<IssuedTokenResponse> issueToken(IssuedTokenRequest issuedTokenRequest) {
        return Mono.zip(
                tokenService.issueAccessToken(issuedTokenRequest),
                tokenService.issueRefreshToken(issuedTokenRequest)
        ).map(tuple -> {
            TokenDto accessToken = tuple.getT1();
            TokenDto refreshToken = tuple.getT2();
            return tokenConverter.toIssuedTokenResponse(accessToken, refreshToken);
        });
    }
}
