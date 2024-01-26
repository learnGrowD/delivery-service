package org.will.delivery.web.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.will.delivery.web.domain.user.business.UserBusiness;
import org.will.delivery.web.domain.user.controller.model.request.UserLoginRequest;
import org.will.delivery.web.domain.user.controller.model.request.UserRegisterRequest;
import org.will.delivery.web.domain.user.controller.model.response.UserLoginResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserRegisterResponse;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.exception.ApiException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("open-api/will-d/v1/delivery/user/web")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping
    public Mono<Api<UserRegisterResponse>> register(
            @Valid
            @RequestBody
            UserRegisterRequest userRegisterRequest
    ) {
        return userBusiness.register(userRegisterRequest)
                .map(Api::OK);
    }

    @PostMapping("login")
    public Mono<Api<UserLoginResponse>> login(
            @Valid
            @RequestBody
            UserLoginRequest userLoginRequest
    ) {
        throw new ApiException(ErrorCode.NULL_POINT);
//        return userBusiness.login(userLoginRequest)
//                .map(Api::OK);
    }
}
