package org.example.digitalWalletSystem.common.specification;

import jakarta.persistence.criteria.Predicate;
import org.example.digitalWalletSystem.user.userentity.UserEntity;
import org.example.digitalWalletSystem.user.userentity.UserRole;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> withFilters(
            String email,
            String mobileNumber,
            String businessName,
            String contactPerson,
            UserRole role
    ){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (email != null) {
                predicates.add(cb.like(cb.lower(root.get("email")), '%' + email.toLowerCase() + '%'));
            }


//            Join<Object, Object> profileJoin = root.join("userProfile", JoinType.LEFT);
//
//            if (mobileNumber != null) {
//                predicates.add(cb.equal(profileJoin.get("mobileNumber"), mobileNumber));
//            }
//            if (businessName != null) {
//                predicates.add(cb.like(cb.lower(profileJoin.get("businessName")), "%" + businessName.toLowerCase() + "%"));
//            }
//            if (contactPerson != null) {
//                predicates.add(cb.like(cb.lower(profileJoin.get("contactPerson")), "%" + contactPerson.toLowerCase() + "%"));
//            }

//Or,

            if (mobileNumber != null) {
                predicates.add(cb.equal(root.get("userProfile").get("mobileNumber"), mobileNumber));
            }
            if (businessName != null) {
                predicates.add(cb.like(cb.lower(root.get("userProfile").get("businessName")), "%" + businessName.toLowerCase() + "%"));
            }
            if (contactPerson != null) {
                predicates.add(cb.like(cb.lower(root.get("userProfile").get("contactPerson")), "%" + contactPerson.toLowerCase() + "%"));
            }


            if (role != null) {
                predicates.add(cb.equal(root.get("role"), role));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
