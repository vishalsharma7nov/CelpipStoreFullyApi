package com.celpipstore.Adapter.SpeakingTestAdapter.Question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.celpipstore.GetterAndSetterClasses.SpeakingTest.Question.SpeakingTestPart3;
import com.celpipstore.R;

import java.util.List;


public class SpeakingTestPart8Adapter extends BaseAdapter{
    private Context c;
    private List<SpeakingTestPart3> speakingTestPart3;

    public SpeakingTestPart8Adapter(Context c, List<SpeakingTestPart3> practiceTestSpeaking) {
        this.c = c;
        this.speakingTestPart3 = practiceTestSpeaking;
    }

    @Override
    public int getCount() {
        return speakingTestPart3.size();
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
        convertView=in.inflate(R.layout.speakingtestpart3_question_and_answer_layout,null);
        TextView question1 = convertView.findViewById(R.id.question1);
        ImageView question1_image = convertView.findViewById(R.id.question1_image);
        question1.setText(speakingTestPart3.get(position).getQ1_question());
        Glide .with(c)
              .load("http://demo.celpip.biz/uploads/speaking_part8/"+speakingTestPart3.get(position).getQ1_image())
              .into(question1_image);
        return convertView;
    }
}