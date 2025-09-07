package org.example.digitalWalletSystem.brandcetagoryproduct.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> , JpaSpecificationExecutor<Brand> {
    Brand findByName( String name);
}
