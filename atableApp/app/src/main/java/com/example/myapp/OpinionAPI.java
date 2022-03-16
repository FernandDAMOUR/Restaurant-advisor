package com.example.myapp;

import android.util.Log;

import com.example.myapp.model.Menu;
import com.example.myapp.model.Opinion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface OpinionAPI {
    @GET("restaurant/{restaurant_id}/opinions")
    Call<List<Opinion>> getOpinions(@Path("restaurant_id") String id);
}
