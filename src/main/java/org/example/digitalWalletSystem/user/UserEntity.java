package org.example.digitalWalletSystem.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    // ✅ UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Spring Security uses this to authenticate
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // or implement your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // or implement your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // or implement your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // or implement your logic
    }
}
