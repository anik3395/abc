package org.example.digitalWalletSystem.brandcetagoryproduct.brand;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.model.BrandReqModel;
import org.example.digitalWalletSystem.common.security.SecurityConfig;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.status.KhenaBechaStatus;
import org.example.digitalWalletSystem.common.utils.EndPointsUtils;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping(value = EndPointsUtils.CREATE_BRAND)
    public ResponseEntity<ApiResponse> createBrand(@RequestBody BrandReqModel brandReqModel) {
        UserEntity currentUser = SecurityConfig.getCurrentUser();
        return new ResponseEntity<>(brandService.createBrand(brandReqModel,currentUser), HttpStatus.CREATED);
    }

    @GetMapping(value = EndPointsUtils.FETCH_ALL_BRAND)
    public ResponseEntity<ApiResponse> fetchAllBrand(@RequestParam (required = false) String name,
                                                     @RequestParam (required = false) KhenaBechaStatus status,
                                                     @RequestParam (required = false,defaultValue = "0") Integer page,
                                                     @RequestParam (required = false,defaultValue = "10") Integer size) {
        ApiResponse response = brandService.fetchAllBrand(name,status,page,size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
