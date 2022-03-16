package com.example.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.MenuAPI;
import com.example.myapp.OpinionAPI;
import com.example.myapp.R;
import com.example.myapp.model.Opinion;
import com.example.myapp.model.MyListViewOpinionAdapter;
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

public class OpinionActivity extends AppCompatActivity {

    private final String TAG = "OpinionActivity";

    //private static OpinionAPI opinionApi;
    //private Retrofit retrofit;
    private MyListViewOpinionAdapter myListViewOpinionAdapter;
    private List<Opinion> opinions;
    private ListView listViewOpinion;
    private TextView tvOpinionRest;
    private Button buttonBack;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinions);

        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");
        opinions = new ArrayList<>();
        listViewOpinion = findViewById(R.id.listViewOpinion);
        tvOpinionRest = findViewById(R.id.tvOpinionRest);
        buttonBack = findViewById(R.id.buttonBack);

        this.myListViewOpinionAdapter = new MyListViewOpinionAdapter(getApplicationContext(), opinions);
        this.listViewOpinion.setAdapter(myListViewOpinionAdapter);

        tvOpinionRest.setText("Les avis sur le restaurant "+restaurant.getName());

        buttonBack.setOnClickListener((v) -> {
            startBackActivity(restaurant);
        });

        //this.configureRetrofitMenu();

        getOpinionsViaAPI(restaurant.getId());
    }


    /**
     * private void configureRetrofitMenu() {
     * <p>
     * Gson gson = new GsonBuilder()
     * .setLenient()
     * .create();
     * <p>
     * <p>
     * retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
     * .addConverterFactory(GsonConverterFactory.create(gson))
     * .build();
     * <p>
     * <p>
     * menuApi = retrofit.create(MenuAPI.class);
     * <p>
     * <p>
     * }
     **/


    private void startBackActivity(Restaurant restaurant) {
        Intent intent = new Intent (this, MainActivity2.class);
        intent.putExtra("restaurant",restaurant);
        startActivity(intent);
    }

    private void getOpinionsViaAPI(String id) {

        Log.d(TAG,id);
        Log.d(TAG,"coucou");
        MainActivity.getOpinionApi().getOpinions(id).enqueue(new Callback<List<Opinion>>() {
            @Override
            public void onResponse(Call<List<Opinion>> call, Response<List<Opinion>> response) {

                Log.d(TAG, "OnResponse:");
                List<Opinion> opinionsList = response.body();
                if (opinionsList != null) {
                    opinions.addAll(opinionsList);
                    myListViewOpinionAdapter.notifyDataSetChanged();

                    for (Opinion opinion : opinionsList) {
                        Log.d(TAG, "opinion recu " + opinion.getId() + " " + opinion.getDescription());
                    }
                }
                else{
                    Log.d(TAG, "youpi");
                }
            }

            @Override
            public void onFailure (Call < List < Opinion >> call, Throwable t){
                Log.d(TAG, String.valueOf(call));
                Log.d(TAG, String.valueOf(t));
            }
        });
    };
}