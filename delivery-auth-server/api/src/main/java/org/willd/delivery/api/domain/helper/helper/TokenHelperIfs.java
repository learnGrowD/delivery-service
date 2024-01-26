package org.willd.delivery.api.domain.helper.helper;

import org.willd.delivery.api.domain.helper.TokenDto;

import java.util.Map;

public interface TokenHelperIfs {
    TokenDto issuedAccessToken(Map<String, Object> data);

    TokenDto issuedRefreshToken(Map<String, Object> data);

    Map<String, Object> validationToken(String token);
}
