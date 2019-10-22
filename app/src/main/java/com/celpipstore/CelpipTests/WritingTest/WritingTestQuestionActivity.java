package com.celpipstore.CelpipTests.WritingTest;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.WritingTestAdapter.WritingTestTask1Adapter;
import com.celpipstore.Adapter.WritingTestAdapter.WritingTestTask2Adapter;
import com.celpipstore.CelpipTests.SpeakingTest.SPEAKING;
import com.celpipstore.GetterAndSetterClasses.WritingTest.WritingTask1;
import com.celpipstore.GetterAndSetterClasses.WritingTest.WritingTask2;
import com.celpipstore.JsonData.WritingTest.JsonDataHandlerWritingTask1;
import com.celpipstore.JsonData.WritingTest.JsonDataHandlerWritingTask2;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WritingTestQuestionActivity extends AppCompatActivity {
    private List<WritingTask1> writingTask1;
    private List<WritingTask2> writingTask2;
    private ListView listViewWritingTask1,listViewWritingTask2;
    private String API,tokenCode,task1,task2,member_id,option_selected;
    private EditText editTextAnswer;
    private RelativeLayout relativeLayoutwritingtask1,relativeLayoutwritingtask2;
    private Button buttonSubmitWritingTask1,buttonSubmitWritingTask2;
    WritingTestTask2Adapter writingTestTask2Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_test_question);
        listViewWritingTask1 = findViewById(R.id.listViewWritingTask1);
        listViewWritingTask2 = findViewById(R.id.listViewWritingTask2);
        editTextAnswer = findViewById(R.id.writingtask1_answer);
        relativeLayoutwritingtask1 = findViewById(R.id.relativeLayoutwritingtask1);
        relativeLayoutwritingtask1.setVisibility(View.GONE);
        relativeLayoutwritingtask2 = findViewById(R.id.relativeLayoutwritingtask2);
        relativeLayoutwritingtask2.setVisibility(View.GONE);
        buttonSubmitWritingTask1 = findViewById(R.id.buttonSubmitWritingTask1);
        buttonSubmitWritingTask2 = findViewById(R.id.buttonSubmitWritingTask2);
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        tokenCode = bb.getString("tokenCode", "tokenCode");
        member_id = bb.getString("member_id", "member_id");
        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        API = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        task1 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=47&testcode="+test_code;
        task2 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=46&testcode="+test_code;
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
            Toast.makeText(getApplicationContext(), "Network Not Available", Toast.LENGTH_LONG).show();
        }
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
                                final ProgressDialog loading = ProgressDialog.show(WritingTestQuestionActivity.this,"LOADING","PLEASE WAIT!!",false,false);
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
                                                        if (API.equals(task1))
                                                        {
                                                            loading.dismiss();
                                                            relativeLayoutwritingtask1.setVisibility(View.VISIBLE);
                                                            showJSONWritingTask1(response);
                                                            buttonSubmitWritingTask1.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    submitAnswerTask1();
                                                                }
                                                            });
                                                        }
                                                        else if(API.equals(task2))
                                                        {
                                                            loading.dismiss();
                                                            relativeLayoutwritingtask2.setVisibility(View.VISIBLE);
                                                            showJSONWritingTask2(response);
                                                            buttonSubmitWritingTask2.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    submitAnswerTask2();
                                                                }
                                                            });
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
                                RequestQueue requestQueue = Volley.newRequestQueue(WritingTestQuestionActivity.this);
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
    private void showJSONWritingTask1(String json) {
        JsonDataHandlerWritingTask1 jsonDataHandlerWritingTask1 = new JsonDataHandlerWritingTask1(json);
        writingTask1=jsonDataHandlerWritingTask1.parseJSON();
        WritingTestTask1Adapter ca = new WritingTestTask1Adapter(this,writingTask1);
        listViewWritingTask1.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONWritingTask2(String json) {
        JsonDataHandlerWritingTask2 jsonDataHandlerWritingTask2 = new JsonDataHandlerWritingTask2(json);
        writingTask2=jsonDataHandlerWritingTask2.parseJSON();
        WritingTestTask2Adapter ca = new WritingTestTask2Adapter(this,writingTask2);
        listViewWritingTask2.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void submitAnswerTask1 (){
        final String writing_task1_answer = editTextAnswer.getText().toString();
        final String urlForSubmittingOptions = "http://demo.celpip.biz/api/wtPart1Submit?token="+tokenCode+"&q1_response="+writing_task1_answer+"&memberid="+member_id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        Log.e("===Answer",urlForSubmittingOptions);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("===ErrorAnswer",urlForSubmittingOptions);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("q1_response", writing_task1_answer);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Intent intent = new Intent(getApplicationContext(), SPEAKING.class);
        startActivity(intent);
    }
    private void submitAnswerTask2(){
        final String writing_task2_answer = editTextAnswer.getText().toString();
//        option_selected = writingTestTask2Adapter.answer;
//        Log.e("===Answer",writingTestTask2Adapter.answer);
        final String urlForSubmittingOptions = "http://demo.celpip.biz/api/wtPart2Submit?token="+tokenCode+"&q1_response="+writing_task2_answer+"&memberid="+member_id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        Log.e("===Answer",urlForSubmittingOptions);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("===ErrorAnswer",urlForSubmittingOptions);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("option_selected", option_selected);
                params.put("q1_response", writing_task2_answer);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
//        Intent intent = new Intent(getApplicationContext(), SPEAKING.class);
//        startActivity(intent);
    }
}
