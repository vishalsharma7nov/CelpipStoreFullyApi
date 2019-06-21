package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ListeningTestPart4QuestionAdapter extends BaseAdapter{

    Context c;

    String userAnswerQuestion1;
    String userAnswerQuestion2;
    String userAnswerQuestion3;
    String userAnswerQuestion4;
    String userAnswerQuestion5;

    //json data
    public static String id;
    public static String test_code;
    public static String converstaion_1_audio;

    public static String q1_question;
    public static String q2_question;
    public static String q3_question;
    public static String q4_question;
    public static String q5_question;

    public static String q1_option1;
    public static String q1_option2;
    public static String q1_option3;
    public static String q1_option4;
    public static String q1_answer;

    public static String q2_option1;
    public static String q2_option2;
    public static String q2_option3;
    public static String q2_option4;
    public static String q2_answer;

    public static String q3_option1;
    public static String q3_option2;
    public static String q3_option3;
    public static String q3_option4;
    public static String q3_answer;

    public static String q4_option1;
    public static String q4_option2;
    public static String q4_option3;
    public static String q4_option4;
    public static String q4_answer;

    public static String q5_option1;
    public static String q5_option2;
    public static String q5_option3;
    public static String q5_option4;
    public static String q5_answer;

    public ListeningTestPart4QuestionAdapter(Context c, String id, String test_code, String converstaion_1_audio,String q1_question,String q2_question,String q3_question,String q4_question,String q5_question, String q1_option1, String q1_option2, String q1_option3, String q1_option4, String q2_option1, String q2_option2, String q2_option3, String q2_option4,  String q3_option1, String q3_option2, String q3_option3, String q3_option4, String q4_option1, String q4_option2, String q4_option3, String q4_option4, String q5_option1, String q5_option2, String q5_option3, String q5_option4)
    {
        this.c=c;
        this.id         = id;
        this.test_code  = test_code;
        this.converstaion_1_audio = converstaion_1_audio;

        this.q1_question = q1_question;
        this.q2_question = q2_question;
        this.q3_question = q3_question;
        this.q4_question = q4_question;
        this.q5_question = q5_question;

        this.q1_option1 = q1_option1;
        this.q1_option2 = q1_option2;
        this.q1_option3 = q1_option3;
        this.q1_option4 = q1_option4;

        this.q2_option1 = q2_option1;
        this.q2_option2 = q2_option2;
        this.q2_option3 = q2_option3;
        this.q2_option4 = q2_option4;

        this.q3_option1 = q3_option1;
        this.q3_option2 = q3_option2;
        this.q3_option3 = q3_option3;
        this.q3_option4 = q3_option4;

        this.q4_option1 = q4_option1;
        this.q4_option2 = q4_option2;
        this.q4_option3 = q4_option3;
        this.q4_option4 = q4_option4;

        this.q5_option1 = q5_option1;
        this.q5_option2 = q5_option2;
        this.q5_option3 = q5_option3;
        this.q5_option4 = q5_option4;

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

        convertView=in.inflate(R.layout.test_adapter_listeningtestpart4,null);

        TextView  question1 = convertView.findViewById(R.id.question1);
        TextView  question2 = convertView.findViewById(R.id.question2);
        TextView  question3 = convertView.findViewById(R.id.question3);
        TextView  question4 = convertView.findViewById(R.id.question4);
        TextView  question5 = convertView.findViewById(R.id.question5);

        Spinner   optionsquestion1   = convertView.findViewById(R.id.optionsquestion1);
        Spinner   optionsquestion2   = convertView.findViewById(R.id.optionsquestion2);
        Spinner   optionsquestion3   = convertView.findViewById(R.id.optionsquestion3);
        Spinner   optionsquestion4   = convertView.findViewById(R.id.optionsquestion4);
        Spinner   optionsquestion5   = convertView.findViewById(R.id.optionsquestion5);

        question1.setText("1."+q1_question);
        question2.setText("2."+q2_question);
        question3.setText("3."+q3_question);
        question4.setText("4."+q4_question);
        question5.setText("5."+q5_question);


        final String question_option1[] = {q1_option1,q2_option1,q3_option3,q4_option1,q5_option1};
        final String question_option2[] = {q1_option2,q2_option2,q3_option2,q4_option2,q5_option2};
        final String question_option3[] = {q1_option3,q2_option3,q3_option3,q4_option3,q5_option3};
        final String question_option4[] = {q1_option4,q2_option4,q3_option4,q4_option4,q5_option4};

        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");





        return convertView;
    }
}