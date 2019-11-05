package com.celpipstore.Adapter.SpeakingTestAdapter.Question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.celpipstore.GetterAndSetterClasses.SpeakingTest.Question.PracticeTestSpeaking;
import com.celpipstore.R;

import java.util.List;


public class SpeakingTestPart1Adapter extends BaseAdapter{
    private Context c;
    private List<PracticeTestSpeaking> practiceTestSpeaking;

    public SpeakingTestPart1Adapter(Context c, List<PracticeTestSpeaking> practiceTestSpeaking) {
        this.c = c;
        this.practiceTestSpeaking = practiceTestSpeaking;
    }

    @Override
    public int getCount() {
        return practiceTestSpeaking.size();
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
        convertView=in.inflate(R.layout.speakingpracticetest_question_and_answer_layout,null);
        TextView question1 = convertView.findViewById(R.id.question1);
        question1.setText(practiceTestSpeaking.get(position).getQ1_question());
        return convertView;
    }
}