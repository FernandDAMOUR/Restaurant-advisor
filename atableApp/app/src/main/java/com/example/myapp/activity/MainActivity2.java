package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.model.Restaurant;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");


        TextView restauName = (TextView) findViewById(R.id.Namerest);

        TextView restauDesc = (TextView) findViewById(R.id.textView3);

        TextView restauGrade = (TextView) findViewById(R.id.restGrade);

        TextView restauLocalization = (TextView) findViewById(R.id.restLocal);

        TextView restauPhone = (TextView) findViewById(R.id.restphone);

        TextView restauHours = (TextView) findViewById(R.id.restHours);


        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load("https://media-cdn.tripadvisor.com/media/photo-s/0e/ea/a5/da/salon-interior.jpg")
                .into(imageView);


        restauName.setText(restaurant.getName());
        restauDesc.setText(restaurant.getDescription());
        restauGrade.setText(restaurant.getGrade());
        restauLocalization.setText(restaurant.getLocalization());
        restauPhone.setText(restaurant.getPhone());
        restauHours.setText(restaurant.getHours());


        Button button = (Button) findViewById(R.id.buttonBack);
        Button buttonMenu = (Button) findViewById(R.id.btnMenu);
        Button buttonOpinions = (Button) findViewById(R.id.btnAvis);


        buttonOpinions.setOnClickListener((v) -> {
            startOpinionActivity(restaurant);
        });

        buttonMenu.setOnClickListener((v) -> {

            startMenuActivity(restaurant);
        });

        button.setOnClickListener((v) -> {
            startBackActivity(restaurant);
        });


    }



    private void startOpinionActivity(Restaurant restaurant) {
        Intent intent = new Intent (this, OpinionActivity.class);
        intent.putExtra("restaurant",restaurant);
        startActivity(intent);
    }

    private void startBackActivity(Restaurant restaurant) {
        Intent intent = new Intent (this, MainActivity.class);
        intent.putExtra("id",restaurant.getId());
        startActivity(intent);
    }

    private void startActivity(){
            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }

    private void startMenuActivity(Restaurant restaurant){
        Intent intent = new Intent (this, MenuActivity.class);
        intent.putExtra("restaurant",restaurant);
        startActivity(intent);
    }
}
