package org.will.delivery.web.domain.user.controller.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.willd.delivery.db.user.enums.UserStatus;

@Getter
public class UserRegisterRequest {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String address;
}
