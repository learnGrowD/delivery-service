package org.willd.delivery.db.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.willd.delivery.common.annotation.Converter;
import org.willd.delivery.db.BaseEntity;
import org.willd.delivery.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Table("delivery_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UserEntity extends BaseEntity {

    private String name;

    private String email;

    private String password;

    private String status;

    private String address;

    @CreatedDate
    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}