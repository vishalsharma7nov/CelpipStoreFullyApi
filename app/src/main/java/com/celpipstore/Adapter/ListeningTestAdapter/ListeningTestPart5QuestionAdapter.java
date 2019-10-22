package com.celpipstore.Adapter.ListeningTestAdapter;

import android.content.Context;
import android.content.Intent;
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
import com.celpipstore.CelpipTests.ListeningTest.LISTENING_part2;
import com.celpipstore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListeningTestPart5QuestionAdapter extends BaseAdapter{

    Context c;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;
    private ArrayAdapter<String> adapter5;
    private ArrayAdapter<String> adapter6;
    private ArrayAdapter<String> adapter7;
    private ArrayAdapter<String> adapter8;
    private String userAnswerQuestion1;
    private String userAnswerQuestion2;
    private String userAnswerQuestion3;
    private String userAnswerQuestion4;
    private String userAnswerQuestion5;
    private String userAnswerQuestion6;
    private String userAnswerQuestion7;
    private String userAnswerQuestion8;
    private Spinner optionsquestion1;
    private Spinner optionsquestion2;
    private Spinner optionsquestion3;
    private Spinner optionsquestion4;
    private Spinner optionsquestion5;
    private Spinner optionsquestion6;
    private Spinner optionsquestion7;
    private Spinner optionsquestion8;
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

    public ListeningTestPart5QuestionAdapter(Context c, String id, String test_code, String conversation_1_video, String q1_question, String q2_question, String q3_question, String q4_question, String q5_question, String q6_question, String q7_question, String q8_question, String q1_option1, String q1_option2, String q1_option3, String q1_option4, String q2_option1, String q2_option2, String q2_option3, String q2_option4, String q3_option1, String q3_option2, String q3_option3, String q3_option4, String q4_option1, String q4_option2, String q4_option3, String q4_option4, String q5_option1, String q5_option2, String q5_option3, String q5_option4,String q6_option1,String q6_option2,String q6_option3,String q6_option4,String q7_option1,String q7_option2,String q7_option3,String q7_option4,String q8_option1,String q8_option2,String q8_option3,String q8_option4)
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
        optionsquestion1   = convertView.findViewById(R.id.optionsquestion1);
        optionsquestion2   = convertView.findViewById(R.id.optionsquestion2);
        optionsquestion3   = convertView.findViewById(R.id.optionsquestion3);
        optionsquestion4   = convertView.findViewById(R.id.optionsquestion4);
        optionsquestion5   = convertView.findViewById(R.id.optionsquestion5);
        optionsquestion6   = convertView.findViewById(R.id.optionsquestion6);
        optionsquestion7   = convertView.findViewById(R.id.optionsquestion7);
        optionsquestion8   = convertView.findViewById(R.id.optionsquestion8);
        question1.setText("1."+q1_question);
        question2.setText("2."+q2_question);
        question3.setText("3."+q3_question);
        question4.setText("4."+q4_question);
        question5.setText("5."+q5_question);
        question6.setText("6."+q6_question);
        question7.setText("7."+q7_question);
        question8.setText("8."+q8_question);
        Button buttonSubmit = convertView.findViewById(R.id.buttonSubmitTest);
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
        adapter1 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question1_option);
        optionsquestion1.setAdapter(adapter1);
        optionsquestion1.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter2 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question2_option);
        optionsquestion2.setAdapter(adapter2);
        optionsquestion2.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter3 = new ArrayAdapter<>(c, R.layout.spinner_item,R.id.customSpinnerItemTextView, question3_option);
        optionsquestion3.setAdapter(adapter3);
        optionsquestion3.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter4 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question4_option);
        optionsquestion4.setAdapter(adapter4);
        optionsquestion4.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter5 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question5_option);
        optionsquestion5.setAdapter(adapter5);
        optionsquestion5.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter6 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question6_option);
        optionsquestion6.setAdapter(adapter6);
        optionsquestion6.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter7 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question7_option);
        optionsquestion7.setAdapter(adapter7);
        optionsquestion7.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        adapter8 = new ArrayAdapter<>(c, R.layout.spinner_item,R.id.customSpinnerItemTextView, question8_option);
        optionsquestion8.setAdapter(adapter8);
        optionsquestion8.setOnItemSelectedListener(new ListeningTestPart5QuestionAdapter.MySpinner());
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/lsPart5Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&q7_response="+userAnswerQuestion7+"&q8_response="+userAnswerQuestion8+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, "Answer Submitted", Toast.LENGTH_SHORT).show();
                                Log.e("===Answer Submitting",urlForSubmittingOptions);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("===ErrorAnswerSubmit",urlForSubmittingOptions);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("q1_response", userAnswerQuestion1);
                        params.put("q2_response", userAnswerQuestion2);
                        params.put("q3_response", userAnswerQuestion3);
                        params.put("q4_response", userAnswerQuestion4);
                        params.put("q5_response", userAnswerQuestion5);
                        params.put("q6_response", userAnswerQuestion6);
                        params.put("q7_response", userAnswerQuestion7);
                        params.put("q8_response", userAnswerQuestion8);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c.getApplicationContext());
                requestQueue.add(stringRequest);
                Intent intent = new Intent(c , LISTENING_part2.class);
                c.startActivity(intent);
            }
        });
        return convertView;
    }
    private class MySpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapterView.getId() == R.id.optionsquestion1)
            {
                if (i == 1 )
                {
                    userAnswerQuestion1 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion1 = "option2";
                }
                if (i == 3 )
                {
                    userAnswerQuestion1 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion1 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion2)
            {
                if (i == 1 )
                {
                    userAnswerQuestion2 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion2 = "option2";
                }
                if (i == 3 )
                {
                    userAnswerQuestion2 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion2 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion3)
            {
                if (i == 1 )
                {
                    userAnswerQuestion3 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion3 = "option2";
                }
                if (i == 3 )
                {
                    userAnswerQuestion3 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion3 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion4)
            {
                if (i == 1 )
                {
                    userAnswerQuestion4 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion4 = "option2";
                }
                if (i == 3 )
                {
                    userAnswerQuestion4 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion4 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion5)
            {
                if (i == 1 )
                {
                    userAnswerQuestion5 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion5 = "option2";
                }
                if (i == 3)
                {
                    userAnswerQuestion5 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion5 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion6)
            {
                if (i == 1 )
                {
                    userAnswerQuestion6 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion6 = "option2";
                }
                if (i == 3)
                {
                    userAnswerQuestion6 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion6 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion7)
            {
                if (i == 1 )
                {
                    userAnswerQuestion7 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion7 = "option2";
                }
                if (i == 3)
                {
                    userAnswerQuestion7 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion7 = "option4";
                }
            }
            if (adapterView.getId() == R.id.optionsquestion8)
            {
                if (i == 1 )
                {
                    userAnswerQuestion8 = "option1";
                }
                if (i == 2)
                {
                    userAnswerQuestion8 = "option2";
                }
                if (i == 3)
                {
                    userAnswerQuestion8 = "option3";
                }
                if (i == 4)
                {
                    userAnswerQuestion8 = "option4";
                }
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(c, "PLEASE SELECT THE OPTIONS!!", Toast.LENGTH_SHORT).show();
        }
    }
}