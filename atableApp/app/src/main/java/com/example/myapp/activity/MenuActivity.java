package com.example.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.MenuAPI;
import com.example.myapp.R;
import com.example.myapp.model.Menu;
import com.example.myapp.model.MyListViewMenuAdapter;
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

public class MenuActivity extends AppCompatActivity {

    private final String TAG = "MenuActivity";
    private MyListViewMenuAdapter myListViewMenuAdapter;
    private List<Menu> menus;
    private ListView listViewmenu;
    private Button btnajoutmenu;
    private String id;
    private Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");
        menus = new ArrayList<>();
        listViewmenu = findViewById(R.id.listviewmenu);
        btnajoutmenu = findViewById(R.id.btnajoutmenu);
        buttonBack = findViewById(R.id.buttonBack);

        this.myListViewMenuAdapter = new MyListViewMenuAdapter(getApplicationContext(), menus);
        this.listViewmenu.setAdapter(myListViewMenuAdapter);

        Button button = (Button) findViewById(R.id.btnajoutmenu);


        btnajoutmenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //intent.putExtra("menu", menu);
                startAddmenuActivity();
            }
        });

        buttonBack.setOnClickListener((v) -> {
            startBackActivity(restaurant);
        });

        listViewmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Récupere le restaurant depuis la liste
                Menu menu = menus.get(position);

                //Activité pour modifier le menu en question ou le supprimer
                //startActivity(menu);
            }
        });

        //this.configureRetrofitMenu();

        getMenusViaAPI(restaurant.getId());
    }

    private void startAddmenuActivity(){

        Intent intent = new Intent(this, AddMenuActivity.class);
        //intent.putExtra("String","Bonjour ceci n'est pas un restaurant ");
        startActivity(intent);
    }


    private void startBackActivity(Restaurant restaurant) {
        Intent intent = new Intent (this, MainActivity2.class);
        intent.putExtra("restaurant",restaurant);
        startActivity(intent);
    }

    private void startActivity(Menu menu) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("menu", menu);
        startActivity(intent);
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
    private void getMenusViaAPI(String id) {

        //Log.d(TAG,id);
        MainActivity.getMenuApi().getMenus(id).enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {

                Log.d(TAG, "onResponse:");
                List<Menu> menusList = response.body();
                if (menusList != null) {
                    menus.addAll(menusList);
                    myListViewMenuAdapter.notifyDataSetChanged();

                    for (Menu menu : menusList) {
                        Log.d(TAG, "menu recu " + menu.getId() + " " + menu.getName());
                    }
                }
                else{
                    Log.d(TAG, "null");
                }
            }

                @Override
                public void onFailure (Call < List < Menu >> call, Throwable t){
                }
        });
    }
}