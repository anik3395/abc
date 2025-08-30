package org.example.digitalWalletSystem.user;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.exceptions.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public ApiResponse createUserProfile(UserProfile userProfile) {
        UserProfile profileMobileNumber = userProfileRepository.findByMobileNumber(userProfile.getMobileNumber());
        if(profileMobileNumber != null) {
            throw new InvalidDataException("Mobile number is already in use");
        }
        userProfile.setMobileNumber(userProfile.getMobileNumber());
        userProfile.setBusinessName(userProfile.getBusinessName());
        userProfile.setContactPerson(userProfile.getContactPerson());
        userProfile.setCity(userProfile.getCity());
        userProfile.setCountry(userProfile.getCountry());
        userProfile.setStreet(userProfile.getStreet());
        userProfile.setPostalCode(userProfile.getPostalCode());
        userProfile.setGender(userProfile.getGender());
        userProfile.setFullName(userProfile.getFullName());
        userProfileRepository.save(userProfile);

        // Return only message without data
        return ApiResponse.successMessage("User profile created successfully");

    }



    public void assignProfileToUser(Long userId, Long profileId) {

        UserEntity user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new InvalidDataException("User not found");
        }
        UserProfile userProfile = userProfileRepository.findById(profileId).orElse(null);
        if(userProfile == null) {
            throw new InvalidDataException("Profile not found");
        }

        Optional<UserEntity> existingUserWithProfile = userRepository.findByUserProfile(userProfile);
        if (existingUserWithProfile.isPresent() && !existingUserWithProfile.get().getId().equals(userId)) {
            throw new InvalidDataException("This profile is already assigned to another user");
        }
        user.setUserProfile(userProfile);
        userRepository.save(user);

    }
}
