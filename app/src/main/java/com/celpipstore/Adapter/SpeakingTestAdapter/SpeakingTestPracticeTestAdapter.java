package com.celpipstore.Adapter.SpeakingTestAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.GetterAndSetterClasses.SpeakingTest.PracticeTestSpeaking;
import com.celpipstore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SpeakingTestPracticeTestAdapter extends BaseAdapter{
    private Context c;
    private List<PracticeTestSpeaking> practiceTestSpeaking;

    public SpeakingTestPracticeTestAdapter(Context c, List<PracticeTestSpeaking> practiceTestSpeaking) {
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