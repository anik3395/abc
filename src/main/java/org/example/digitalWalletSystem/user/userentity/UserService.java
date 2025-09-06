package org.example.digitalWalletSystem.user.userentity;

import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.example.digitalWalletSystem.common.specification.UserSpecification;
import org.example.digitalWalletSystem.exceptions.InvalidDataException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ApiResponse fetchAllUsers(String email, String mobileNumber, String businessName, String contactPerson, UserRole role) {
        Specification<UserEntity> spec = UserSpecification.withFilters(
                email,
                mobileNumber,
                businessName,
                contactPerson,
                role);

        List<UserEntity> users = userRepository.findAll(spec);

        if (users.isEmpty()) {
            throw new InvalidDataException("No User available in the system");
        }

        List<UserResponseModel> userResponseList = new ArrayList<>();

        for (UserEntity user : users) {
            UserResponseModel userResponseModel = new UserResponseModel();
            userResponseModel.setId(user.getId());
            userResponseModel.setUsername(user.getUsername());
            userResponseModel.setEmail(user.getEmail());
            userResponseModel.setRole(user.getRole());


                userResponseModel.setBusinessName(user.getUserProfile().getBusinessName());
                userResponseModel.setContactPerson(user.getUserProfile().getContactPerson());
                userResponseModel.setFullName(user.getUserProfile().getFullName());
                userResponseModel.setMobileNumber(user.getUserProfile().getMobileNumber());
                userResponseModel.setGender(user.getUserProfile().getGender());
                userResponseModel.setCity(user.getUserProfile().getCity());
                userResponseModel.setCountry(user.getUserProfile().getCountry());
                userResponseModel.setStreet(user.getUserProfile().getStreet());
                userResponseModel.setPostalCode(user.getUserProfile().getPostalCode());


            userResponseList.add(userResponseModel);
        }

        return ApiResponse.success("List of users:", userResponseList);
    }




//    public ApiResponse fetchAllUsers(String email, String mobileNumber, String businessName,
//                                     String contactPerson, UserRole role) {
//
//        // Step 1: Fetch all users from DB
//        List<UserEntity> users = userRepository.findAll();
//
//        if (users.isEmpty()) {
//            throw new InvalidDataException("No User available in the system");
//        }
//
////        // Step 2: Apply filters (if provided)
////        List<UserEntity> filteredUsers = users.stream()
////                .filter(u -> (email == null || u.getEmail().equalsIgnoreCase(email)))
////                .filter(u -> (mobileNumber == null || u.getUserProfile().getMobileNumber().equalsIgnoreCase(mobileNumber)))
////                .filter(u -> (businessName == null || u.getUserProfile().getBusinessName().equalsIgnoreCase(businessName)))
////                .filter(u -> (contactPerson == null || u.getUserProfile().getContactPerson().equalsIgnoreCase(contactPerson)))
////                .filter(u -> (role == null || u.getRole().equals(role)))
////                .collect(Collectors.toList());
//
//        if (filteredUsers.isEmpty()) {
//            throw new InvalidDataException("No users found with given filters");
//        }
//
//        // Step 3: Map entities → response models
//        List<UserResponseModel> userResponseModels = filteredUsers.stream().map(u -> {
//            UserResponseModel dto = new UserResponseModel();
//            dto.setId(u.getId());
//            dto.setUsername(u.getUsername());
//            dto.setEmail(u.getEmail());
//            dto.setRole(u.getRole());
//            dto.setFullName(u.getUserProfile().getFullName());
//            dto.setBusinessName(u.getUserProfile().getBusinessName());
//            dto.setContactPerson(u.getUserProfile().getContactPerson());
//            dto.setMobileNumber(u.getUserProfile().getMobileNumber());
//            dto.setGender(u.getUserProfile().getGender());
//            dto.setStreet(u.getUserProfile().getStreet());
//            dto.setCity(u.getUserProfile().getCity());
//            dto.setPostalCode(u.getUserProfile().getPostalCode());
//            dto.setCountry(u.getUserProfile().getCountry());
//            return dto;
//        }).collect(Collectors.toList());
//
//        // Step 4: Wrap response
//        return ApiResponse.success("List of users:", userResponseModels);
//    }

}
