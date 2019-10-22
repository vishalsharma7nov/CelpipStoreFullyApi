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


public class ListeningTestPart4QuestionAdapter extends BaseAdapter{

    Context c;
    String userAnswerQuestion1;
    String userAnswerQuestion2;
    String userAnswerQuestion3;
    String userAnswerQuestion4;
    String userAnswerQuestion5;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    ArrayAdapter<String> adapter5;
    ArrayAdapter<String> adapter6;
    Spinner optionsquestion1;
    Spinner optionsquestion2;
    Spinner optionsquestion3;
    Spinner optionsquestion4;
    Spinner optionsquestion5;
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
        optionsquestion1   = convertView.findViewById(R.id.optionsquestion1);
        optionsquestion2   = convertView.findViewById(R.id.optionsquestion2);
        optionsquestion3   = convertView.findViewById(R.id.optionsquestion3);
        optionsquestion4   = convertView.findViewById(R.id.optionsquestion4);
        optionsquestion5   = convertView.findViewById(R.id.optionsquestion5);
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
        adapter1 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question1_option);
        optionsquestion1.setAdapter(adapter1);
        optionsquestion1.setOnItemSelectedListener(new ListeningTestPart4QuestionAdapter.MySpinner());
        adapter2 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question2_option);
        optionsquestion2.setAdapter(adapter2);
        optionsquestion2.setOnItemSelectedListener(new ListeningTestPart4QuestionAdapter.MySpinner());
        adapter3 = new ArrayAdapter<>(c, R.layout.spinner_item,R.id.customSpinnerItemTextView, question3_option);
        optionsquestion3.setAdapter(adapter3);
        optionsquestion3.setOnItemSelectedListener(new ListeningTestPart4QuestionAdapter.MySpinner());
        adapter4 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question4_option);
        optionsquestion4.setAdapter(adapter4);
        optionsquestion4.setOnItemSelectedListener(new ListeningTestPart4QuestionAdapter.MySpinner());
        adapter5 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question5_option);
        optionsquestion5.setAdapter(adapter5);
        optionsquestion5.setOnItemSelectedListener(new ListeningTestPart4QuestionAdapter.MySpinner());
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/lsPart4Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&memberid="+member_id;
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
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(c, "PLEASE SELECT THE OPTIONS!!", Toast.LENGTH_SHORT).show();
        }
    }
}