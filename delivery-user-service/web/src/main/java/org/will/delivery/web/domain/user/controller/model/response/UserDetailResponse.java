package org.will.delivery.web.domain.user.controller.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailResponse {
    private Long id;
    private String name;
    private String email;
}
