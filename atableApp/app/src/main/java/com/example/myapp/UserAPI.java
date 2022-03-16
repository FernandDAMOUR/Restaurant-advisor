package com.example.myapp;

import com.example.myapp.model.LoginRequest;
import com.example.myapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST ("register")
    Call<User> createPost(@Body User user);


    @POST("auth")
    Call<User> createAuth(@Body LoginRequest loginRequest);

}
