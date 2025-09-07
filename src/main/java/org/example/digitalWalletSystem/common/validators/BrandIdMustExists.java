package org.example.digitalWalletSystem.common.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrandIdMustExistsValidator.class)
public @interface BrandIdMustExists {
    String message() default "Invalid brandId";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
