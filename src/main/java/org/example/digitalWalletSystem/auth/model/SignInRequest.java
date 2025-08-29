package org.example.digitalWalletSystem.auth.model;

public record SignInRequest(
        String email,
        String password
) {}
