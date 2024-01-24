package org.willd.delivery.db.adminuser.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminUserStatus {
    REGISTERED("등록"),

    UNREGISTERED("해지");

    private final String description;
}
