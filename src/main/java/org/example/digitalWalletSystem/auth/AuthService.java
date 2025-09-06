package org.example.digitalWalletSystem.auth;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.auth.model.AuthResponse;
import org.example.digitalWalletSystem.auth.model.RegisterRequest;
import org.example.digitalWalletSystem.auth.model.SignInRequest;
import org.example.digitalWalletSystem.exceptions.InvalidDataException;
import org.example.digitalWalletSystem.token.JwtService;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.example.digitalWalletSystem.user.userentity.UserRepository;
import org.example.digitalWalletSystem.user.userentity.UserRole;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private static final long ACCESS_TOKEN_EXP = 15 * 60 * 1000; // 15 min
    private static final long REFRESH_TOKEN_EXP = 7 * 24 * 60 * 60 * 1000; // 7 days


    public AuthResponse signUpAsAdmin(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new InvalidDataException("Email already in use");
        }

        UserEntity user = UserEntity.builder()
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(UserRole.ADMIN)
                .build();

        user.setUsername(registerRequest.email());
        userRepository.save(user);
        return generateTokens(user);

    }


    public AuthResponse signUpAsSeller(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new InvalidDataException("Email already in use");
        }

        UserEntity user = UserEntity.builder()
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(UserRole.SELLER)
                .build();

        user.setUsername(registerRequest.email());
        userRepository.save(user);
        return generateTokens(user);

    }


    public AuthResponse signUpAsBuyer(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new InvalidDataException("Email already in use");
        }

        UserEntity user = UserEntity.builder()
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(UserRole.BUYER)
                .build();

        user.setUsername(registerRequest.email());
        userRepository.save(user);
        return generateTokens(user);
    }


    private AuthResponse generateTokens(UserEntity user) {
        String accessToken = jwtService.generateToken(user, ACCESS_TOKEN_EXP);
        String refreshToken = jwtService.generateToken(user, REFRESH_TOKEN_EXP);
        return new AuthResponse(accessToken, refreshToken);
    }


    public AuthResponse signInAllUsers(SignInRequest signInRequest) {

        Optional<UserEntity> user = userRepository.findByEmail(signInRequest.email());
        if (user.isEmpty()) {
            throw new InvalidDataException("User not found");
        }
        if(!passwordEncoder.matches(signInRequest.password(),user.get().getPassword())) {
            throw new BadCredentialsException("Invalid password.Please try again");
        }
        return generateTokens(user.get()); }

}
