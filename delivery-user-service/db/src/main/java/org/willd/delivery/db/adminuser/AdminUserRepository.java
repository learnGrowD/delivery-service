package org.willd.delivery.db.adminuser;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AdminUserRepository extends ReactiveCrudRepository<AdminUserEntity, Long> {
    //select * from admin_user where id = ? and status =? order by id desc limit 1;
    Mono<AdminUserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    //select * from admin_user where email =? and password = ? and status = ? order by id desc limit 1
    Mono<AdminUserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, String status);

    //select * from admin_user where status = ? order by id desc
    Flux<AdminUserEntity> findAllByStatusOrderByIdDesc(String status);

    //select * from admin_user where status = ? and role = ? order by id desc
    Flux<AdminUserEntity> findAllByStatusAndRoleOrderByIdDesc(String status, String role);
}
