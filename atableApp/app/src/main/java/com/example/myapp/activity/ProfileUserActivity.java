package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.R;

public class ProfileUserActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvUsername;
    TextView tvLastname;
    TextView tvEmail;
    TextView tvAge;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        getUser();
        tvUsername = findViewById(R.id.tvUsername);
        tvName = findViewById(R.id.tvName);
        tvLastname = findViewById(R.id.tvLastame);
        tvEmail = findViewById(R.id.tvEmail);
        tvAge = findViewById(R.id.tvAge);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(ProfileUserActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private void getUser() {
        tvUsername.setText("Username : ");
        tvName.setText("Name : ");
        tvLastname.setText("Firstname: ");
        tvEmail.setText("Email : ");
        tvAge.setText("Age : ");
    }
}