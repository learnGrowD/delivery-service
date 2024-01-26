package org.will.delivery.web.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.will.delivery.web.domain.user.business.UserBusiness;
import org.will.delivery.web.domain.user.controller.model.request.UserLoginRequest;
import org.will.delivery.web.domain.user.controller.model.request.UserRegisterRequest;
import org.will.delivery.web.domain.user.controller.model.response.UserDetailResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserListItemResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserLoginResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserRegisterResponse;
import org.willd.delivery.common.api.Api;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/will-d/v1/delivery/user/web")
public class UserApiController {
    private final UserBusiness userBusiness;

    @GetMapping("list")
    public Mono<Api<List<UserListItemResponse>>> list() {
        return userBusiness.list()
                .collectList()
                .map(Api::OK);
    }

    @GetMapping("{id}")
    public Mono<Api<UserDetailResponse>> detail(
            @PathVariable("id")
            Long id
    ) {
        return userBusiness.detail(id)
                .map(Api::OK);
    }
}
