package org.willd.delivery.db.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
    //select * from delivery_user where id = ? and status = ? order by id desc limit 1;
    Mono<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    //select * from delivery_user where email = ? and password = ? and status = ? order by id desc limit 1;
    Mono<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, String status);

    //select * from delivery_user where status = ? order by id desc;
    Flux<UserEntity> findAllByStatusOrderByIdDesc(String status);
}
