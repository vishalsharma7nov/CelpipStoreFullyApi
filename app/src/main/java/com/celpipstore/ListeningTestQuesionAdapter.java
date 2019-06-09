package com.celpipstore;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class ListeningTestQuesionAdapter extends BaseAdapter{

    Context c;
    public static String id;
    public static String test_code;
    public static String converstaion_1_audio;
    public static String q1_audio;
    public static String q1_option1;
    public static String q1_option2;
    public static String q1_option3;
    public static String q1_option4;
    public static String q1_answer;
    public static String[] q2_audio;
    public static String[] q2_option1;
    public static String[] q2_option2;
    public static String[] q2_option3;
    public static String[] q2_option4;
    public static String[] q2_answer;
    public static String[] q3_audio;
    public static String[] q3_option1;
    public static String[] q3_option2;
    public static String[] q3_option3;
    public static String[] q3_option4;
    public static String[] q3_answer;
    public static String[] q4_audio;
    public static String[] q4_option1;
    public static String[] q4_option2;
    public static String[] q4_option3;
    public static String[] q4_option4;
    public static String[] q4_answer;
    public static String[] q5_audio;
    public static String[] q5_option1;
    public static String[] q5_option2;
    public static String[] q5_option3;
    public static String[] q5_option4;
    public static String[] q5_answer;


    public ListeningTestQuesionAdapter(Context c, String id, String test_code,String converstaion_1_audio,String q1_audio,String q1_option1,String q1_option2,String q1_option3,String q1_option4)
    {
        this.c=c;
        this.id         = id;
        this.test_code  = test_code;
        this.converstaion_1_audio = converstaion_1_audio;
        this.q1_audio = converstaion_1_audio;
        this.q1_option1 = q1_option1;
        this.q1_option2 = q1_option2;
        this.q1_option3 = q1_option3;
        this.q1_option4 = q1_option4;

    }


    @Override
    public int getCount() {
        return 1;
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

        convertView=in.inflate(R.layout.test_adapter_listeningtest,null);


        TextView t1=(TextView)convertView.findViewById(R.id.t1);
        TextView t2=(TextView)convertView.findViewById(R.id.t2);
        TextView t3=(TextView)convertView.findViewById(R.id.t3);
        TextView t4=(TextView)convertView.findViewById(R.id.t4);
        TextView t5=(TextView)convertView.findViewById(R.id.t5);


        for (int i = 0;i<=1;i++)
        {
            t1.setText(q1_option1);
            t2.setText(q1_option2);
            t3.setText(q1_option3);
            t4.setText(q1_option4);


        }
        return convertView;
    }
}