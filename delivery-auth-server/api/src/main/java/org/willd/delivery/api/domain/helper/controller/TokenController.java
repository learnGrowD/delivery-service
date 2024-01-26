package org.willd.delivery.api.domain.helper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.willd.delivery.api.domain.helper.business.TokenBusiness;
import org.willd.delivery.api.domain.helper.controller.model.IssuedTokenRequest;
import org.willd.delivery.common.api.Api;
import org.willd.delivery.common.error.ErrorCode;

@RequiredArgsConstructor
@RestController
@RequestMapping("will-d/v1/delivery/auth/token")
public class TokenController {

    private final TokenBusiness tokenBusiness;

    @PostMapping("issue")
    public ResponseEntity issueToken(@Valid @RequestBody IssuedTokenRequest issuedTokenRequest) {
        Api a = Api.ERROR(ErrorCode.VALIDATION, "이것을 파싱하는게 핵심이다!!!!!!!!!!!!!!ㅎㅎ!!!!!!!!!!!!!!!!!!!!!!!!");
        return ResponseEntity
                .status(500)
                .body(a);
    }
}
