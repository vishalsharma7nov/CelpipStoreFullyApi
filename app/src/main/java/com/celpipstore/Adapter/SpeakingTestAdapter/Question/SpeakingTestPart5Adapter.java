package com.celpipstore.Adapter.SpeakingTestAdapter.Question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.celpipstore.GetterAndSetterClasses.SpeakingTest.Question.SpeakingTestPart5;
import com.celpipstore.R;

import java.util.List;


public class SpeakingTestPart5Adapter extends BaseAdapter{
    private Context c;
    private List<SpeakingTestPart5> speakingTestPart5;

    public SpeakingTestPart5Adapter(Context c, List<SpeakingTestPart5> speakingTestPart5) {
        this.c = c;
        this.speakingTestPart5 = speakingTestPart5;
    }

    @Override
    public int getCount() {
        return speakingTestPart5.size();
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
        convertView=in.inflate(R.layout.speakingtestpart5_question_and_answer_layout,null);
        TextView question1 = convertView.findViewById(R.id.question1);
        ImageView question1_image = convertView.findViewById(R.id.question1_image);
        ImageView question1_image2 = convertView.findViewById(R.id.question1_image2);
        question1.setText(speakingTestPart5.get(position).getQ1_question());
        Glide .with(c)
              .load("http://demo.celpip.biz/uploads/speaking_part5/"+speakingTestPart5.get(position).getQ1_image())
              .into(question1_image);
        Glide .with(c)
                .load("http://demo.celpip.biz/uploads/speaking_part5/"+speakingTestPart5.get(position).getQ1_image2())
                .into(question1_image2);
        return convertView;
    }
}