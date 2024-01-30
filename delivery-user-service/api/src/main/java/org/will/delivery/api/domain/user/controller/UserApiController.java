package org.will.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.will.delivery.api.domain.user.business.UserBusiness;
import org.will.delivery.api.domain.user.controller.model.response.UserDetailResponse;
import org.will.delivery.api.domain.user.controller.model.response.UserListItemResponse;
import org.willd.delivery.common.api.Api;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/will-d/v1/user/web")
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
