package com.example.myapp;

import com.example.myapp.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestaurantApi {

    @GET("restaurants")
    Call<List<Restaurant>>getRestaurants();

    @POST("restaurant")
    Call<String> postRestaurant(@Body Restaurant restaurant);
}
