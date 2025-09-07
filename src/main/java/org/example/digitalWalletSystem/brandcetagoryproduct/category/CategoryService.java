package org.example.digitalWalletSystem.brandcetagoryproduct.category;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.Brand;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.BrandRepository;
import org.example.digitalWalletSystem.brandcetagoryproduct.category.model.CategoryReqModel;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.status.KhenaBechaStatus;
import org.example.digitalWalletSystem.exceptions.InvalidDataException;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ApiResponse createCategory(CategoryReqModel categoryReqModel, UserEntity user) {
        Category existingCategory = categoryRepository.findByName(categoryReqModel.name());
        if (existingCategory != null) {
            throw new InvalidDataException("Category with name " + categoryReqModel.name() + " already exists");
        }

        if(categoryReqModel.status().equals(KhenaBechaStatus.ARCHIVED)){
            throw new InvalidDataException("For creation of Category, ARCHIVED is not possible");
        }

//        Brand existingBrand = brandRepository.findById(categoryReqModel.brandId()).orElse(null);
//        if(existingBrand == null){
//            throw new InvalidDataException("Brand with id " + categoryReqModel.brandId() + " does not exist");
//        }

        Category category = new Category();
        category.setUser(user);
        category.setName(categoryReqModel.name());
        category.setDescription(categoryReqModel.description());
        category.setStatus(categoryReqModel.status());
        category.setBrandId(categoryReqModel.brandId());
        categoryRepository.save(category);
        return ApiResponse.success("Category created Successfully",category);
    }
}
