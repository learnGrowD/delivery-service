package org.will.delivery.web.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.will.delivery.web.domain.user.business.UserBusiness;
import org.will.delivery.web.domain.user.controller.model.request.UserRegisterRequest;
import org.will.delivery.web.domain.user.controller.model.response.UserDetailResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserListItemResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserRegisterResponse;
import org.willd.delivery.common.api.Api;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("will-d/v1/delivery/web/user")
public class UserController {
    private final UserBusiness userBusiness;
    @PostMapping
    public Mono<Api<UserRegisterResponse>> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userBusiness.register(userRegisterRequest)
                .map(Api::OK);
    }

    @GetMapping("list")
    public Mono<Api<List<UserListItemResponse>>> list() {
        return userBusiness.list()
                .collectList()
                .map(Api::OK);
    }

    @GetMapping("{id}")
    public Mono<Api<UserDetailResponse>> detail(@PathVariable("id") Long id) {
        return userBusiness.detail(id)
                .map(Api::OK);
    }
}
