package com.samiwogdev.loantrust.api;

import com.samiwogdev.loantrust.model.AuthResponse;
import com.samiwogdev.loantrust.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("authCustomer")
    Call<AuthResponse> authCustomer(
            @Field("email") String email,
            @Field("account") String account
    );

    @FormUrlEncoded
    @POST("loginAuth")
    Call<LoginResponse> login(
           @Field("email") String email,
           @Field("password") String password
    );
}
