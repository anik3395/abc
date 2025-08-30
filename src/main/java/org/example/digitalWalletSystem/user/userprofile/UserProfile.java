package org.example.digitalWalletSystem.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
