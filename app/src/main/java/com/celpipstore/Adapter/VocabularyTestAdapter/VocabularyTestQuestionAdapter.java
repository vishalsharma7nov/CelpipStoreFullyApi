package com.celpipstore.Adapter.VocabularyTestAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.celpipstore.R;


public class VocabularyTestQuestionAdapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] level_id;
    public static String[] title;
    public static String[] question_image;
    public static String[] options1;
    public static String[] options2;
    public static String[] options3;
    public static String[] options4;


    public VocabularyTestQuestionAdapter(Context c, String[] id, String[] level_id, String[] title, String[] question_image, String[] options1, String[] options2, String[] options3, String[] options4)
    {
        this.c=c;
        VocabularyTestQuestionAdapter.id = id;
        VocabularyTestQuestionAdapter.level_id = level_id;
        VocabularyTestQuestionAdapter.title = title;
        VocabularyTestQuestionAdapter.question_image = question_image;
        VocabularyTestQuestionAdapter.options1 = options1;
        VocabularyTestQuestionAdapter.options2 = options2;
        VocabularyTestQuestionAdapter.options3 = options3;
        VocabularyTestQuestionAdapter.options4 = options4;

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
        convertView=in.inflate(R.layout.test_question_list_adapter,null);
        TextView t1=convertView.findViewById(R.id.title);
        TextView t2=convertView.findViewById(R.id.option1);
        TextView t3=convertView.findViewById(R.id.option2);
        TextView t4=convertView.findViewById(R.id.option3);
        TextView t5=convertView.findViewById(R.id.option4);
        TextView t6=convertView.findViewById(R.id.textViewId);
        Button   b1=convertView.findViewById(R.id.buttonSelectedTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        t1.setText(title[position]);
        t2.setText(options1[position]);
        t3.setText(options2[position]);
        t4.setText(options3[position]);
        t5.setText(options4[position]);
        t6.setText(id[position]);
        return convertView;
    }
}