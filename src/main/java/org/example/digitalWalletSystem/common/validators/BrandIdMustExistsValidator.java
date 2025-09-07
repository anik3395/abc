package org.example.digitalWalletSystem.common.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandIdMustExistsValidator  implements ConstraintValidator<BrandIdMustExists, Long> {

    @Autowired
    private  BrandRepository brandRepository;

    @Override
    public void initialize(BrandIdMustExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long brandId, ConstraintValidatorContext constraintValidatorContext) {
        return brandRepository.existsById(brandId);
    }
}
