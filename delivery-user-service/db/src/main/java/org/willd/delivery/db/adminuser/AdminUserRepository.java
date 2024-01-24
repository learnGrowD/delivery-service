package org.willd.delivery.db.adminuser;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends ReactiveCrudRepository<AdminUserEntity, Long> {

}
