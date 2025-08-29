package org.example.digitalWalletSystem.auth;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.auth.model.AuthResponse;
import org.example.digitalWalletSystem.auth.model.RegisterRequest;
import org.example.digitalWalletSystem.auth.model.SignInRequest;
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

    @PostMapping(value = EndPointsUtils.SELLER_SIGNUP)
    public ResponseEntity<ApiResponse<AuthResponse>> signUpAsSeller(@RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authService.signUpAsSeller(registerRequest);
        return ResponseEntity.ok(ApiResponse.success("Seller successfully registered",response));
    }


    @PostMapping(value = EndPointsUtils.BUYER_SIGNUP)
    public ResponseEntity<ApiResponse<AuthResponse>> signUpAsBuyer(@RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authService.signUpAsBuyer(registerRequest);
        return ResponseEntity.ok(ApiResponse.success("Buyer successfully registered",response));
    }

    @PostMapping(value = EndPointsUtils.USERS_SIGNIN)
    public ResponseEntity<ApiResponse<AuthResponse>> signInAllUsers(@RequestBody SignInRequest  signInRequest) {
        AuthResponse response = authService.signInAllUsers(signInRequest);
        return ResponseEntity.ok(ApiResponse.success("Users SignIn successfully done",response));
    }
}
