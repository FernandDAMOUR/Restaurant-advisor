package com.example.myapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;

import java.util.List;

public class MyListViewOpinionAdapter extends BaseAdapter {
    private final Context context;
    private final List<Opinion> opinionList;


    public MyListViewOpinionAdapter(Context context,List opinionList){

        this.context = context;
        this.opinionList = opinionList;

    }

    @Override
    public int getCount(){
        return opinionList.size();
    }

    @Override
    public Object getItem(int position){
        return opinionList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.opinion_row,null);

        }
        Opinion opinion = opinionList.get(position);

        TextView textViewGradeOpinion = (TextView) convertView.findViewById(R.id.tvGradeOpinion);
        TextView textViewDescOpinion = (TextView) convertView.findViewById(R.id.tvDescOpinion);



        textViewGradeOpinion.setText("note : "+ opinion.getGrade().toString());
        textViewDescOpinion.setText("avis : "+opinion.getDescription());

        return convertView;
    }
}
