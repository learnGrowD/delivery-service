package org.will.delivery.web.client.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExternalAuthIssuedTokenRequest {
    private Long userId;
}
