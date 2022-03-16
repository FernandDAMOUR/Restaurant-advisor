package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapp.MenuAPI;
import com.example.myapp.OpinionAPI;
import com.example.myapp.model.MyListViewAdapter;
import com.example.myapp.R;
import com.example.myapp.RestaurantApi;
import com.example.myapp.model.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private static RestaurantApi restaurantApi;
    private static MenuAPI menuApi;
    private static OpinionAPI opinionApi;
    private Retrofit retrofit;
    private ListView listView;
    private MyListViewAdapter myListViewAdapter;
    private List<Restaurant> restaurants;
    private Button btnconnex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.listView);

        btnconnex = findViewById(R.id.buttonProfile);


        Button button = (Button) findViewById(R.id.buttonStartActivity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startRestActivity();

            }
        });

        btnconnex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        this.myListViewAdapter = new MyListViewAdapter(getApplicationContext(), restaurants);
        this.listView.setAdapter(myListViewAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Récupere le restaurant depuis la liste
                Restaurant restaurant = restaurants.get(position);

                //Activité pour afficher la fiche du restaurant
                startActivity(restaurant);
            }
        });

        this.configureRetrofit();

        getRestaurantsViaAPI();


    }

    //Lancement d'une activité
    private void  startActivity(Restaurant restaurant){
        Intent intent =new Intent(this , MainActivity2.class);
        intent.putExtra("restaurant",restaurant);
        startActivity(intent);
    }

    private void startRestActivity(){

        Intent intent = new Intent(this, RestActivity.class);
        //intent.putExtra("String","Bonjour ceci n'est pas un restaurant ");
        startActivity(intent);

    }

    //Configuration de retrofit
    private void configureRetrofit() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restaurantApi = retrofit.create(RestaurantApi.class);
        menuApi = retrofit.create(MenuAPI.class);
        opinionApi = retrofit.create(OpinionAPI.class);
    }


    //Get via API
    private void getRestaurantsViaAPI() {

           restaurantApi.getRestaurants().enqueue(new Callback<List<Restaurant>>() {
               @Override
               public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                   Log.d(TAG, "onResponse:");
                   List<Restaurant> restaurantsList = response.body();
                   if (restaurantsList != null){
                       restaurants.addAll(restaurantsList);
                       myListViewAdapter.notifyDataSetChanged();
                       for (Restaurant restaurant: restaurantsList){
                           Log.d(TAG ,"restaurant recu " +restaurant.getId() +" " +restaurant.getName());
                       }
                   }
                   else {
                       Log.d(TAG, "onResponse: restaurant is empty " );
                   }
               }

               @Override
               public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                   Log.e(TAG, "onFailure " + t.getMessage());
               }
           });
    }

    public static MenuAPI getMenuApi() {
        return menuApi;
    }
    public static OpinionAPI getOpinionApi() {
        return opinionApi;
    }
}





