package com.example.myapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.MenuAPI;
import com.example.myapp.R;
import com.example.myapp.model.Menu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMenuActivity extends AppCompatActivity {

    private final String TAG = "AddMenuActivity";
    private EditText et_menuname;
    private EditText et_menudesc;
    private EditText et_menuprice;
    private TextView responseApi;
    private Button btnaddmenu;
    private static MenuAPI menuAPI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
            //getSupportActionBar().setTitle("Add Restaurant");

            et_menuname = findViewById(R.id.etMenuname);
            et_menudesc = findViewById(R.id.etMenudesc);
            et_menuprice = findViewById(R.id.etMenuprice);
            //responseApi = findViewById(R.id.responseApi);
            btnaddmenu = findViewById(R.id.buttonAddMenu);

            btnaddmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (et_menuname.getText().toString().isEmpty() ||
                            et_menudesc.getText().toString().isEmpty() ||
                            et_menuprice.getText().toString().isEmpty() ) {
                        Toast.makeText(AddMenuActivity.this, "Please enter all information", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    btnaddmenu(et_menuname.getText().toString(),
                            et_menudesc.getText().toString(),
                            et_menuprice.getText().toString());

                }
            });
        }
        private void btnaddmenu (String name, String description, String price){

            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            menuAPI = retrofit.create((MenuAPI.class));
            Menu menu = new Menu(name, description, price);
            Call<Menu> call = menuAPI.addMenu(menu);

            call.enqueue(new Callback<Menu>() {
                @Override
                public void onResponse(Call<Menu> call, Response<Menu> response) {
                    Toast.makeText(AddMenuActivity.this, "Menu added to API", Toast.LENGTH_SHORT).show();
                    et_menuname.setText("");
                    et_menudesc.setText("");
                    et_menuprice.setText("");
                    try {
                        Log.d(TAG, "erreur ca ne passe pas");
                        //String errorCatch = "Error code " + response.code() + " " + response.errorBody().string();
                        //String responseString = "Response Code : " + errorCatch;
                        //responseApi.setText(responseString);

                    } catch (Exception e) {
                        Log.e(TAG, "oh hell no ");
                       // String responseString = "Response Code : " + response.code();
                        //responseApi.setText(responseString);
                    }
                }

                @Override
                public void onFailure(Call<Menu> call, Throwable t) {
                    Log.d(TAG, "erreur");
                    //responseApi.setText("Error found is : " + t.getMessage());

                }
            });
        }
    }
