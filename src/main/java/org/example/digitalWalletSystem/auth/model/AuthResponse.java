package org.example.digitalWalletSystem.auth.model;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
