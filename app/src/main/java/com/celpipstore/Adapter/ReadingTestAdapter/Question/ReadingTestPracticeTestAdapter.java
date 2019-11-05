package com.celpipstore.Adapter.ReadingTestAdapter.Question;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
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
import com.celpipstore.CelpipTests.ReadingTest.READING;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerPracticeTestReading;
import com.celpipstore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ReadingTestPracticeTestAdapter extends BaseAdapter{

    private Context c;
    private String id;
    private String test_code;
    private String question;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String userAnswerQuestion1;
    public ReadingTestPracticeTestAdapter(Context c, String id, String test_code, String question, String question_title, String option1, String option2, String option3, String option4, String answer) {
        this.c = c;
        this.id = id;
        this.test_code = test_code;
        this.question = question;
        this.question_title = question_title;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
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
        convertView=in.inflate(R.layout.readingpracticetest_question_and_answer_layout,null);
        final LinearLayout frame1 = convertView.findViewById(R.id.frame1);
        //========================================================================================//
        /************************************Correct Answer****************************************/
        //========================================================================================//
        final ScrollView scrollView = convertView.findViewById(R.id.userAnswer);
        final TextView c1 = convertView.findViewById(R.id.question1_correct);
        TextView c2 = convertView.findViewById(R.id.question2_correct);
        TextView c3 = convertView.findViewById(R.id.question3_correct);
        TextView c4 = convertView.findViewById(R.id.question4_correct);
        TextView c5 = convertView.findViewById(R.id.question5_correct);
        TextView c6 = convertView.findViewById(R.id.question6_correct);
        TextView c7 = convertView.findViewById(R.id.question7_correct);
        TextView c8 = convertView.findViewById(R.id.question8_correct);
        TextView c9 = convertView.findViewById(R.id.question9_correct);
        TextView c10 = convertView.findViewById(R.id.question10_correct);
        TextView c11 = convertView.findViewById(R.id.question11_correct);
        final TextView u1 = convertView.findViewById(R.id.question1_user);
        final TextView u2 = convertView.findViewById(R.id.question2_user);
        final TextView u3 = convertView.findViewById(R.id.question3_user);
        final TextView u4 = convertView.findViewById(R.id.question4_user);
        final TextView u5 = convertView.findViewById(R.id.question5_user);
        final TextView u6 = convertView.findViewById(R.id.question6_user);
        final TextView u7 = convertView.findViewById(R.id.question7_user);
        final TextView u8 = convertView.findViewById(R.id.question8_user);
        final TextView u9  = convertView.findViewById(R.id.question9_user);
        final TextView u10 = convertView.findViewById(R.id.question10_user);
        final TextView u11 = convertView.findViewById(R.id.question11_user);
        c1 .setText("1.Correct Answer = "+" option"+answer);
        c2 .setVisibility(View.GONE);
        c3 .setVisibility(View.GONE);
        c4 .setVisibility(View.GONE);
        c5 .setVisibility(View.GONE);
        c6 .setVisibility(View.GONE);
        c7 .setVisibility(View.GONE);
        c8 .setVisibility(View.GONE);
        c9 .setVisibility(View.GONE);
        c10.setVisibility(View.GONE);
        c11.setVisibility(View.GONE);
        u2 .setVisibility(View.GONE);
        u3 .setVisibility(View.GONE);
        u4 .setVisibility(View.GONE);
        u5 .setVisibility(View.GONE);
        u6 .setVisibility(View.GONE);
        u7 .setVisibility(View.GONE);
        u8 .setVisibility(View.GONE);
        u9 .setVisibility(View.GONE);
        u10.setVisibility(View.GONE);
        u11.setVisibility(View.GONE);
        final Button buttonBackToListeningTest = convertView.findViewById(R.id.buttonBackTOListeningTest);
        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");
        TextView q = convertView.findViewById(R.id.question);
        TextView q_title = convertView.findViewById(R.id.question_title);
        Spinner options = convertView.findViewById(R.id.options);
        ArrayList<String> question_option = new ArrayList<>();
        question_option.add("");
        question_option.add(option1);
        question_option.add(option2);
        question_option.add(option3);
        question_option.add(option4);
        q.setText(question);
        q_title.setText(question_title);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question_option);
        options.setAdapter(adapter1);
        options.setOnItemSelectedListener(new MySpinner());
        Button   submit  = convertView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame1.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                u1.setText("1.Your Answer = "+userAnswerQuestion1);
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/practiceTaskSubmit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, "Answer Submitted!!", Toast.LENGTH_SHORT).show();
                                Log.e("===Answer",urlForSubmittingOptions);
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, "Error Submitting Answer!! "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("===ErrorAnswer",urlForSubmittingOptions);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("q1_response", userAnswerQuestion1);
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
                Intent intent = new Intent(c, READING.class);
                c.startActivity(intent);
            }
        });
        return convertView;
    }

    private class MySpinner implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapterView.getId() ==R.id.options) {
                if (i == 1) {
                    userAnswerQuestion1 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion1 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion1 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion1 = "option4";
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}