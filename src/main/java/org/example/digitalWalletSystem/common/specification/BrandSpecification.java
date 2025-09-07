package org.example.digitalWalletSystem.common.specification;

import jakarta.persistence.criteria.Predicate;
import org.example.digitalWalletSystem.brandcetagoryproduct.brand.Brand;
import org.example.digitalWalletSystem.common.status.KhenaBechaStatus;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BrandSpecification {

    public static Specification<Brand> withFilters(UserEntity currentUser, String name, KhenaBechaStatus status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            //Only show brands of the logged-in user
            predicates.add(cb.equal(root.get("user"), currentUser));

            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
