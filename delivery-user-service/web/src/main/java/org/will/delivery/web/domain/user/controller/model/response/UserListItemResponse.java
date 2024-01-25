package org.will.delivery.web.domain.user.controller.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserListItemResponse {
    private Long id;
    private String name;
    private String email;
}
