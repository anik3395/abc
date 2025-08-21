package org.example.digitalWalletSystem.auth;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.auth.model.AuthResponse;
import org.example.digitalWalletSystem.auth.model.RegisterRequest;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.utils.EndPointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = EndPointsUtils.ADMIN_SIGNUP)

    public ResponseEntity<ApiResponse<AuthResponse>> signUpAsAdmin(@RequestBody RegisterRequest registerRequest) {
           AuthResponse response = authService.signUpAsAdmin(registerRequest);
           return ResponseEntity.ok(ApiResponse.success("Admin successfully registered",response));
    }
}
