package com.example.myapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;

import java.util.List;

public class MyListViewMenuAdapter extends BaseAdapter {
    private Context context;
    private List<Menu> menuList;


    public MyListViewMenuAdapter(Context context,List menuList){

        this.context = context;
        this.menuList = menuList;

    }

    @Override
    public int getCount(){
        return menuList.size();
    }

    @Override
    public Object getItem(int position){
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.menu_row,null);

        }
        Menu menu = menuList.get(position);

        TextView textViewNameMenu = (TextView) convertView.findViewById(R.id.tvNamemenu);
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.tvPrice);
        TextView textViewMenuDesc = (TextView) convertView.findViewById(R.id.tvDescMenu);



        textViewNameMenu.setText("Nom : "+menu.getName());
        textViewPrice.setText("Prix : "+menu.getPrice()+"€");
        textViewMenuDesc.setText("Description : "+menu.getDescription());


        return convertView;
    }
}
