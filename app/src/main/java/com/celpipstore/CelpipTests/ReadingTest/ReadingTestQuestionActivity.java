package com.celpipstore.CelpipTests.ReadingTest;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.ReadingTestAdapter.ReadingTestPart1QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.ReadingTestPart2QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.ReadingTestPart3QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.ReadingTestPart4QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.ReadingTestPracticeTestAdapter;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerPracticeTestReading;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart1;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart2;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart3;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart4;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadingTestQuestionActivity extends AppCompatActivity {
    private String tokenCode,API,part1,part2,part3,part4,practicetest;
    private ListView listViewReadingPractice,listViewReadingPart1,listViewReadingPart2,listViewReadingPart3,listViewReadingPart4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_question);
        listViewReadingPractice = findViewById(R.id.listViewReadingPractice);
        listViewReadingPart1    = findViewById(R.id.listViewReadingPart1);
        listViewReadingPart2    = findViewById(R.id.listViewReadingPart2);
        listViewReadingPart3    = findViewById(R.id.listViewReadingPart3);
        listViewReadingPart4    = findViewById(R.id.listViewReadingPart4);
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        tokenCode = bb.getString("tokenCode", "tokenCode");
        String member_id = bb.getString("member_id", "member_id");
        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        API = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        practicetest = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=40&testcode="+test_code;
        part1 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=39&testcode="+test_code;
        part2 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=38&testcode="+test_code;
        part3 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=37&testcode="+test_code;
        part4 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=36&testcode="+test_code;
        connectionChecker();
//        Log.e("===APIREADING",API);
    }

    private void connectionChecker() {
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            sendRequest();
        }
        else
        {
            AlertDialog alertbox = new AlertDialog.Builder(this)
                    .setMessage("CHECK YOUR INTERNET CONNECTION?")
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
            Toast.makeText(ReadingTestQuestionActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitByBackKey() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("DO YOU WANT TO EXIT?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }

    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"LOADING","PLEASE WAIT!!",false,false);
        StringRequest stringRequest = new StringRequest(API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            String tokenCode = obj.getString("tdcode");
                            final String a = "http://demo.celpip.biz/api/testnewprogress?token="+tokenCode;
                            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("tokenCode", tokenCode);
                            edit.commit();
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "READING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                final ProgressDialog loading = ProgressDialog.show(ReadingTestQuestionActivity.this,"LOADING","PLEASE WAIT!!",false,false);
                                StringRequest stringRequest = new StringRequest(a,
                                        new com.android.volley.Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject obj = new JSONObject(response);
                                                    int abc = Integer.parseInt(obj.getString("response"));
                                                    if (abc !=1 )
                                                    {
                                                        loading.dismiss();
                                                        Toast.makeText(getApplicationContext(), "READING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if (abc == 1)
                                                    {
                                                        if (API.equals(part1))
                                                        {
                                                            listViewReadingPart1.setVisibility(View.VISIBLE);
                                                            loading.dismiss();
                                                            showJSONPart1(response);
//                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if(API.equals(part2))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart2(response);
                                                            listViewReadingPart2.setVisibility(View.VISIBLE);
//                                                            Log.e("===READINGPART2",a);
                                                        }
                                                        else if (API.equals(part3))
                                                        {
//                                                            Log.e("===READINGPART3",a);
                                                            listViewReadingPart3.setVisibility(View.VISIBLE);
                                                            loading.dismiss();
                                                            showJSONPart3(response);
                                                        }
                                                        else if (API.equals(part4))
                                                        {
//                                                            Log.e("===READINGPART4",a);
                                                            loading.dismiss();
                                                            showJSONPart4(response);
                                                            listViewReadingPart4.setVisibility(View.VISIBLE);
                                                        }
                                                        else
                                                        {
                                                            listViewReadingPractice.setVisibility(View.VISIBLE);
                                                            loading.dismiss();
                                                            showJSONPracticeTest(response);
                                                        }
                                                    }
                                                }
                                                catch (JSONException e) {
                                                    e.printStackTrace();
                                                    loading.dismiss();
                                                    Toast.makeText(getApplicationContext(), "Json Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(),"Response "+error.getMessage(), Toast.LENGTH_LONG).show();
                                                loading.dismiss();
                                            }
                                        });
                                RequestQueue requestQueue = Volley.newRequestQueue(ReadingTestQuestionActivity.this);
                                requestQueue.add(stringRequest);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Json Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSONPracticeTest(String json) {
        JsonDataHandlerPracticeTestReading jsonDataHandlerPracticeTestReading = new JsonDataHandlerPracticeTestReading(json);
        jsonDataHandlerPracticeTestReading.parseJSON();
        ReadingTestPracticeTestAdapter ca = new ReadingTestPracticeTestAdapter(this,JsonDataHandlerPracticeTestReading.id,JsonDataHandlerPracticeTestReading.test_code,JsonDataHandlerPracticeTestReading.question,JsonDataHandlerPracticeTestReading.question_title,JsonDataHandlerPracticeTestReading.option1,JsonDataHandlerPracticeTestReading.option2,JsonDataHandlerPracticeTestReading.option3,JsonDataHandlerPracticeTestReading.option4,JsonDataHandlerPracticeTestReading.answer);
        listViewReadingPractice.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart1(String json) {
        JsonDataHandlerReadingPart1 jsonDataHandlerReadingPart1 = new JsonDataHandlerReadingPart1(json);
        jsonDataHandlerReadingPart1.parseJSON();
        ReadingTestPart1QuestionAdapter ca = new ReadingTestPart1QuestionAdapter(this,JsonDataHandlerReadingPart1.id,JsonDataHandlerReadingPart1.test_code,JsonDataHandlerReadingPart1.passages,JsonDataHandlerReadingPart1.q1_question,JsonDataHandlerReadingPart1.q1_option1,JsonDataHandlerReadingPart1.q1_option2,JsonDataHandlerReadingPart1.q11_option3,JsonDataHandlerReadingPart1.q1_option4,JsonDataHandlerReadingPart1.q1_answerer,JsonDataHandlerReadingPart1.q2_question,JsonDataHandlerReadingPart1.q2_option1,JsonDataHandlerReadingPart1.q2_option2,JsonDataHandlerReadingPart1.q2_option3,JsonDataHandlerReadingPart1.q2_option4,JsonDataHandlerReadingPart1.q2_answerer,JsonDataHandlerReadingPart1.q3_question,JsonDataHandlerReadingPart1.q3_option1,JsonDataHandlerReadingPart1.q3_option2,JsonDataHandlerReadingPart1.q3_option3,JsonDataHandlerReadingPart1.q3_option4,JsonDataHandlerReadingPart1.q3_answerer,JsonDataHandlerReadingPart1.q4_question,JsonDataHandlerReadingPart1.q4_option1,JsonDataHandlerReadingPart1.q4_option2,JsonDataHandlerReadingPart1.q4_option3,JsonDataHandlerReadingPart1.q4_option4,JsonDataHandlerReadingPart1.q4_answerer,JsonDataHandlerReadingPart1.q5_question,JsonDataHandlerReadingPart1.q5_option1,JsonDataHandlerReadingPart1.q5_option2,JsonDataHandlerReadingPart1.q5_option3,JsonDataHandlerReadingPart1.q5_option4,JsonDataHandlerReadingPart1.q5_answerer,JsonDataHandlerReadingPart1.q6_question,JsonDataHandlerReadingPart1.q6_option1,JsonDataHandlerReadingPart1.q6_option2,JsonDataHandlerReadingPart1.q6_option3,JsonDataHandlerReadingPart1.q6_option4,JsonDataHandlerReadingPart1.q6_answerer,JsonDataHandlerReadingPart1.hi_message,JsonDataHandlerReadingPart1.q7_question,JsonDataHandlerReadingPart1.q7_option1,JsonDataHandlerReadingPart1.q7_option2,JsonDataHandlerReadingPart1.q7_option3,JsonDataHandlerReadingPart1.q7_option4,JsonDataHandlerReadingPart1.q7_answerer,JsonDataHandlerReadingPart1.q8_question,JsonDataHandlerReadingPart1.q8_option1,JsonDataHandlerReadingPart1.q8_option2,JsonDataHandlerReadingPart1.q8_option3,JsonDataHandlerReadingPart1.q8_option4,JsonDataHandlerReadingPart1.q8_answerer,JsonDataHandlerReadingPart1.q9_question,JsonDataHandlerReadingPart1.q9_option1,JsonDataHandlerReadingPart1.q9_option2,JsonDataHandlerReadingPart1.q9_option3,JsonDataHandlerReadingPart1.q9_option4,JsonDataHandlerReadingPart1.q9_answerer,JsonDataHandlerReadingPart1.q10_question,JsonDataHandlerReadingPart1.q10_option1,JsonDataHandlerReadingPart1.q10_option2,JsonDataHandlerReadingPart1.q10_option3,JsonDataHandlerReadingPart1.q10_option4,JsonDataHandlerReadingPart1.q10_answerer,JsonDataHandlerReadingPart1.q11_question,JsonDataHandlerReadingPart1.q11_option1,JsonDataHandlerReadingPart1.q11_option2,JsonDataHandlerReadingPart1.q11_option3,JsonDataHandlerReadingPart1.q11_option4,JsonDataHandlerReadingPart1.q11_answerer,JsonDataHandlerReadingPart1.thank_message);
        listViewReadingPart1.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart2(String json) {
        JsonDataHandlerReadingPart2 jsonDataHandlerReadingPart2 = new JsonDataHandlerReadingPart2(json);
        jsonDataHandlerReadingPart2.parseJSON();
        ReadingTestPart2QuestionAdapter ca = new ReadingTestPart2QuestionAdapter(this,JsonDataHandlerReadingPart2.id,JsonDataHandlerReadingPart2.test_code,JsonDataHandlerReadingPart2.image_1,JsonDataHandlerReadingPart2.image_2,JsonDataHandlerReadingPart2.image_3,JsonDataHandlerReadingPart2.image_4,JsonDataHandlerReadingPart2.q1_question,JsonDataHandlerReadingPart2.q1_option1,JsonDataHandlerReadingPart2.q1_option2,JsonDataHandlerReadingPart2.q1_option3,JsonDataHandlerReadingPart2.q1_option4,JsonDataHandlerReadingPart2.q1_answerer,JsonDataHandlerReadingPart2.q2_question,JsonDataHandlerReadingPart2.q2_option1,JsonDataHandlerReadingPart2.q2_option2,JsonDataHandlerReadingPart2.q2_option3,JsonDataHandlerReadingPart2.q2_option4,JsonDataHandlerReadingPart2.q2_answerer,JsonDataHandlerReadingPart2.q3_question,JsonDataHandlerReadingPart2.q3_option1,JsonDataHandlerReadingPart2.q3_option2,JsonDataHandlerReadingPart2.q3_option3,JsonDataHandlerReadingPart2.q3_option4,JsonDataHandlerReadingPart2.q3_answerer,JsonDataHandlerReadingPart2.q4_question,JsonDataHandlerReadingPart2.q4_option1,JsonDataHandlerReadingPart2.q4_option2,JsonDataHandlerReadingPart2.q4_option3,JsonDataHandlerReadingPart2.q4_option4,JsonDataHandlerReadingPart2.q4_answerer,JsonDataHandlerReadingPart2.q5_question,JsonDataHandlerReadingPart2.q5_option1,JsonDataHandlerReadingPart2.q5_option2,JsonDataHandlerReadingPart2.q5_option3,JsonDataHandlerReadingPart2.q5_option4,JsonDataHandlerReadingPart2.q5_answerer,JsonDataHandlerReadingPart2.q6_question,JsonDataHandlerReadingPart2.q6_option1,JsonDataHandlerReadingPart2.q6_option2,JsonDataHandlerReadingPart2.q6_option3,JsonDataHandlerReadingPart2.q6_option4,JsonDataHandlerReadingPart2.q6_answerer,JsonDataHandlerReadingPart2.q7_question,JsonDataHandlerReadingPart2.q7_option1,JsonDataHandlerReadingPart2.q7_option2,JsonDataHandlerReadingPart2.q7_option3,JsonDataHandlerReadingPart2.q7_option4,JsonDataHandlerReadingPart2.q7_answerer,JsonDataHandlerReadingPart2.q8_question,JsonDataHandlerReadingPart2.q8_option1,JsonDataHandlerReadingPart2.q8_option2,JsonDataHandlerReadingPart2.q8_option3,JsonDataHandlerReadingPart2.q8_option4,JsonDataHandlerReadingPart2.q8_answerer);
        listViewReadingPart2.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart3(String json) {
        JsonDataHandlerReadingPart3 jsonDataHandlerReadingPart3 = new JsonDataHandlerReadingPart3(json);
        jsonDataHandlerReadingPart3.parseJSON();
        ReadingTestPart3QuestionAdapter ca = new ReadingTestPart3QuestionAdapter(this,JsonDataHandlerReadingPart3.id,JsonDataHandlerReadingPart3.test_code,JsonDataHandlerReadingPart3.passages_1,JsonDataHandlerReadingPart3.passages_2,JsonDataHandlerReadingPart3.passages_3,JsonDataHandlerReadingPart3.passages_4,JsonDataHandlerReadingPart3.q1_question,JsonDataHandlerReadingPart3.q1_answerer,JsonDataHandlerReadingPart3.q2_question,JsonDataHandlerReadingPart3.q2_answerer,JsonDataHandlerReadingPart3.q3_question,JsonDataHandlerReadingPart3.q3_answerer,JsonDataHandlerReadingPart3.q4_question,JsonDataHandlerReadingPart3.q4_question,JsonDataHandlerReadingPart3.q5_question,JsonDataHandlerReadingPart3.q5_answerer,JsonDataHandlerReadingPart3.q6_question,JsonDataHandlerReadingPart3.q6_answerer,JsonDataHandlerReadingPart3.q7_question,JsonDataHandlerReadingPart3.q7_answerer,JsonDataHandlerReadingPart3.q8_question,JsonDataHandlerReadingPart3.q8_answerer,JsonDataHandlerReadingPart3.q9_question,JsonDataHandlerReadingPart3.q9_answerer);
        listViewReadingPart3.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart4(String json) {
        JsonDataHandlerReadingPart4 jsonDataHandlerReadingPart4 = new JsonDataHandlerReadingPart4(json);
        jsonDataHandlerReadingPart4.parseJSON();
        ReadingTestPart4QuestionAdapter ca = new ReadingTestPart4QuestionAdapter(this,JsonDataHandlerReadingPart4.id,JsonDataHandlerReadingPart4.test_code,JsonDataHandlerReadingPart4.passages,JsonDataHandlerReadingPart4.q1_question,JsonDataHandlerReadingPart4.q1_option1,JsonDataHandlerReadingPart4.q1_option2,JsonDataHandlerReadingPart4.q1_option3,JsonDataHandlerReadingPart4.q1_option4,JsonDataHandlerReadingPart4.q1_answerer,JsonDataHandlerReadingPart4.q2_question,JsonDataHandlerReadingPart4.q2_option1,JsonDataHandlerReadingPart4.q2_option2,JsonDataHandlerReadingPart4.q2_option3,JsonDataHandlerReadingPart4.q2_option4,JsonDataHandlerReadingPart4.q2_answerer,JsonDataHandlerReadingPart4.q3_question,JsonDataHandlerReadingPart4.q3_option1,JsonDataHandlerReadingPart4.q3_option2,JsonDataHandlerReadingPart4.q3_option3,JsonDataHandlerReadingPart4.q3_option4,JsonDataHandlerReadingPart4.q3_answerer,JsonDataHandlerReadingPart4.q4_question,JsonDataHandlerReadingPart4.q4_option1,JsonDataHandlerReadingPart4.q4_option2,JsonDataHandlerReadingPart4.q4_option3,JsonDataHandlerReadingPart4.q4_option4,JsonDataHandlerReadingPart4.q4_answerer,JsonDataHandlerReadingPart4.q5_question,JsonDataHandlerReadingPart4.q5_option1,JsonDataHandlerReadingPart4.q5_option2,JsonDataHandlerReadingPart4.q5_option3,JsonDataHandlerReadingPart4.q5_option4,JsonDataHandlerReadingPart4.q5_answerer,JsonDataHandlerReadingPart4.q6_question,JsonDataHandlerReadingPart4.q6_option1,JsonDataHandlerReadingPart4.q6_option2,JsonDataHandlerReadingPart4.q6_option3,JsonDataHandlerReadingPart4.q6_option4,JsonDataHandlerReadingPart4.q6_answerer,JsonDataHandlerReadingPart4.q7_question,JsonDataHandlerReadingPart4.q7_option1,JsonDataHandlerReadingPart4.q7_option2,JsonDataHandlerReadingPart4.q7_option3,JsonDataHandlerReadingPart4.q7_option4,JsonDataHandlerReadingPart4.q7_answerer,JsonDataHandlerReadingPart4.q8_question,JsonDataHandlerReadingPart4.q8_option1,JsonDataHandlerReadingPart4.q8_option2,JsonDataHandlerReadingPart4.q8_option3,JsonDataHandlerReadingPart4.q8_option4,JsonDataHandlerReadingPart4.q8_answerer,JsonDataHandlerReadingPart4.q9_question,JsonDataHandlerReadingPart4.q9_option1,JsonDataHandlerReadingPart4.q9_option2,JsonDataHandlerReadingPart4.q9_option3,JsonDataHandlerReadingPart4.q9_option4,JsonDataHandlerReadingPart4.q9_answerer,JsonDataHandlerReadingPart4.q10_question,JsonDataHandlerReadingPart4.q10_option1,JsonDataHandlerReadingPart4.q10_option2,JsonDataHandlerReadingPart4.q10_option3,JsonDataHandlerReadingPart4.q10_option4,JsonDataHandlerReadingPart4.q10_answerer);
        listViewReadingPart4.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
