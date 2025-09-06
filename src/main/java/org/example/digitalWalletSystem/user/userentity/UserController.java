package org.example.digitalWalletSystem.user.userentity;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.utils.EndPointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = EndPointsUtils.FETCH_ALL_USERS)
    public ResponseEntity<ApiResponse> fetchAllUsers(@RequestParam(required = false) String email,
                                                     @RequestParam(required = false) String mobileNumber,
                                                     @RequestParam(required = false) String businessName,
                                                     @RequestParam(required = false) String contactPerson,
                                                     @RequestParam(required = false) UserRole role) {
        ApiResponse response = userService.fetchAllUsers(email,mobileNumber,businessName,contactPerson,role);
        return ResponseEntity.ok(response);
    }
}
