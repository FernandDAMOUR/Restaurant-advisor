package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.RestaurantApi;
import com.example.myapp.model.Restaurant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestActivity extends AppCompatActivity {

    private EditText et_restname;
    private EditText et_restdescription;
    private EditText et_restlocalization;
    private EditText et_restwebsite;
    private EditText et_resthours;
    private EditText et_restphonenumber;
    private TextView responseApi;
    private Button btnaddrestau;
    private Button buttonBack;
    private static RestaurantApi restaurantApi;

    String restname;
    String restdesc;
    String restloc;
    String restphone;
    String restweb;
    String resthours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        //getSupportActionBar().setTitle("Add Restaurant");

        et_restname = findViewById(R.id.Postname);
        et_restdescription = findViewById(R.id.Postdesc);
        et_restlocalization = findViewById(R.id.Postloc);
        et_restphonenumber= findViewById(R.id.Postphone);
        et_restwebsite = findViewById(R.id.Postwebsite);
        et_resthours = findViewById(R.id.Posthours);
        //responseApi = findViewById(R.id.responseApi);
        btnaddrestau= findViewById(R.id.buttonAddRest);
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener((v) -> {
            startBackActivity();
        });

        btnaddrestau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 restname = et_restname.getText().toString();
                 restdesc = et_restdescription.getText().toString();
                 restloc = et_restlocalization.getText().toString();
                 restphone = et_restphonenumber.getText().toString();
                 restweb = et_restwebsite.getText().toString();
                 resthours = et_resthours.getText().toString();


                if (restname.isEmpty() ||restdesc.isEmpty() ||restloc.isEmpty() || restphone.isEmpty() || restweb.isEmpty() || resthours.isEmpty()){
                    Toast.makeText(RestActivity.this,"rentrez toutes les informations",Toast.LENGTH_SHORT).show();
                    return;
                }
                btnaddrestau(restname , restdesc , restloc , restphone , restweb , resthours);

            }
        });
    }

    private void startBackActivity() {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    private void btnaddrestau(String name, String description, String localization,String phone, String website, String hours) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RestaurantApi restaurantApi= (RestaurantApi) retrofit.create((RestaurantApi.class));


        Restaurant restaurant = new Restaurant(name,description,localization, phone ,website,hours);
        Call<String> call = restaurantApi.postRestaurant(restaurant);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(RestActivity.this, "Restaurant added to API", Toast.LENGTH_SHORT).show();
                et_restname.setText("");
                et_restdescription.setText("");
                et_restlocalization.setText("");
                et_restphonenumber.setText("");
                et_restwebsite.setText("");
                et_resthours.setText("");
                try {
                    String errorCatch = "Error code " + response.code() + " " + response.errorBody().string();
                    String responseString = "Response Code : " + errorCatch;
                    responseApi.setText(responseString);

                } catch (Exception e) {
                    String responseString = "Response Code : " + response.code();
                    responseApi.setText(responseString);
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                responseApi.setText("Error found is : " + t.getMessage());
            }
        });
    }
}