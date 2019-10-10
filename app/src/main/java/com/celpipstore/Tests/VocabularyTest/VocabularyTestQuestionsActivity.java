package com.celpipstore.Tests.VocabularyTest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import com.celpipstore.Adapter.VocabularyTestQuestionAdapter;
import com.celpipstore.JsonData.JsonDataHandlerVocabularyTestQuestionList;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

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

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
        }
        else
        {
            AlertDialog alertbox = new AlertDialog.Builder(this)
                    .setMessage("Check Your Internet Connention?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                            startActivity(intent);
                            recreate();
                        }
                    })
                    .show();
            Toast.makeText(VocabularyTestQuestionsActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
        radioGroupOptions = findViewById(R.id.radiogroupOptions);
        viewReportLayout = findViewById(R.id.viewReportLayout);
        questionLayout = findViewById(R.id.questionLayout);
        textViewQuestion = findViewById(R.id.question);
        textViewGivenAnswer = findViewById(R.id.givenanswer);
        textViewCorrectAnswer = findViewById(R.id.correctanswer);
        radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        radioButtonOption3 = findViewById(R.id.radioButtonOption3);
        radioButtonOption4 = findViewById(R.id.radioButtonOption4);
        radioButtonOption1.setChecked(true);
        radioButtonOption2.setChecked(false);
        radioButtonOption3.setChecked(false);
        radioButtonOption4.setChecked(false);
        buttonSubmit = findViewById(R.id.submit);
        buttonNext = findViewById(R.id.next);
        buttonViewReport = findViewById(R.id.viewReport);
        buttonDone = findViewById(R.id.done);
        buttonBack = findViewById(R.id.back);
        buttonFinish = findViewById(R.id.finish);


        Intent intent = getIntent();
        String id = intent.getStringExtra("t1");
        url = "http://online.celpip.biz/api/vocabularyQuestion?lavelId="+id;
        Log.d("url", url);
        listView = findViewById(R.id.listViewVocabularyTestQuestionsList);

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
                String[] total_questions = JsonDataHandlerVocabularyTestQuestionList.id;
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
                Intent intent = new Intent(VocabularyTestQuestionsActivity.this, VOCABULARY_TESTS.class);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
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
        VocabularyTestQuestionAdapter ca = new VocabularyTestQuestionAdapter(this, JsonDataHandlerVocabularyTestQuestionList.id, JsonDataHandlerVocabularyTestQuestionList.level_id, JsonDataHandlerVocabularyTestQuestionList.title, JsonDataHandlerVocabularyTestQuestionList.question_image, JsonDataHandlerVocabularyTestQuestionList.options1, JsonDataHandlerVocabularyTestQuestionList.options2, JsonDataHandlerVocabularyTestQuestionList.options3, JsonDataHandlerVocabularyTestQuestionList.options4);
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
