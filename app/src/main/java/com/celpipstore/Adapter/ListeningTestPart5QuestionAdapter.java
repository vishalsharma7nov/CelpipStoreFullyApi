package com.celpipstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.celpipstore.Tests.ListeningTest.LISTENING_part2;
import com.celpipstore.R;

import java.util.ArrayList;


public class ListeningTestPart5QuestionAdapter extends BaseAdapter{

    Context c;

    String userAnswerQuestion1;
    String userAnswerQuestion2;
    String userAnswerQuestion3;
    String userAnswerQuestion4;
    String userAnswerQuestion5;

    //json data
    public static String id;
    public static String test_code;
    public static String conversation_1_video;

    public static String q1_question;
    public static String q2_question;
    public static String q3_question;
    public static String q4_question;
    public static String q5_question;
    public static String q6_question;
    public static String q7_question;
    public static String q8_question;

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

    public static String q6_option1;
    public static String q6_option2;
    public static String q6_option3;
    public static String q6_option4;
    public static String q6_answer;

    public static String q7_option1;
    public static String q7_option2;
    public static String q7_option3;
    public static String q7_option4;
    public static String q7_answer;

    public static String q8_option1;
    public static String q8_option2;
    public static String q8_option3;
    public static String q8_option4;
    public static String q8_answer;

    public ListeningTestPart5QuestionAdapter(Context c, String id, String test_code, String conversation_1_video, String q1_question, String q2_question, String q3_question, String q4_question, String q5_question, String q6_question, String q7_question, String q8_question, String q1_option1, String q1_option2, String q1_option3, String q1_option4, String q2_option1, String q2_option2, String q2_option3, String q2_option4, String q3_option1, String q3_option2, String q3_option3, String q3_option4, String q4_option1, String q4_option2, String q4_option3, String q4_option4, String q5_option1, String q5_option2, String q5_option3, String q5_option4
        ,String q6_option1
        ,String q6_option2
        ,String q6_option3
        ,String q6_option4
        ,String q7_option1
        ,String q7_option2
        ,String q7_option3
        ,String q7_option4
        ,String q8_option1
        ,String q8_option2
        ,String q8_option3
        ,String q8_option4)
    {
        this.c=c;
        ListeningTestPart5QuestionAdapter.id = id;
        ListeningTestPart5QuestionAdapter.test_code = test_code;
        ListeningTestPart5QuestionAdapter.conversation_1_video = conversation_1_video;

        ListeningTestPart5QuestionAdapter.q1_question = q1_question;
        ListeningTestPart5QuestionAdapter.q2_question = q2_question;
        ListeningTestPart5QuestionAdapter.q3_question = q3_question;
        ListeningTestPart5QuestionAdapter.q4_question = q4_question;
        ListeningTestPart5QuestionAdapter.q5_question = q5_question;
        ListeningTestPart5QuestionAdapter.q6_question = q6_question;
        ListeningTestPart5QuestionAdapter.q7_question = q7_question;
        ListeningTestPart5QuestionAdapter.q8_question = q8_question;

        ListeningTestPart5QuestionAdapter.q1_option1 = q1_option1;
        ListeningTestPart5QuestionAdapter.q1_option2 = q1_option2;
        ListeningTestPart5QuestionAdapter.q1_option3 = q1_option3;
        ListeningTestPart5QuestionAdapter.q1_option4 = q1_option4;

        ListeningTestPart5QuestionAdapter.q2_option1 = q2_option1;
        ListeningTestPart5QuestionAdapter.q2_option2 = q2_option2;
        ListeningTestPart5QuestionAdapter.q2_option3 = q2_option3;
        ListeningTestPart5QuestionAdapter.q2_option4 = q2_option4;

        ListeningTestPart5QuestionAdapter.q3_option1 = q3_option1;
        ListeningTestPart5QuestionAdapter.q3_option2 = q3_option2;
        ListeningTestPart5QuestionAdapter.q3_option3 = q3_option3;
        ListeningTestPart5QuestionAdapter.q3_option4 = q3_option4;

        ListeningTestPart5QuestionAdapter.q4_option1 = q4_option1;
        ListeningTestPart5QuestionAdapter.q4_option2 = q4_option2;
        ListeningTestPart5QuestionAdapter.q4_option3 = q4_option3;
        ListeningTestPart5QuestionAdapter.q4_option4 = q4_option4;

        ListeningTestPart5QuestionAdapter.q5_option1 = q5_option1;
        ListeningTestPart5QuestionAdapter.q5_option2 = q5_option2;
        ListeningTestPart5QuestionAdapter.q5_option3 = q5_option3;
        ListeningTestPart5QuestionAdapter.q5_option4 = q5_option4;

        ListeningTestPart5QuestionAdapter.q6_option1 = q6_option1;
        ListeningTestPart5QuestionAdapter.q6_option2 = q6_option2;
        ListeningTestPart5QuestionAdapter.q6_option3 = q6_option3;
        ListeningTestPart5QuestionAdapter.q6_option4 = q6_option4;

        ListeningTestPart5QuestionAdapter.q7_option1 = q7_option1;
        ListeningTestPart5QuestionAdapter.q7_option2 = q7_option2;
        ListeningTestPart5QuestionAdapter.q7_option3 = q7_option3;
        ListeningTestPart5QuestionAdapter.q7_option4 = q7_option4;

        ListeningTestPart5QuestionAdapter.q8_option1 = q8_option1;
        ListeningTestPart5QuestionAdapter.q8_option2 = q8_option2;
        ListeningTestPart5QuestionAdapter.q8_option3 = q8_option3;
        ListeningTestPart5QuestionAdapter.q8_option4 = q8_option4;

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

        convertView=in.inflate(R.layout.test_adapter_listeningtestpart5,null);

        TextView  question1 = convertView.findViewById(R.id.question1);
        TextView  question2 = convertView.findViewById(R.id.question2);
        TextView  question3 = convertView.findViewById(R.id.question3);
        TextView  question4 = convertView.findViewById(R.id.question4);
        TextView  question5 = convertView.findViewById(R.id.question5);
        TextView  question6 = convertView.findViewById(R.id.question6);
        TextView  question7 = convertView.findViewById(R.id.question7);
        TextView  question8 = convertView.findViewById(R.id.question8);

        final TextView  answerquestion1 = convertView.findViewById(R.id.answer1);
        final TextView  answerquestion2 = convertView.findViewById(R.id.answer2);
        final TextView  answerquestion3 = convertView.findViewById(R.id.answer3);
        final TextView  answerquestion4 = convertView.findViewById(R.id.answer4);
        final TextView  answerquestion5 = convertView.findViewById(R.id.answer5);

        final Spinner   optionsquestion1   = convertView.findViewById(R.id.optionsquestion1);
        final Spinner   optionsquestion2   = convertView.findViewById(R.id.optionsquestion2);
        final Spinner   optionsquestion3   = convertView.findViewById(R.id.optionsquestion3);
        final Spinner   optionsquestion4   = convertView.findViewById(R.id.optionsquestion4);
        final Spinner   optionsquestion5   = convertView.findViewById(R.id.optionsquestion5);
        final Spinner   optionsquestion6   = convertView.findViewById(R.id.optionsquestion6);
        final Spinner   optionsquestion7   = convertView.findViewById(R.id.optionsquestion7);
        final Spinner   optionsquestion8   = convertView.findViewById(R.id.optionsquestion8);

        question1.setText("1."+q1_question);
        question2.setText("2."+q2_question);
        question3.setText("3."+q3_question);
        question4.setText("4."+q4_question);
        question5.setText("5."+q5_question);
        question6.setText("6."+q6_question);
        question7.setText("7."+q7_question);
        question8.setText("8."+q8_question);

        final Button buttonSubmit = convertView.findViewById(R.id.buttonSubmitTest);

        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");

        ArrayList<String> question1_option = new ArrayList<>();
        question1_option.add("Choose The Answer");
        question1_option.add(q1_option1);
        question1_option.add(q1_option2);
        question1_option.add(q1_option3);
        question1_option.add(q1_option4);

        ArrayList<String> question2_option = new ArrayList<>();
        question2_option.add("Choose The Answer");
        question2_option.add(q2_option1);
        question2_option.add(q2_option2);
        question2_option.add(q2_option3);
        question2_option.add(q2_option4);

        ArrayList<String> question3_option = new ArrayList<>();
        question3_option.add("Choose The Answer");
        question3_option.add(q3_option1);
        question3_option.add(q3_option2);
        question3_option.add(q3_option3);
        question3_option.add(q3_option4);

        ArrayList<String> question4_option = new ArrayList<>();
        question4_option.add("Choose The Answer");
        question4_option.add(q4_option1);
        question4_option.add(q4_option2);
        question4_option.add(q4_option3);
        question4_option.add(q4_option4);

        ArrayList<String> question5_option = new ArrayList<>();
        question5_option.add("Choose The Answer");
        question5_option.add(q5_option1);
        question5_option.add(q5_option2);
        question5_option.add(q5_option3);
        question5_option.add(q5_option4);

        ArrayList<String> question6_option = new ArrayList<>();
        question6_option.add("Choose The Answer");
        question6_option.add(q6_option1);
        question6_option.add(q6_option2);
        question6_option.add(q6_option3);
        question6_option.add(q6_option4);

        ArrayList<String> question7_option = new ArrayList<>();
        question7_option.add("Choose The Answer");
        question7_option.add(q7_option1);
        question7_option.add(q7_option2);
        question7_option.add(q7_option3);
        question7_option.add(q7_option4);

        ArrayList<String> question8_option = new ArrayList<>();
        question8_option.add("Choose The Answer");
        question8_option.add(q8_option1);
        question8_option.add(q8_option2);
        question8_option.add(q8_option3);
        question8_option.add(q8_option4);

        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question1_option);
        adapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question2_option);
        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question3_option);
        adapter3.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question4_option);
        adapter4.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion4.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question5_option);
        adapter5.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion5.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question6_option);
        adapter6.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion6.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question7_option);
        adapter7.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion7.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 =
                new ArrayAdapter<String>(c,  android.R.layout.simple_spinner_dropdown_item, question8_option);
        adapter8.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        optionsquestion8.setAdapter(adapter8);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, LISTENING_part2.class);
                c.startActivity(intent);
            }
        });

        return convertView;
    }
}