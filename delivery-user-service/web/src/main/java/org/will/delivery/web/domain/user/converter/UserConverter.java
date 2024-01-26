package org.will.delivery.web.domain.user.converter;

import org.will.delivery.web.client.auth.model.ExternalAuthIssuedTokenResponse;
import org.will.delivery.web.domain.user.controller.model.request.UserRegisterRequest;
import org.will.delivery.web.domain.user.controller.model.response.UserDetailResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserListItemResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserLoginResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserRegisterResponse;
import org.willd.delivery.common.annotation.Converter;
import org.willd.delivery.db.user.UserEntity;
import org.willd.delivery.db.user.enums.UserStatus;

@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest userRegisterRequest) {
        return UserEntity.builder()
                .name(userRegisterRequest.getName())
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .address(userRegisterRequest.getAddress())
                .status(UserStatus.REGISTERED.name())
                .unregisteredAt(null)
                .lastLoginAt(null)
                .build();
    }

    public UserRegisterResponse toRegisterResponse(ExternalAuthIssuedTokenResponse issuedTokenResult) {
        return UserRegisterResponse.builder()
                .accessToken(issuedTokenResult.getAccessToken())
                .accessTokenExpiredAt(issuedTokenResult.getAccessTokenExpiredAt())
                .refreshToken(issuedTokenResult.getRefreshToken())
                .refreshTokenExpiredAt(issuedTokenResult.getRefreshTokenExpiredAt())
                .build();
    }

    public UserLoginResponse toLoginResponse(ExternalAuthIssuedTokenResponse issuedTokenResult) {
        return UserLoginResponse.builder()
                .accessToken(issuedTokenResult.getAccessToken())
                .accessTokenExpiredAt(issuedTokenResult.getAccessTokenExpiredAt())
                .refreshToken(issuedTokenResult.getRefreshToken())
                .refreshTokenExpiredAt(issuedTokenResult.getRefreshTokenExpiredAt())
                .build();
    }

    public UserListItemResponse toListItemResponse(UserEntity userEntity) {
        return UserListItemResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }

    public UserDetailResponse toDetailResponse(UserEntity userEntity) {
        return UserDetailResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
