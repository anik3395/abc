package org.example.digitalWalletSystem.common.utils;

public class EndPointsUtils {
    public static final String ADMIN_SIGNUP = "/api/auth/admin/sign-up";
    public static final String SELLER_SIGNUP = "/api/auth/seller/sign-up";
    public static final String BUYER_SIGNUP = "/api/auth/buyer/sign-up";
    public static final String USERS_SIGNIN = "/api/auth/users/sign-in";


    //User
    public static final String FETCH_ALL_USERS = "/api/auth/fetch/all-users";



    //UserProfile
    public static final String CREATE_USER_PROFILE = "/api/auth/create/user-profile";
    public static final String ASSIGN_USER_PROFILE_WITH_USER = "/api/auth/assign-user-profile-with-user";


    //Brand
    public static final String CREATE_BRAND = "/api/v1/public/create/brand";
    public static final String FETCH_ALL_BRAND = "/api/auth/fetch/brand";
    public static final String UPDATE_BRAND = "/api/auth/update/brand";
    public static final String DELETE_BRAND = "/api/auth/delete/brand";


}
