package org.example.digitalWalletSystem.user.userentity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserResponseModel {
    private Long id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String fullName;
    private String businessName;
    private String contactPerson;
    private String mobileNumber;
    private String gender;
    private String street;
    private String city;
    private String postalCode;
    private String country;


}
