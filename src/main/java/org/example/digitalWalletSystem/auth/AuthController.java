package org.example.digitalWalletSystem.auth;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.common.EndPointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    @PostMapping(value = EndPointsUtils.ADMIN_SIGNUP)
//    public ResponseEntity
}
