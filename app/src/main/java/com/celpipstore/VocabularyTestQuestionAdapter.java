package com.celpipstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class VocabularyTestQuestionAdapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] level_id;
    public static String[] title;
    public static String[] question_image;
    public static String[] options;


    public VocabularyTestQuestionAdapter(Context c, String[] id, String[] level_id, String[] title, String[] question_image, String[] options)
    {
        this.c=c;
        this.id             = id;
        this.level_id       = level_id;
        this.title          = title;
        this.question_image = question_image;
        this.options        = options;

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


        TextView t1=(TextView)convertView.findViewById(R.id.title);
        TextView t2=(TextView)convertView.findViewById(R.id.option1);
        TextView t3=(TextView)convertView.findViewById(R.id.option2);
        TextView t4=(TextView)convertView.findViewById(R.id.option3);
        TextView t5=(TextView)convertView.findViewById(R.id.option4);

        TextView t6=(TextView)convertView.findViewById(R.id.textViewId);

        Button b1 = (Button)convertView.findViewById(R.id.buttonSelectedTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(c,VocabularyTestQuestionsActivity.class);
//                intent.putExtra("t1", id[position]);
//                c.startActivity(intent);
            }
        });


        for (int i = 0;i<id.length;i++)
        {
            t1.setText(title[position]);
            t2.setText(options[position]);
            t3.setText(options[position]);
            t4.setText(options[position]);
            t5.setText(options[position]);
            t6.setText(id[position]);


        }


        return convertView;
    }
}