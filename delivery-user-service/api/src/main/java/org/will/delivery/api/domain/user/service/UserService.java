package org.will.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.willd.delivery.common.error.ErrorCode;
import org.willd.delivery.common.exception.ApiException;
import org.willd.delivery.db.user.UserEntity;
import org.willd.delivery.db.user.UserRepository;
import org.willd.delivery.db.user.enums.UserStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Mono<UserEntity> create(UserEntity userEntity) {
        if (userEntity == null)
            throw new ApiException(ErrorCode.NULL_POINT);
        return userRepository.save(userEntity);
    }

    public Mono<UserEntity> getUser(String email, String password) {
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, UserStatus.REGISTERED.name())
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)));
    }

    public Mono<UserEntity> getUser(Long id) {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(id, UserStatus.REGISTERED.name())
                .switchIfEmpty(Mono.error(new ApiException(ErrorCode.NULL_POINT)));
    }

    public Flux<UserEntity> getUserList() {
        return userRepository.findAllByStatusOrderByIdDesc(UserStatus.REGISTERED.name());
    }
}
