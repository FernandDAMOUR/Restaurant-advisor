package com.example.myapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.model.Restaurant;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Restaurant> restaurantList;


    public MyListViewAdapter(Context context,List restaurantList){

        this.context = context;
        this.restaurantList = restaurantList;

    }

    @Override
    public int getCount(){
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position){
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.restaurant_row,null);

        }
        Restaurant restaurant= restaurantList.get(position);



        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView3);
        TextView textViewRestaurantName = (TextView) convertView.findViewById(R.id.restaurant_name);
        TextView textViewRestaurantId = (TextView) convertView.findViewById(R.id.restaurant_id);
        TextView textViewRestaurantDesc = (TextView) convertView.findViewById(R.id.restaurant_desc);
        TextView textViewRestaurantGrade = (TextView) convertView.findViewById(R.id.restaurant_grade);
        TextView textViewRestaurantLocalization = (TextView) convertView.findViewById(R.id.restaurant_localization);
        TextView textViewRestaurantHours = (TextView) convertView.findViewById(R.id.restaurant_hours);
        TextView textViewRestaurantPhone = (TextView) convertView.findViewById(R.id.restaurant_phone);


        Glide.with(context)
                .load("https://media-cdn.tripadvisor.com/media/photo-s/0e/ea/a5/da/salon-interior.jpg")
                .into(imageView);

        imageView.findViewById(R.id.imageView3);
        textViewRestaurantName.setText(restaurant.getName());
        textViewRestaurantId.setText(restaurant.getId());
        textViewRestaurantDesc.setText(restaurant.getDescription());
        textViewRestaurantGrade.setText(restaurant.getGrade());
        textViewRestaurantLocalization.setText(restaurant.getLocalization());
        textViewRestaurantHours.setText(restaurant.getHours());
        textViewRestaurantPhone.setText(restaurant.getPhone());

        return convertView;
    }
}
