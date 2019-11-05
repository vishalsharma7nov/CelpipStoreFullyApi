package com.celpipstore.Adapter.ReadingTestAdapter.Question;

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
import com.celpipstore.CelpipTests.ReadingTest.READING;
import com.celpipstore.GetterAndSetterClasses.ReadingTest.Question.ReadingTestPart3;
import com.celpipstore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReadingTestPart3QuestionAdapter extends BaseAdapter{

    private Context c;
    private List<ReadingTestPart3> readingTestPart3;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;
    private ArrayAdapter<String> adapter5;
    private ArrayAdapter<String> adapter6;
    private ArrayAdapter<String> adapter7;
    private ArrayAdapter<String> adapter8;
    private ArrayAdapter<String> adapter9;
    private String userAnswerQuestion1;
    private String userAnswerQuestion2;
    private String userAnswerQuestion3;
    private String userAnswerQuestion4;
    private String userAnswerQuestion5;
    private String userAnswerQuestion6;
    private String userAnswerQuestion7;
    private String userAnswerQuestion8;
    private String userAnswerQuestion9;
    private Spinner optionsquestion1;
    private Spinner optionsquestion2;
    private Spinner optionsquestion3;
    private Spinner optionsquestion4;
    private Spinner optionsquestion5;
    private Spinner optionsquestion6;
    private Spinner optionsquestion7;
    private Spinner optionsquestion8;
    private Spinner optionsquestion9;

    public ReadingTestPart3QuestionAdapter(Context c, List<ReadingTestPart3> readingTestPart3) {
        this.c = c;
        this.readingTestPart3 = readingTestPart3;
    }

    @Override
    public int getCount() {
        return readingTestPart3.size();
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
        convertView=in.inflate(R.layout.test_adpater_readingtestpart3,null);
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
        c1 .setText("1.Correct Answer = "+" option"+readingTestPart3.get(position).getQ1_question());
        c2 .setText("2.Correct Answer = "+" option"+readingTestPart3.get(position).getQ2_question());
        c3 .setText("3.Correct Answer = "+" option"+readingTestPart3.get(position).getQ3_question());
        c4 .setText("4.Correct Answer = "+" option"+readingTestPart3.get(position).getQ4_question());
        c5 .setText("5.Correct Answer = "+" option"+readingTestPart3.get(position).getQ5_question());
        c6 .setText("6.Correct Answer = "+" option"+readingTestPart3.get(position).getQ6_question());
        c7 .setText("7.Correct Answer = "+" option"+readingTestPart3.get(position).getQ7_question());
        c8 .setText("8.Correct Answer = "+" option"+readingTestPart3.get(position).getQ8_question());
        c9 .setText("9.Correct Answer = "+" option"+readingTestPart3.get(position).getQ9_question());
        c10.setVisibility(View.GONE);
        c11.setVisibility(View.GONE);
        u10.setVisibility(View.GONE);
        u11.setVisibility(View.GONE);
        final Button buttonBackToListeningTest = convertView.findViewById(R.id.buttonBackTOListeningTest);
        //***************************************************************************//
        /***********************************Images***********************************/
        //***************************************************************************//
        TextView passage = convertView.findViewById(R.id.passage);
        String text = readingTestPart3.get(position).getPassages_1()+"\n\n\n"+readingTestPart3.get(position).getPassages_2()+"\n\n\n"+readingTestPart3.get(position).getPassages_3()+"\n\n\n"+readingTestPart3.get(position).getPassages_4();
        text=text.replaceAll("<p>","");
        text=text.replaceAll("</p>","");
        text=text.replaceAll("<b>"," ");
        text=text.replaceAll("</b>"," ");
        passage.setText(text);
        //***************************************************************************//
        /********************************Reading Test Part 1 Questions***************/
        //**************************************************************************//
        TextView reading_part3_question1 = convertView.findViewById(R.id.reading_part3_question1);
        TextView reading_part3_question2 = convertView.findViewById(R.id.reading_part3_question2);
        TextView reading_part3_question3 = convertView.findViewById(R.id.reading_part3_question3);
        TextView reading_part3_question4 = convertView.findViewById(R.id.reading_part3_question4);
        TextView reading_part3_question5 = convertView.findViewById(R.id.reading_part3_question5);
        TextView reading_part3_question6 = convertView.findViewById(R.id.reading_part3_question6);
        TextView reading_part3_question7 = convertView.findViewById(R.id.reading_part3_question7);
        TextView reading_part3_question8 = convertView.findViewById(R.id.reading_part3_question8);
        TextView reading_part3_question9 = convertView.findViewById(R.id.reading_part3_question9);
        reading_part3_question1.setText(readingTestPart3.get(position).getQ1_question());
        reading_part3_question2.setText(readingTestPart3.get(position).getQ2_question());
        reading_part3_question3.setText(readingTestPart3.get(position).getQ3_question());
        reading_part3_question4.setText(readingTestPart3.get(position).getQ4_question());
        reading_part3_question5.setText(readingTestPart3.get(position).getQ5_question());
        reading_part3_question6.setText(readingTestPart3.get(position).getQ6_question());
        reading_part3_question7.setText(readingTestPart3.get(position).getQ7_question());
        reading_part3_question8.setText(readingTestPart3.get(position).getQ8_question());
        reading_part3_question9.setText(readingTestPart3.get(position).getQ9_question());
        //***************************************************************************//
        /********************************Reading Test Part 1 Answer******************/
        //**************************************************************************//
        optionsquestion1    = convertView.findViewById(R.id.reading_part3_question1_options);
        optionsquestion2    = convertView.findViewById(R.id.reading_part3_question2_options);
        optionsquestion3    = convertView.findViewById(R.id.reading_part3_question3_options);
        optionsquestion4    = convertView.findViewById(R.id.reading_part3_question4_options);
        optionsquestion5    = convertView.findViewById(R.id.reading_part3_question5_options);
        optionsquestion6    = convertView.findViewById(R.id.reading_part3_question6_options);
        optionsquestion7    = convertView.findViewById(R.id.reading_part3_question7_options);
        optionsquestion8    = convertView.findViewById(R.id.reading_part3_question8_options);
        optionsquestion9    = convertView.findViewById(R.id.reading_part3_question9_options);
        ArrayList<String> question1_option = new ArrayList<>();
        question1_option.add("");
        question1_option.add("A");
        question1_option.add("B");
        question1_option.add("C");
        question1_option.add("D");
        question1_option.add("E");
        ArrayList<String> question2_option = new ArrayList<>();
        question2_option.add("");
        question2_option.add("A");
        question2_option.add("B");
        question2_option.add("C");
        question2_option.add("D");
        question2_option.add("E");
        ArrayList<String> question3_option = new ArrayList<>();
        question3_option.add("");
        question3_option.add("A");
        question3_option.add("B");
        question3_option.add("C");
        question3_option.add("D");
        question3_option.add("E");
        ArrayList<String> question4_option = new ArrayList<>();
        question4_option.add("");
        question4_option.add("A");
        question4_option.add("B");
        question4_option.add("C");
        question4_option.add("D");
        question4_option.add("E");
        ArrayList<String> question5_option = new ArrayList<>();
        question5_option.add("");
        question5_option.add("A");
        question5_option.add("B");
        question5_option.add("C");
        question5_option.add("D");
        question5_option.add("E");
        ArrayList<String> question6_option = new ArrayList<>();
        question6_option.add("");
        question6_option.add("A");
        question6_option.add("B");
        question6_option.add("C");
        question6_option.add("D");
        question6_option.add("E");
        ArrayList<String> question7_option = new ArrayList<>();
        question7_option.add("");
        question7_option.add("A");
        question7_option.add("B");
        question7_option.add("C");
        question7_option.add("D");
        question7_option.add("E");
        ArrayList<String> question8_option = new ArrayList<>();
        question8_option.add("");
        question8_option.add("A");
        question8_option.add("B");
        question8_option.add("C");
        question8_option.add("D");
        question8_option.add("E");
        ArrayList<String> question9_option = new ArrayList<>();
        question9_option.add("");
        question9_option.add("A");
        question9_option.add("B");
        question9_option.add("C");
        question9_option.add("D");
        question9_option.add("E");
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
        adapter9 = new ArrayAdapter<>(c, R.layout.spinner_item,R.id.customSpinnerItemTextView, question9_option);
        optionsquestion9.setAdapter(adapter9);
        optionsquestion9.setOnItemSelectedListener(new MySpinner());
        //******************************************************************************************//
        /**************************************Answer Submitting************************************/
        //*****************************************************************************************//
        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");
        Button buttonSubmit = convertView.findViewById(R.id.buttonReadingPart3Submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame1.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                u1.setText("1.Your Answer = "+userAnswerQuestion1);
                u2.setText("2.Your Answer = "+userAnswerQuestion2);
                u3.setText("3.Your Answer = "+userAnswerQuestion3);
                u4.setText("4.Your Answer = "+userAnswerQuestion4);
                u5.setText("5.Your Answer = "+userAnswerQuestion5);
                u6.setText("6.Your Answer = "+userAnswerQuestion6);
                u7.setText("7.Your Answer = "+userAnswerQuestion7);
                u8.setText("8.Your Answer = "+userAnswerQuestion8);
                u9.setText("9.Your Answer = "+userAnswerQuestion9);
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/rdPart3Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&q7_response="+userAnswerQuestion7+"&q8_response="+userAnswerQuestion8+"&q9_response="+userAnswerQuestion9+"&memberid="+member_id;
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
                        params.put("q9_response", userAnswerQuestion9);
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
                Intent intent = new Intent(c , READING.class);
                c.startActivity(intent);
            }
        });
        return convertView;
    }
    private class MySpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapterView.getId() ==R.id.reading_part3_question1_options) {
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
                if (i == 4) {
                    userAnswerQuestion1 = "option5";
                }

            }
            if (adapterView.getId() ==R.id.reading_part3_question2_options) {
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
                if (i == 5) {
                    userAnswerQuestion2 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question3_options) {
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
                if (i == 5) {
                    userAnswerQuestion3 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question4_options) {
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
                if (i == 5) {
                    userAnswerQuestion4 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question5_options) {
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
                if (i == 5) {
                    userAnswerQuestion5 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question6_options) {
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
                if (i == 5) {
                    userAnswerQuestion6 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question7_options) {
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
                if (i == 5) {
                    userAnswerQuestion7 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question8_options) {
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
                if (i == 5) {
                    userAnswerQuestion8 = "option5";
                }
            }
            if (adapterView.getId() ==R.id.reading_part3_question9_options) {
                if (i == 1) {
                    userAnswerQuestion9 = "option1";
                }
                if (i == 2) {
                    userAnswerQuestion9 = "option2";
                }
                if (i == 3) {
                    userAnswerQuestion9 = "option3";
                }
                if (i == 4) {
                    userAnswerQuestion9 = "option4";
                }
                if (i == 5) {
                    userAnswerQuestion9 = "option5";
                }
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(c, "PLEASE SELECT THE OPTIONS!!", Toast.LENGTH_SHORT).show();
        }
    }
}