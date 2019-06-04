package com.celpipstore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VocabularyTestQuestionsActivity extends AppCompatActivity {

    ListView listView;
    Button buttonSubmit,buttonNext,buttonViewReport,buttonDone,buttonBack,buttonFinish;
    TextView textViewQuestion,textViewGivenAnswer,textViewCorrectAnswer;
    RadioGroup radioGroupOptions;
    RadioButton radioButtonOption1,radioButtonOption2,radioButtonOption3,radioButtonOption4;
    String url;
    int position =0;
    JsonDataHandlerVocabularyTestQuestionList jsonHolderListing;
    LinearLayout questionLayout,viewReportLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_test_questions);

        radioGroupOptions = findViewById(R.id.radiogroupOptions);

        viewReportLayout = (LinearLayout)findViewById(R.id.viewReportLayout);
        questionLayout = (LinearLayout)findViewById(R.id.questionLayout);

        textViewQuestion = (TextView)findViewById(R.id.question);
        textViewGivenAnswer = (TextView)findViewById(R.id.givenanswer);
        textViewCorrectAnswer = (TextView)findViewById(R.id.correctanswer);

        radioButtonOption1 =(RadioButton)findViewById(R.id.radioButtonOption1);
        radioButtonOption2 =(RadioButton)findViewById(R.id.radioButtonOption2);
        radioButtonOption3 =(RadioButton)findViewById(R.id.radioButtonOption3);
        radioButtonOption4 =(RadioButton)findViewById(R.id.radioButtonOption4);

        radioButtonOption1.setChecked(true);
        radioButtonOption2.setChecked(false);
        radioButtonOption3.setChecked(false);
        radioButtonOption4.setChecked(false);

        buttonSubmit = (Button)findViewById(R.id.submit);
        buttonNext = (Button)findViewById(R.id.next);
        buttonViewReport = (Button)findViewById(R.id.viewReport);
        buttonDone = (Button)findViewById(R.id.done);
        buttonBack = (Button)findViewById(R.id.back);
        buttonFinish = (Button)findViewById(R.id.finish);


        Intent intent = getIntent();
        String id = intent.getStringExtra("t1");
        url = "http://online.celpip.biz/api/vocabularyQuestion?lavelId="+id;
        Log.d("url", url);
        listView =(ListView)findViewById(R.id.listViewVocabularyTestQuestionsList);

        buttonViewReport.setVisibility(View.GONE);
        buttonBack.setVisibility(View.GONE);
        sendRequest();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = ++position;
                setData(p);

                    buttonBack.setVisibility(View.VISIBLE);
                    radioButtonOption1.setChecked(true);
                    radioButtonOption2.setChecked(false);
                    radioButtonOption3.setChecked(false);
                    radioButtonOption4.setChecked(false);
                    buttonSubmit.setVisibility(View.VISIBLE);
                    buttonViewReport.setVisibility(View.GONE);


                String total_questions[] = jsonHolderListing.id;
                int aa = total_questions.length;

                if (position == aa-1)
                {
                        buttonNext.setVisibility(View.GONE);
                        buttonFinish.setVisibility(View.VISIBLE);
                }


            }
        });
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VocabularyTestQuestionsActivity.this,VOCABULARY_TESTS.class);
                startActivity(intent);
            }
        });
        buttonViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionLayout.setVisibility(View.GONE);
                viewReportLayout.setVisibility(View.VISIBLE);
                textViewGivenAnswer.setVisibility(View.VISIBLE);
                textViewCorrectAnswer.setVisibility(View.VISIBLE);
            }
        });
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewReportLayout.setVisibility(View.GONE);
                questionLayout.setVisibility(View.VISIBLE);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = --position;
                setData(p);
                buttonNext.setVisibility(View.VISIBLE);
                buttonFinish.setVisibility(View.GONE);
                if (position==0)
                {
                    buttonBack.setVisibility(View.GONE);
                }

            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                buttonViewReport.setVisibility(View.VISIBLE);
                String option1 = JsonDataHandlerVocabularyTestQuestionList.options1_is_correct[position];
                String option2 = JsonDataHandlerVocabularyTestQuestionList.options2_is_correct[position];
                String option3 = JsonDataHandlerVocabularyTestQuestionList.options3_is_correct[position];
                String option4 = JsonDataHandlerVocabularyTestQuestionList.options4_is_correct[position];

                String a= "1";

                if (radioButtonOption1.isChecked())
                {
                    if (a.equals(option1))
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                        textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                    }
                    else
                    {
                        Toast.makeText(VocabularyTestQuestionsActivity.this, option1, Toast.LENGTH_SHORT).show();
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                        if (option1.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                        }
                        else if (option2.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                        }
                        else if (option3.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                        }
                        else
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                        }
                    }

                }
                if (radioButtonOption2.isChecked())
                {
                    if (a.equals(option2))
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                        textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                    }
                    else
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                        if (option1.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                        }
                        else if (option2.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                        }
                        else if (option3.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                        }
                        else
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                        }


                    }
                }
                if (radioButtonOption3.isChecked())
                {
                    if (a.equals(option3))
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                        textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                    }
                    else
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                        if (option1.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                        }
                        else if (option2.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                        }
                        else if (option3.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                        }
                        else
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                        }
                    }
                }
                if (radioButtonOption4.isChecked())
                {
                    if (a.equals(option4))
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                        textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                    }
                    else
                    {
                        textViewGivenAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                        if (option1.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
                        }
                        else if (option2.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
                        }
                        else if (option3.equals(a))
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
                        }
                        else
                        {
                            textViewCorrectAnswer.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);
                        }
                    }
                }



                buttonSubmit.setVisibility(View.GONE);
            }
        });
    }

    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));

                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(VocabularyTestQuestionsActivity.this, "Application Maintance in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json) {
        JsonDataHandlerVocabularyTestQuestionList jsonHolderListing = new JsonDataHandlerVocabularyTestQuestionList(json);
        jsonHolderListing.parseJSON();

        setData(position);
        VocabularyTestQuestionAdapter ca = new VocabularyTestQuestionAdapter(this,jsonHolderListing.id,jsonHolderListing.level_id, jsonHolderListing.title,jsonHolderListing.question_image,jsonHolderListing.options1,jsonHolderListing.options2,jsonHolderListing.options3,jsonHolderListing.options4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();


    }

    private void setData(int position) {
        textViewQuestion.setText(JsonDataHandlerVocabularyTestQuestionList.title[position]);
        radioButtonOption1.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
        radioButtonOption2.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position]);
        radioButtonOption3.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position]);
        radioButtonOption4.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position]);

    }
}
