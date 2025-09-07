package org.example.digitalWalletSystem.brandcetagoryproduct.category.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.digitalWalletSystem.common.status.KhenaBechaStatus;
import org.example.digitalWalletSystem.common.validators.BrandIdMustExists;

public record CategoryReqModel(
        @NotBlank
        String name,

        String description,

        @NotNull
        KhenaBechaStatus status,

        @NotNull
        @BrandIdMustExists
        Long brandId

) {
}
