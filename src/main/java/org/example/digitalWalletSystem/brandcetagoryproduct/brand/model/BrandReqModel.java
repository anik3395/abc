package org.example.digitalWalletSystem.brandcetagoryproduct.brand.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.digitalWalletSystem.common.status.KhenaBechaStatus;

public record BrandReqModel(
        @NotBlank
        @Size(max = 100, message = "Brand Name must not exceed 100 characters")
        String name,

        String description,

        @NotBlank
        @Size(max = 10, message = "Country must not exceed 100 characters")
        String country,

        @NotNull
        KhenaBechaStatus status
) {
}
