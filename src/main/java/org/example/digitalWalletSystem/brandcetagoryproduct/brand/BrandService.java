package org.example.digitalWalletSystem.brandcetagoryproduct.brand;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.model.BrandReqModel;
import org.example.digitalWalletSystem.common.SecurityConfig;
import org.example.digitalWalletSystem.common.pagination.ResponseUtils;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.specification.BrandSpecification;
import org.example.digitalWalletSystem.common.status.KhenaBechaStatus;
import org.example.digitalWalletSystem.exceptions.InvalidDataException;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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



    public ApiResponse fetchAllBrand(String name, KhenaBechaStatus status, Integer page, Integer size) {
        UserEntity currentUser = SecurityConfig.getCurrentUser();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Brand> brandPage = brandRepository.findAll(
                BrandSpecification.withFilters(currentUser, name, status),
                pageable
        );

        return ApiResponse.success("Brands fetched successfully", ResponseUtils.buildPageableSuccessResponse(brandPage).getData());

    }



}
