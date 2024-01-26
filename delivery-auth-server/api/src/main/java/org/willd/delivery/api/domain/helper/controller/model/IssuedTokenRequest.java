package org.willd.delivery.api.domain.helper.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class IssuedTokenRequest {

    @NotNull
    private Long userId;

}
