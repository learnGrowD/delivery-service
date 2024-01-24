package org.willd.delivery.db.storeuser.enums;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;

@AllArgsConstructor
public enum StoreUserStatus {
    REGISTERED("등록"),

    UNREGISTERED("해지");

    private final String description;
}
