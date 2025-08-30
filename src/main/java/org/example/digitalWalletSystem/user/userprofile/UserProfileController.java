package org.example.digitalWalletSystem.user;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.utils.EndPointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

     @PostMapping(value = EndPointsUtils.CREATE_USER_PROFILE)
     public ResponseEntity<ApiResponse> createUserProfile(@RequestBody UserProfile userProfile) {
         ApiResponse response = userProfileService.createUserProfile(userProfile);
         return ResponseEntity.ok(response);
     }

    @PutMapping(value = EndPointsUtils.ASSIGN_USER_PROFILE_WITH_USER)
    public ResponseEntity<ApiResponse<Void>> assignProfile(
            @RequestParam Long userId,
            @RequestParam Long profileId) {

        userProfileService.assignProfileToUser(userId, profileId);
        return ResponseEntity.ok(ApiResponse.successMessage("Profile assigned to user successfully"));
    }


}
