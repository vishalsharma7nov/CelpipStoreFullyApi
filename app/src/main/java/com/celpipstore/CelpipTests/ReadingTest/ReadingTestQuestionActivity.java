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
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.ReadingTestAdapter.Question.ReadingTestPart1QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.Question.ReadingTestPart2QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.Question.ReadingTestPart3QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.Question.ReadingTestPart4QuestionAdapter;
import com.celpipstore.Adapter.ReadingTestAdapter.Question.ReadingTestPracticeTestAdapter;
import com.celpipstore.GetterAndSetterClasses.ReadingTest.Question.ReadingTestPart1;
import com.celpipstore.GetterAndSetterClasses.ReadingTest.Question.ReadingTestPart2;
import com.celpipstore.GetterAndSetterClasses.ReadingTest.Question.ReadingTestPart3;
import com.celpipstore.GetterAndSetterClasses.ReadingTest.Question.ReadingTestPart4;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerPracticeTestReading;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart1;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart2;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart3;
import com.celpipstore.JsonData.ReadingTest.JsonDataHandlerReadingPart4;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ReadingTestQuestionActivity extends AppCompatActivity {
    private List<ReadingTestPart1> readingTestPart1;
    private List<ReadingTestPart2> readingTestPart2;
    private List<ReadingTestPart3> readingTestPart3;
    private List<ReadingTestPart4> readingTestPart4;
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
        readingTestPart1=jsonDataHandlerReadingPart1.parseJSON();
        ReadingTestPart1QuestionAdapter ca = new ReadingTestPart1QuestionAdapter(this,readingTestPart1);
        listViewReadingPart1.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart2(String json) {
        JsonDataHandlerReadingPart2 jsonDataHandlerReadingPart2 = new JsonDataHandlerReadingPart2(json);
        readingTestPart2=jsonDataHandlerReadingPart2.parseJSON();
        ReadingTestPart2QuestionAdapter ca = new ReadingTestPart2QuestionAdapter(this,readingTestPart2);
        listViewReadingPart2.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart3(String json) {
        JsonDataHandlerReadingPart3 jsonDataHandlerReadingPart3 = new JsonDataHandlerReadingPart3(json);
        readingTestPart3=jsonDataHandlerReadingPart3.parseJSON();
        ReadingTestPart3QuestionAdapter ca = new ReadingTestPart3QuestionAdapter(this,readingTestPart3);
        listViewReadingPart3.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart4(String json) {
        JsonDataHandlerReadingPart4 jsonDataHandlerReadingPart4 = new JsonDataHandlerReadingPart4(json);
        readingTestPart4=jsonDataHandlerReadingPart4.parseJSON();
        ReadingTestPart4QuestionAdapter ca = new ReadingTestPart4QuestionAdapter(this,readingTestPart4);
        listViewReadingPart4.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
