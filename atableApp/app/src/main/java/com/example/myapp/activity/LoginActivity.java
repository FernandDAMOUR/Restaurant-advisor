package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.UserAPI;
import com.example.myapp.model.LoginRequest;
import com.example.myapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Button btnRegister;
    Button btnLogin;
    String username;
    String password;
    private TextView responseTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        responseTV = findViewById(R.id.tvResponse);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkLogin();
                authdata(username, password);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void authdata(String username, String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI retrofitAPI = retrofit.create(UserAPI.class);

        // envoie des champs vers notre model LoginRequest.
        LoginRequest modal = new LoginRequest();
        modal.setUsername(username);
        modal.setPassword(password);

        Call<User> call = retrofitAPI.createAuth( modal );
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                // on below line we are setting empty text
                // to our both edit text.
                etUsername.setText("");
                etPassword.setText("");
                try {
                    String errorCatch = "Error code " + response.code() + " " + response.errorBody().string();
                    String responseString = "Response Code : " + errorCatch;


                } catch (Exception e) {
                    String responseString = "Response Code : " + response.code();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                String responseString = "Error found is : " + t.getMessage();
            }
        });

    }

    private void checkLogin(){
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        if(username.isEmpty()|| password.isEmpty()){
            alertFail("Il faut remplir tous les champs");

        }
       else{
            sendLogin();
       }
    }

    private void sendLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "SEND", Toast.LENGTH_SHORT).show();
    }

    private void alertFail(String s){
        new AlertDialog.Builder(this )
                .setTitle("Demande refusé")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();


    }


}