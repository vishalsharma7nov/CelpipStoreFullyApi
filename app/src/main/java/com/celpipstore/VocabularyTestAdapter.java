package com.celpipstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class VocabularyTestAdapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] total_questions;
    public static String[] active;
    public static String[] coin_cost;


    public VocabularyTestAdapter(Context c, String[] id, String[] name, String[] total_questions, String[] active,String[] coin_cost)
    {
        this.c=c;
        this.id         = id;
        this.name = name;
        this.total_questions= total_questions;
        this.active= active;
        this.coin_cost  = coin_cost;

    }


    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.test_adapter_vocabulary,null);


        TextView t1=(TextView)convertView.findViewById(R.id.name);
        TextView t2=(TextView)convertView.findViewById(R.id.total_questions);
        TextView t3=(TextView)convertView.findViewById(R.id.coin_cost);
        TextView t4=(TextView)convertView.findViewById(R.id.textViewId);

        Button b1 = (Button)convertView.findViewById(R.id.buttonSelectedTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c,VocabularyTestQuestionsActivity.class);
                intent.putExtra("t1", id[position]);
                c.startActivity(intent);
            }
        });


        for (int i = 0;i<coin_cost.length;i++)
        {
            t1.setText(name[position]);
            t2.setText(total_questions[position]);
            t3.setText(coin_cost[position]);
            t4.setText(id[position]);

        }


        return convertView;
    }
}