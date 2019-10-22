package com.celpipstore.Adapter.ReadingTestAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.celpipstore.CelpipTests.ReadingTest.READING;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart2;
import com.celpipstore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart1.q10_question;
import static com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart1.q9_question;


public class ReadingTestPart2QuestionAdapter extends BaseAdapter{

    private Context c;
    private String url ="https://demo.celpip.biz/uploads/part1_listening/";
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
    private String id;
    private String test_code;
    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;
    private String q1_question;
    private String q1_option1;
    private String q1_option2;
    private String q1_option3;
    private String q1_option4;
    private String q1_answer;
    private String q2_question;
    private String q2_option1;
    private String q2_option2;
    private String q2_option3;
    private String q2_option4;
    private String q2_answer;
    private String q3_question;
    private String q3_option1;
    private String q3_option2;
    private String q3_option3;
    private String q3_option4;
    private String q3_answer;
    private String q4_question;
    private String q4_option1;
    private String q4_option2;
    private String q4_option3;
    private String q4_option4;
    private String q4_answer;
    private String q5_question;
    private String q5_option1;
    private String q5_option2;
    private String q5_option3;
    private String q5_option4;
    private String q5_answer;
    private String q6_question;
    private String q6_option1;
    private String q6_option2;
    private String q6_option3;
    private String q6_option4;
    private String q6_answer;
    private String q7_question;
    private String q7_option1;
    private String q7_option2;
    private String q7_option3;
    private String q7_option4;
    private String q7_answer;
    private String q8_question;
    private String q8_option1;
    private String q8_option2;
    private String q8_option3;
    private String q8_option4;
    private String q8_answer;

    public ReadingTestPart2QuestionAdapter(Context c,String id, String test_code, String image_1, String image_2, String image_3, String image_4, String q1_question, String q1_option1, String q1_option2, String q1_option3, String q1_option4, String q1_answer, String q2_question, String q2_option1, String q2_option2, String q2_option3, String q2_option4, String q2_answer, String q3_question, String q3_option1, String q3_option2, String q3_option3, String q3_option4, String q3_answer, String q4_question, String q4_option1, String q4_option2, String q4_option3, String q4_option4, String q4_answer, String q5_question, String q5_option1, String q5_option2, String q5_option3, String q5_option4, String q5_answer, String q6_question, String q6_option1, String q6_option2, String q6_option3, String q6_option4, String q6_answer, String q7_question, String q7_option1, String q7_option2, String q7_option3, String q7_option4, String q7_answer, String q8_question, String q8_option1, String q8_option2, String q8_option3, String q8_option4, String q8_answer) {
        this.c=c;
        this.id = id;
        this.test_code = test_code;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.image_3 = image_3;
        this.image_4 = image_4;
        this.q1_question = q1_question;
        this.q1_option1 = q1_option1;
        this.q1_option2 = q1_option2;
        this.q1_option3 = q1_option3;
        this.q1_option4 = q1_option4;
        this.q1_answer = q1_answer;
        this.q2_question = q2_question;
        this.q2_option1 = q2_option1;
        this.q2_option2 = q2_option2;
        this.q2_option3 = q2_option3;
        this.q2_option4 = q2_option4;
        this.q2_answer = q2_answer;
        this.q3_question = q3_question;
        this.q3_option1 = q3_option1;
        this.q3_option2 = q3_option2;
        this.q3_option3 = q3_option3;
        this.q3_option4 = q3_option4;
        this.q3_answer = q3_answer;
        this.q4_question = q4_question;
        this.q4_option1 = q4_option1;
        this.q4_option2 = q4_option2;
        this.q4_option3 = q4_option3;
        this.q4_option4 = q4_option4;
        this.q4_answer = q4_answer;
        this.q5_question = q5_question;
        this.q5_option1 = q5_option1;
        this.q5_option2 = q5_option2;
        this.q5_option3 = q5_option3;
        this.q5_option4 = q5_option4;
        this.q5_answer = q5_answer;
        this.q6_question = q6_question;
        this.q6_option1 = q6_option1;
        this.q6_option2 = q6_option2;
        this.q6_option3 = q6_option3;
        this.q6_option4 = q6_option4;
        this.q6_answer = q6_answer;
        this.q7_question = q7_question;
        this.q7_option1 = q7_option1;
        this.q7_option2 = q7_option2;
        this.q7_option3 = q7_option3;
        this.q7_option4 = q7_option4;
        this.q7_answer = q7_answer;
        this.q8_question = q8_question;
        this.q8_option1 = q8_option1;
        this.q8_option2 = q8_option2;
        this.q8_option3 = q8_option3;
        this.q8_option4 = q8_option4;
        this.q8_answer = q8_answer;
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
        convertView=in.inflate(R.layout.test_adpater_readingtestpart2,null);
        //***************************************************************************//
        /***********************************Images***********************************/
        //***************************************************************************//
        ImageView imageView1 = convertView.findViewById(R.id.reading_part2_image1);
        ImageView imageView2 = convertView.findViewById(R.id.reading_part2_image2);
        ImageView imageView3 = convertView.findViewById(R.id.reading_part2_image3);
        ImageView imageView4 = convertView.findViewById(R.id.reading_part2_image4);
        String i1 = url+image_1;
        Log.e("===i1",i1);
        Glide.with(c)
             .load(url+image_1)
             .into(imageView1);
        Glide.with(c)
                .load(url+image_2)
                .into(imageView2);
        Glide.with(c)
                .load(url+image_3)
                .into(imageView3);
        Glide.with(c)
                .load(url+image_4)
                .into(imageView4);
        //***************************************************************************//
        /********************************Reading Test Part 1 Questions***************/
        //**************************************************************************//
        TextView reading_part2_question1 = convertView.findViewById(R.id.reading_part2_question1);
        TextView reading_part2_question2 = convertView.findViewById(R.id.reading_part2_question2);
        TextView reading_part2_question3 = convertView.findViewById(R.id.reading_part2_question3);
        TextView reading_part2_question4 = convertView.findViewById(R.id.reading_part2_question4);
        TextView reading_part2_question5 = convertView.findViewById(R.id.reading_part2_question5);
        TextView reading_part2_question6 = convertView.findViewById(R.id.reading_part2_question6);
        TextView reading_part2_question7 = convertView.findViewById(R.id.reading_part2_question7);
        TextView reading_part2_question8 = convertView.findViewById(R.id.reading_part2_question8);
        String question1 = q1_question;
        question1=question1.replaceAll("<p>","");
        question1=question1.replaceAll("</p>","");
        question1=question1.replaceAll("<br>","");
        question1=question1.replaceAll("</br>","");
        reading_part2_question1.setText(question1);
        reading_part2_question2.setText(JsonDataHandlerReadingPart2.q2_question);
        reading_part2_question3.setText(JsonDataHandlerReadingPart2.q3_question);
        reading_part2_question4.setText(JsonDataHandlerReadingPart2.q4_question);
        reading_part2_question5.setText(JsonDataHandlerReadingPart2.q5_question);
        reading_part2_question6.setText(JsonDataHandlerReadingPart2.q6_question);
        reading_part2_question7.setText(JsonDataHandlerReadingPart2.q7_question);
        reading_part2_question8.setText(JsonDataHandlerReadingPart2.q8_question);
        //***************************************************************************//
        /********************************Reading Test Part 1 Answer******************/
        //**************************************************************************//
        optionsquestion1    = convertView.findViewById(R.id.reading_part2_question1_options);
        optionsquestion2    = convertView.findViewById(R.id.reading_part2_question2_options);
        optionsquestion3    = convertView.findViewById(R.id.reading_part2_question3_options);
        optionsquestion4    = convertView.findViewById(R.id.reading_part2_question4_options);
        optionsquestion5    = convertView.findViewById(R.id.reading_part2_question5_options);
        optionsquestion6    = convertView.findViewById(R.id.reading_part2_question6_options);
        optionsquestion7    = convertView.findViewById(R.id.reading_part2_question7_options);
        optionsquestion8    = convertView.findViewById(R.id.reading_part2_question8_options);
        ArrayList<String> question1_option = new ArrayList<>();
        question1_option.add("");
        question1_option.add(q1_option1);
        question1_option.add(q1_option2);
        question1_option.add(q1_option3);
        question1_option.add(q1_option4);
        ArrayList<String> question2_option = new ArrayList<>();
        question2_option.add("");
        question2_option.add(q2_option1);
        question2_option.add(q2_option2);
        question2_option.add(q2_option3);
        question2_option.add(q2_option4);
        ArrayList<String> question3_option = new ArrayList<>();
        question3_option.add("");
        question3_option.add(q3_option1);
        question3_option.add(q3_option2);
        question3_option.add(q3_option3);
        question3_option.add(q3_option4);
        ArrayList<String> question4_option = new ArrayList<>();
        question4_option.add("");
        question4_option.add(q4_option1);
        question4_option.add(q4_option2);
        question4_option.add(q4_option3);
        question4_option.add(q4_option4);
        ArrayList<String> question5_option = new ArrayList<>();
        question5_option.add("");
        question5_option.add(q5_option1);
        question5_option.add(q5_option2);
        question5_option.add(q5_option3);
        question5_option.add(q5_option4);
        ArrayList<String> question6_option = new ArrayList<>();
        question6_option.add("");
        question6_option.add(q6_option1);
        question6_option.add(q6_option2);
        question6_option.add(q6_option3);
        question6_option.add(q6_option4);
        ArrayList<String> question7_option = new ArrayList<>();
        question7_option.add("");
        question7_option.add(q7_option1);
        question7_option.add(q7_option2);
        question7_option.add(q7_option3);
        question7_option.add(q7_option4);
        ArrayList<String> question8_option = new ArrayList<>();
        question8_option.add("");
        question8_option.add(q8_option1);
        question8_option.add(q8_option2);
        question8_option.add(q8_option3);
        question8_option.add(q8_option4);
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
        adapter7 = new ArrayAdapter<>(c,  R.layout.spinner_item,R.id.customSpinnerItemTextView, question7_option);
        optionsquestion7.setAdapter(adapter7);
        optionsquestion7.setOnItemSelectedListener(new MySpinner());
        adapter8 = new ArrayAdapter<>(c, R.layout.spinner_item,R.id.customSpinnerItemTextView, question8_option);
        optionsquestion8.setAdapter(adapter8);
        optionsquestion8.setOnItemSelectedListener(new MySpinner());
        //******************************************************************************************//
        /**************************************Answer Submitting************************************/
        //*****************************************************************************************//
        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");
        Button buttonSubmit = convertView.findViewById(R.id.buttonReadingPart2Submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/rdPart2Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&q7_response="+userAnswerQuestion7+"&q8_response="+userAnswerQuestion8+"&memberid="+member_id;
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
                Intent intent = new Intent(c , READING.class);
                c.startActivity(intent);
            }
        });
        return convertView;
    }
    private class MySpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapterView.getId() ==R.id.reading_part2_question1_options) {
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
            if (adapterView.getId() ==R.id.reading_part2_question2_options) {
                if (i == 1) {
                    userAnswerQuestion2 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion2 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion2 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion2 = "option4";
                }
            }
            if (adapterView.getId() ==R.id.reading_part2_question3_options) {
                if (i == 1) {
                    userAnswerQuestion3 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion3 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion3 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion3 = "option4";
                }
            }
            if (adapterView.getId() ==R.id.reading_part2_question4_options) {
                if (i == 1) {
                    userAnswerQuestion4 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion4 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion4 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion4 = "option4";
                }
            }
            if (adapterView.getId() ==R.id.reading_part2_question5_options) {
                if (i == 1) {
                    userAnswerQuestion5 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion5 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion5 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion5 = "option4";
                }
            }
            if (adapterView.getId() ==R.id.reading_part2_question6_options) {
                if (i == 1) {
                    userAnswerQuestion6 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion6 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion6 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion6 = "option4";
                }
            }
            if (adapterView.getId() ==R.id.reading_part2_question7_options) {
                if (i == 1) {
                    userAnswerQuestion7 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion7 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion7 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion7 = "option4";
                }
            }
            if (adapterView.getId() ==R.id.reading_part2_question8_options) {
                if (i == 1) {
                    userAnswerQuestion8 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion8 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion8 = "option3";
                }
                if (i == 4) {
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