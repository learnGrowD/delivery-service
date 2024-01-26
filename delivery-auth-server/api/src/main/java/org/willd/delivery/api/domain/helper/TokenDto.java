package org.willd.delivery.api.domain.helper;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
@Getter
@Builder
public class TokenDto {
    private String token;
    private LocalDateTime expiredAt;
}
