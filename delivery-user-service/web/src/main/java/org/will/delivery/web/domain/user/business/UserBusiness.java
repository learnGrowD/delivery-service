package org.will.delivery.web.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.will.delivery.web.client.WebFluxClientIfs;
import org.will.delivery.web.client.auth.AuthWebFluxClient;
import org.will.delivery.web.client.auth.model.ExternalAuthIssuedTokenRequest;
import org.will.delivery.web.client.auth.model.ExternalAuthIssuedTokenResponse;
import org.will.delivery.web.domain.user.controller.model.request.UserLoginRequest;
import org.will.delivery.web.domain.user.controller.model.request.UserRegisterRequest;
import org.will.delivery.web.domain.user.controller.model.response.UserDetailResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserListItemResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserLoginResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserRegisterResponse;
import org.will.delivery.web.domain.user.converter.UserConverter;
import org.will.delivery.web.domain.user.service.UserService;
import org.willd.delivery.common.annotation.Business;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.exception.ApiException;
import org.willd.delivery.db.user.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    //0. @Valid를 통한 id, password 유효성 검사
    //1. 요청 값을 통해서 DB에 User 정보 저장
    //2. Auth 서버에 token 생성 요청
    //3. token과 함께 정보 전달
    @Transactional
    public Mono<UserRegisterResponse> register(UserRegisterRequest userRegisterRequest) {
        WebClient webClient = WebClient.create("http://localhost:8082");

        Mono<UserEntity> storedUserEntity = Mono.just(userRegisterRequest)
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)))
                .map(userConverter::toEntity)
                .flatMap(userService::create);

        return storedUserEntity
                .flatMap(it -> {
                    ExternalAuthIssuedTokenRequest externalAuthIssuedTokenRequest = ExternalAuthIssuedTokenRequest.builder()
                            .userId(it.getId())
                            .build();
                    return webClient.post()
                            .uri("/will-d/v1/delivery/auth/token/issue")
                            .body(BodyInserters.fromValue(externalAuthIssuedTokenRequest))
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<Api<ExternalAuthIssuedTokenResponse>>() {});
                }).map(it -> {
                    return userConverter.toRegisterResponse(it.getBody());
                });
    }

    //1. @Valid를 통한 Request 유효성 검사
    //2. id, password가 있는 Entity가 있는지 (사용자가 있는지 검사) -> Null check
    //3. 있다면 auth 서버에 token 요청
    //4. token 정보 리턴
    @Transactional
    public Mono<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
        WebClient webClient = WebClient.create("http://localhost:8082");
        if (userLoginRequest == null)
            throw new ApiException(ErrorCode.NULL_POINT);
        return userService.getUser(userLoginRequest.getEmail(), userLoginRequest.getPassword())
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)))
                .flatMap(it -> {
                    ExternalAuthIssuedTokenRequest externalAuthIssuedTokenRequest = ExternalAuthIssuedTokenRequest.builder()
                            .userId(it.getId())
                            .build();
                    return webClient.post()
                            .uri("/will-d/v1/delivery/auth/token/issu")
                            .body(BodyInserters.fromValue(externalAuthIssuedTokenRequest))
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<Api<ExternalAuthIssuedTokenResponse>>() {});

                }).map(it -> {
                    return userConverter.toLoginResponse(it.getBody());
                });
    }

    public Flux<UserListItemResponse> list() {
        return userService.getUserList().map(userConverter::toListItemResponse);
    }

    public Mono<UserDetailResponse> detail(Long id) {
        return userService.getUser(id).map(userConverter::toDetailResponse);
    }

}
