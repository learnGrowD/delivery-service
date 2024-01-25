package org.will.delivery.web.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.will.delivery.web.domain.user.controller.model.request.UserRegisterRequest;
import org.will.delivery.web.domain.user.controller.model.response.UserDetailResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserListItemResponse;
import org.will.delivery.web.domain.user.controller.model.response.UserRegisterResponse;
import org.will.delivery.web.domain.user.converter.UserConverter;
import org.will.delivery.web.domain.user.service.UserService;
import org.willd.delivery.common.annotation.Business;
import org.willd.delivery.db.user.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    //여기서 호출 해야 하는 서버 Auth!!

    private final UserService userService;
    private final UserConverter userConverter;

    //0. @Valid를 통한 id, password 유효성 검사
    //1. 요청 값을 통해서 DB에 User 정보 저장
    //2. Auth 서버에 token 생성 요청
    //3. token과 함께 정보 전달
    public Mono<UserRegisterResponse> register(UserRegisterRequest userRegisterRequest) {
        return Mono.just(userRegisterRequest)
                .map(userConverter::toEntity)
                .flatMap(userService::create)
                .map(userConverter::toRegisterResponse);
    }

    //1. @Valid를 통한 Request 유효성 검사
    //2. id, password가 있는 Entity가 있는지 (사용자가 있는지 검사) -> Null check
    //3. 있다면 auth 서버에 token 요청
    //4. token 정보 리턴
//    public Mono<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
//        return userService.getUser(userLoginRequest.getEmail(), userLoginRequest.getPassword())
//                .map(userEntity -> {
//                   return userConverter.
//                });
//    }

    public Flux<UserListItemResponse> list() {
        return userService.getUserList().map(userConverter::toListItemResponse);
    }

    public Mono<UserDetailResponse> detail(Long id) {
        return userService.getUser(id).map(userConverter::toDetailResponse);
    }

}
