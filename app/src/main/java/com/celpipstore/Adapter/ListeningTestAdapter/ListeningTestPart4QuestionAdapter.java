package com.celpipstore.Adapter.ListeningTestAdapter;

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
        ListeningTestPart4QuestionAdapter.id = id;
        ListeningTestPart4QuestionAdapter.test_code = test_code;
        ListeningTestPart4QuestionAdapter.converstaion_1_audio = converstaion_1_audio;

        ListeningTestPart4QuestionAdapter.q1_question = q1_question;
        ListeningTestPart4QuestionAdapter.q2_question = q2_question;
        ListeningTestPart4QuestionAdapter.q3_question = q3_question;
        ListeningTestPart4QuestionAdapter.q4_question = q4_question;
        ListeningTestPart4QuestionAdapter.q5_question = q5_question;

        ListeningTestPart4QuestionAdapter.q1_option1 = q1_option1;
        ListeningTestPart4QuestionAdapter.q1_option2 = q1_option2;
        ListeningTestPart4QuestionAdapter.q1_option3 = q1_option3;
        ListeningTestPart4QuestionAdapter.q1_option4 = q1_option4;

        ListeningTestPart4QuestionAdapter.q2_option1 = q2_option1;
        ListeningTestPart4QuestionAdapter.q2_option2 = q2_option2;
        ListeningTestPart4QuestionAdapter.q2_option3 = q2_option3;
        ListeningTestPart4QuestionAdapter.q2_option4 = q2_option4;

        ListeningTestPart4QuestionAdapter.q3_option1 = q3_option1;
        ListeningTestPart4QuestionAdapter.q3_option2 = q3_option2;
        ListeningTestPart4QuestionAdapter.q3_option3 = q3_option3;
        ListeningTestPart4QuestionAdapter.q3_option4 = q3_option4;

        ListeningTestPart4QuestionAdapter.q4_option1 = q4_option1;
        ListeningTestPart4QuestionAdapter.q4_option2 = q4_option2;
        ListeningTestPart4QuestionAdapter.q4_option3 = q4_option3;
        ListeningTestPart4QuestionAdapter.q4_option4 = q4_option4;

        ListeningTestPart4QuestionAdapter.q5_option1 = q5_option1;
        ListeningTestPart4QuestionAdapter.q5_option2 = q5_option2;
        ListeningTestPart4QuestionAdapter.q5_option3 = q5_option3;
        ListeningTestPart4QuestionAdapter.q5_option4 = q5_option4;

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
        final TextView  answerquestion1 = convertView.findViewById(R.id.answer1);
        final TextView  answerquestion2 = convertView.findViewById(R.id.answer2);
        final TextView  answerquestion3 = convertView.findViewById(R.id.answer3);
        final TextView  answerquestion4 = convertView.findViewById(R.id.answer4);
        final TextView  answerquestion5 = convertView.findViewById(R.id.answer5);

        final Spinner   optionsquestion1   = convertView.findViewById(R.id.optionsquestion1);
        Spinner   optionsquestion2   = convertView.findViewById(R.id.optionsquestion2);
        Spinner   optionsquestion3   = convertView.findViewById(R.id.optionsquestion3);
        Spinner   optionsquestion4   = convertView.findViewById(R.id.optionsquestion4);
        Spinner   optionsquestion5   = convertView.findViewById(R.id.optionsquestion5);

        question1.setText("1."+q1_question);
        question2.setText("2."+q2_question);
        question3.setText("3."+q3_question);
        question4.setText("4."+q4_question);
        question5.setText("5."+q5_question);

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