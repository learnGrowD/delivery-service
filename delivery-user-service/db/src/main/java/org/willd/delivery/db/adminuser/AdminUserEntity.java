package org.willd.delivery.db.adminuser;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.willd.delivery.db.adminuser.enums.AdminUserRole;
import org.willd.delivery.db.adminuser.enums.AdminUserStatus;
import org.willd.delivery.db.BaseEntity;

import java.time.LocalDateTime;

@Table("store_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AdminUserEntity extends BaseEntity {
    private Long storeId;

    private String email;

    private String password;

    private AdminUserStatus status;

    private AdminUserRole role;

    @CreatedDate
    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}
