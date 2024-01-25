package org.will.delivery.web.domain.user.controller.model.request;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
