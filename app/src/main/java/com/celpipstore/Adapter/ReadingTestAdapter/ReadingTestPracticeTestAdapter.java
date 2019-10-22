package com.celpipstore.Adapter.ReadingTestAdapter;

import android.app.ProgressDialog;
import android.content.Context;
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
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/practiceTaskSubmit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, response, Toast.LENGTH_SHORT).show();
                                Log.e("===Answer",urlForSubmittingOptions);
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, error.getMessage(), Toast.LENGTH_SHORT).show();
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