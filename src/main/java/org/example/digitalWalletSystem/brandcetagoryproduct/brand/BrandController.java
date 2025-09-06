package org.example.digitalWalletSystem.brandcetagoryproduct.brand;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.model.BrandReqModel;
import org.example.digitalWalletSystem.common.SecurityConfig;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.utils.EndPointsUtils;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping(value = EndPointsUtils.CREATE_BRAND)
    public ResponseEntity<ApiResponse> createBrand(@RequestBody BrandReqModel brandReqModel) {
        UserEntity currentUser = SecurityConfig.getCurrentUser();
        return new ResponseEntity<>(brandService.createBrand(brandReqModel,currentUser), HttpStatus.CREATED);
    }
}
