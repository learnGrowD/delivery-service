package org.willd.delivery.db.storeuser;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends ReactiveCrudRepository<StoreUserEntity, Long> {

}
