package org.example.digitalWalletSystem.brandcetagoryproduct.brand;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.model.BrandReqModel;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.exceptions.InvalidDataException;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    public ApiResponse createBrand(BrandReqModel brandReqModel, UserEntity currentUser) {

        Brand existingBrand = brandRepository.findByName(brandReqModel.name());
        if (existingBrand != null) {
            throw new InvalidDataException("Brand with name " + brandReqModel.name() + " already exists");
        }

        Brand brand = new Brand();
        brand.setUser(currentUser);
        brand.setName(brandReqModel.name());
        brand.setDescription(brandReqModel.description());
        brand.setCountry(brandReqModel.country());
        brand.setStatus(brandReqModel.status());
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        brandRepository.save(brand);
        return ApiResponse.success("Brand Created Successfully", brand);

    }
}
