package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.myapp.R;
import com.example.myapp.UserAPI;
import com.example.myapp.model.User;

public class RegisterUserActivity extends AppCompatActivity {

    // creating variables for our edittext,
    // button, textview and progressbar.
    private EditText etUsername;
    private EditText etName;
    private EditText etLastname;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etAge;
    private Button postDataBtn;
    private Button btnguest;
    private TextView responseTV;
    String email;
    String password;
    String username;
    String name;
    String lastname;
    String age;
    private final String TAG = "RegisterUserActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // initializing our views
        etUsername = findViewById(R.id.etUsername);
        etName = findViewById(R.id.etName);
        etLastname = findViewById(R.id.etLastname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAge = findViewById(R.id.etAge);
        postDataBtn = findViewById(R.id.btnRegister);
        responseTV = findViewById(R.id.tvResponse);
        btnguest = findViewById(R.id.buttonguest);

        btnguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // adding on click listener to our button.
        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username= etUsername.getText().toString();
                name = etName.getText().toString();
                lastname = etLastname.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                age = etAge.getText().toString();
                // validating if the text field is empty or not.
                if (username.isEmpty()||name.isEmpty()|| lastname.isEmpty() || email.isEmpty() || password.isEmpty() || age.isEmpty()) {

                    alertFail("Il faut remplir tous les champs");
                } else {

                    // calling a method to post the data and passing .
                    postData(username, name, lastname, email, password, age);

                    Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    private void postData(String username, String name, String lastname, String email, String password, String age) {

        // creation d'un client retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAPI retrofitAPI = retrofit.create(UserAPI.class);

        // envoie des champs vers notre model User.
        User modal = new User(username, name, lastname, email, password, age);

        // Appel de la methode post dans
        Call<User> call = retrofitAPI.createPost( modal );

        checkRegister();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // this method is called when we get response from our api.
                Toast.makeText(RegisterUserActivity.this, "Data added to API,201", Toast.LENGTH_SHORT).show();

                // on below line we are setting empty text
                // to our both edit text.
                etUsername.setText("");
                etName.setText("");
                etLastname.setText("");
                etEmail.setText("");
                etPassword.setText("");
                etAge.setText("");

                try {
                    String errorCatch = "Error code " + response.code() + " " + response.errorBody().string();
                    String responseString = "Response Code : " + errorCatch;
                    responseTV.setText(responseString);

                } catch (Exception e) {
                    String responseString = "Response Code : " + response.code();
                    responseTV.setText(responseString);
                }



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }



    private void checkRegister() {
        username = etUsername.getText().toString();
        name = etName.getText().toString();
        lastname = etLastname.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        age = etAge.getText().toString();
        if (username.isEmpty() || name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alertFail("You need to fill all the field.");
        } else {
            sendRegister();
        }
    }

    private  void sendRegister(){
        alertSuccess("Register is successful");
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

    private void alertSuccess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage(s)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(RegisterUserActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();

    }

}