package org.willd.delivery.db.adminuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminUserRole {
    MASTER("마스터"),
    ADMIN("관리자"),
    USER("일반유저");

    private final String description;
}
