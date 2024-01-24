package org.willd.delivery.db.storeuser;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.willd.delivery.db.storeuser.enums.StoreUserRole;
import org.willd.delivery.db.storeuser.enums.StoreUserStatus;
import org.willd.delivery.db.BaseEntity;

import java.time.LocalDateTime;

@Table("store_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class StoreUserEntity extends BaseEntity {
    private Long storeId;

    private String email;

    private String password;

    private StoreUserStatus status;

    private StoreUserRole role;

    @CreatedDate
    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}
