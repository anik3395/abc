package org.example.digitalWalletSystem.auth.model;


public record RegisterRequest(
        String username,
        String email,
        String password
) {}
