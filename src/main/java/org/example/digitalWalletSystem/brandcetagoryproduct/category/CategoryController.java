package org.example.digitalWalletSystem.brandcetagoryproduct.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.brandcetagoryproduct.category.model.CategoryReqModel;
import org.example.digitalWalletSystem.brandcetagoryproduct.category.utils.CategoryEndPointUtil;
import org.example.digitalWalletSystem.common.security.SecurityConfig;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(value = CategoryEndPointUtil.CREATE_CATEGORY)
    public ResponseEntity<ApiResponse> createCategory( @Valid @RequestBody CategoryReqModel categoryReqModel){
        UserEntity user = SecurityConfig.getCurrentUser();
        ApiResponse response = categoryService.createCategory(categoryReqModel,user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
