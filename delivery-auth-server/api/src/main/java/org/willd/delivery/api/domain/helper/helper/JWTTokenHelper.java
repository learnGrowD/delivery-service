package org.willd.delivery.api.domain.helper.helper;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.willd.delivery.api.domain.helper.TokenDto;
import org.willd.delivery.api.error.TokenErrorCode;
import org.willd.delivery.common.exception.ApiException;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenHelper implements TokenHelperIfs {
    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issuedAccessToken(Map<String, Object> data) {
        return issuedToken(data, accessTokenPlusHour);
    }

    @Override
    public TokenDto issuedRefreshToken(Map<String, Object> data) {
        return issuedToken(data, refreshTokenPlusHour);
    }

    @Override
    public Map<String, Object> validationToken(String token) {
        if (token == null)
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            Jwt<Header, Claims> jwt = jwtParser.parseClaimsJwt(token);
            return new HashMap<>(jwt.getBody());
        } catch (SignatureException e) {
            throw new ApiException(TokenErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN);
        } catch (Exception e) {
            throw new ApiException(TokenErrorCode.INTERNAL_TOKEN_ERROR);
        }
    }

    private TokenDto issuedToken(Map<String, Object> data, Long plusHour) {
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(plusHour);

        Date expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder()
                .signWith(key)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }
}
