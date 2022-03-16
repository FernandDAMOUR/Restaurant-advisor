package com.example.myapp;


import com.example.myapp.model.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MenuAPI {

    @GET("restaurant/{restaurant_id}/menus")
    Call<List<Menu>> getMenus(@Path( "restaurant_id") String id);

    @POST("restaurant/{restaurant_id}/menu")
    Call<Menu> addMenu(@Body Menu menu);

    @PUT("restaurant/{restaurant_id}/menu/{menu_id}")
    Call<Menu> updateMenu(Menu menu);

    @DELETE("restaurant/{restaurant_id}/menu/{menu_id}")
    Call<Menu> deleteMenu(Menu menu);

}
