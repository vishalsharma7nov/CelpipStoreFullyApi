package com.celpipstore.Adapter.ListeningTestAdapter.Question;

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
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.celpipstore.GetterAndSetterClasses.ListeningTest.Question.ListeningTestPart6;
import com.celpipstore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListeningTestPart6QuestionAdapter extends BaseAdapter {

    private Context c;
    private List<ListeningTestPart6> listeningTestPart6;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;
    private ArrayAdapter<String> adapter5;
    private ArrayAdapter<String> adapter6;
    private String userAnswerQuestion1;
    private String userAnswerQuestion2;
    private String userAnswerQuestion3;
    private String userAnswerQuestion4;
    private String userAnswerQuestion5;
    private String userAnswerQuestion6;
    private Spinner optionsquestion1;
    private Spinner optionsquestion2;
    private Spinner optionsquestion3;
    private Spinner optionsquestion4;
    private Spinner optionsquestion5;
    private Spinner optionsquestion6;

    public ListeningTestPart6QuestionAdapter(Context c, List<ListeningTestPart6> listeningTestPart6) {
        this.c = c;
        this.listeningTestPart6 = listeningTestPart6;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=in.inflate(R.layout.test_adapter_listeningtestpart6,null);
        final LinearLayout frame1 = convertView.findViewById(R.id.frame1);
        //========================================================================================//
        /************************************Correct Answer****************************************/
        //========================================================================================//
        final ScrollView scrollView = convertView.findViewById(R.id.userAnswer);
        final TextView c1 = convertView.findViewById(R.id.question1_correct);
        final TextView c2 = convertView.findViewById(R.id.question2_correct);
        final TextView c3 = convertView.findViewById(R.id.question3_correct);
        final TextView c4 = convertView.findViewById(R.id.question4_correct);
        final TextView c5 = convertView.findViewById(R.id.question5_correct);
        final TextView c6 = convertView.findViewById(R.id.question6_correct);
        final TextView c7 = convertView.findViewById(R.id.question7_correct);
        final TextView c8 = convertView.findViewById(R.id.question8_correct);
        final TextView u1 = convertView.findViewById(R.id.question1_user);
        final TextView u2 = convertView.findViewById(R.id.question2_user);
        final TextView u3 = convertView.findViewById(R.id.question3_user);
        final TextView u4 = convertView.findViewById(R.id.question4_user);
        final TextView u5 = convertView.findViewById(R.id.question5_user);
        final TextView u6 = convertView.findViewById(R.id.question6_user);
        final TextView u7 = convertView.findViewById(R.id.question7_user);
        final TextView u8 = convertView.findViewById(R.id.question8_user);
        c1.setText("Correct Answer = "+" option"+listeningTestPart6.get(position).getQ1_answer());
        c2.setText("Correct Answer = "+" option"+listeningTestPart6.get(position).getQ2_answer());
        c3.setText("Correct Answer = "+" option"+listeningTestPart6.get(position).getQ3_answer());
        c4.setText("Correct Answer = "+" option"+listeningTestPart6.get(position).getQ4_answer());
        c5.setText("Correct Answer = "+" option"+listeningTestPart6.get(position).getQ5_answer());
        c6.setText("Correct Answer = "+" option"+listeningTestPart6.get(position).getQ6_answer());
        c7.setVisibility(View.GONE);
        c8.setVisibility(View.GONE);
        u7.setVisibility(View.GONE);
        u8.setVisibility(View.GONE);
        final Button buttonBackToListeningTest = convertView.findViewById(R.id.buttonBackTOListeningTest);
        //========================================================================================//
        /****************************************Question Part*************************************/
        //========================================================================================//
        TextView  question1 = convertView.findViewById(R.id.question1);
        TextView  question2 = convertView.findViewById(R.id.question2);
        TextView  question3 = convertView.findViewById(R.id.question3);
        TextView  question4 = convertView.findViewById(R.id.question4);
        TextView  question5 = convertView.findViewById(R.id.question5);
        TextView  question6 = convertView.findViewById(R.id.question6);
        optionsquestion1   = convertView.findViewById(R.id.optionsquestion1);
        optionsquestion2   = convertView.findViewById(R.id.optionsquestion2);
        optionsquestion3   = convertView.findViewById(R.id.optionsquestion3);
        optionsquestion4   = convertView.findViewById(R.id.optionsquestion4);
        optionsquestion5   = convertView.findViewById(R.id.optionsquestion5);
        optionsquestion6   = convertView.findViewById(R.id.optionsquestion6);
        question1.setText("1."+listeningTestPart6.get(position).getQ1_question());
        question2.setText("2."+listeningTestPart6.get(position).getQ2_question());
        question3.setText("3."+listeningTestPart6.get(position).getQ3_question());
        question4.setText("4."+listeningTestPart6.get(position).getQ4_question());
        question5.setText("5."+listeningTestPart6.get(position).getQ5_question());
        question6.setText("6."+listeningTestPart6.get(position).getQ6_question());
        final Button buttonSubmit = convertView.findViewById(R.id.buttonSubmitTest);
        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");
        ArrayList<String> question1_option = new ArrayList<>();
        question1_option.add("");
        question1_option.add(listeningTestPart6.get(position).getQ1_option1());
        question1_option.add(listeningTestPart6.get(position).getQ1_option2());
        question1_option.add(listeningTestPart6.get(position).getQ1_option3());
        question1_option.add(listeningTestPart6.get(position).getQ1_option4());
        ArrayList<String> question2_option = new ArrayList<>();
        question2_option.add("");
        question2_option.add(listeningTestPart6.get(position).getQ2_option1());
        question2_option.add(listeningTestPart6.get(position).getQ2_option2());
        question2_option.add(listeningTestPart6.get(position).getQ2_option3());
        question2_option.add(listeningTestPart6.get(position).getQ2_option4());
        ArrayList<String> question3_option = new ArrayList<>();
        question3_option.add("");
        question3_option.add(listeningTestPart6.get(position).getQ3_option1());
        question3_option.add(listeningTestPart6.get(position).getQ3_option2());
        question3_option.add(listeningTestPart6.get(position).getQ3_option3());
        question3_option.add(listeningTestPart6.get(position).getQ3_option4());
        ArrayList<String> question4_option = new ArrayList<>();
        question4_option.add("");
        question4_option.add(listeningTestPart6.get(position).getQ4_option1());
        question4_option.add(listeningTestPart6.get(position).getQ4_option2());
        question4_option.add(listeningTestPart6.get(position).getQ4_option3());
        question4_option.add(listeningTestPart6.get(position).getQ4_option4());
        ArrayList<String> question5_option = new ArrayList<>();
        question5_option.add("");
        question5_option.add(listeningTestPart6.get(position).getQ5_option1());
        question5_option.add(listeningTestPart6.get(position).getQ5_option2());
        question5_option.add(listeningTestPart6.get(position).getQ5_option3());
        question5_option.add(listeningTestPart6.get(position).getQ5_option4());
        ArrayList<String> question6_option = new ArrayList<>();
        question6_option.add("");
        question6_option.add(listeningTestPart6.get(position).getQ6_option1());
        question6_option.add(listeningTestPart6.get(position).getQ6_option2());
        question6_option.add(listeningTestPart6.get(position).getQ6_option3());
        question6_option.add(listeningTestPart6.get(position).getQ6_option4());
        adapter1 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question1_option);
        optionsquestion1.setAdapter(adapter1);
        optionsquestion1.setOnItemSelectedListener(new MySpinner());
        adapter2 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question2_option);
        optionsquestion2.setAdapter(adapter2);
        optionsquestion2.setOnItemSelectedListener(new MySpinner());
        adapter3 = new ArrayAdapter<>(c, R.layout.spinner_item,R.id.customSpinnerItemTextView, question3_option);
        optionsquestion3.setAdapter(adapter3);
        optionsquestion3.setOnItemSelectedListener(new MySpinner());
        adapter4 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question4_option);
        optionsquestion4.setAdapter(adapter4);
        optionsquestion4.setOnItemSelectedListener(new MySpinner());
        adapter5 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question5_option);
        optionsquestion5.setAdapter(adapter5);
        optionsquestion5.setOnItemSelectedListener(new MySpinner());
        adapter6 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question6_option);
        optionsquestion6.setAdapter(adapter6);
        optionsquestion6.setOnItemSelectedListener(new MySpinner());
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame1.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                u1.setText("Your Answer = "+userAnswerQuestion1);
                u2.setText("Your Answer = "+userAnswerQuestion2);
                u3.setText("Your Answer = "+userAnswerQuestion3);
                u4.setText("Your Answer = "+userAnswerQuestion4);
                u5.setText("Your Answer = "+userAnswerQuestion5);
                u6.setText("Your Answer = "+userAnswerQuestion6);
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/lsPart6Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, "Answer Submitted", Toast.LENGTH_SHORT).show();
                                Log.e("===Answer part6",urlForSubmittingOptions);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, error.networkResponse.allHeaders.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("===ERROR Answer part6",urlForSubmittingOptions  +"\n"+error.networkResponse.allHeaders.toString());
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
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c.getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
        buttonBackToListeningTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(c, "PLEASE SELECT THE OPTIONS!!", Toast.LENGTH_SHORT).show();
        }
    }
}