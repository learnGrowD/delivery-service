package org.will.delivery.api.domain.user.controller.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailResponse {
    private Long id;
    private String name;
    private String email;
}
