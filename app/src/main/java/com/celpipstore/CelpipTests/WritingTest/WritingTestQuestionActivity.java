package com.celpipstore.CelpipTests.WritingTest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.celpipstore.Adapter.WritingTestAdapter.Question.WritingTestTask1Adapter;
import com.celpipstore.Adapter.WritingTestAdapter.Question.WritingTestTask2Adapter;
import com.celpipstore.GetterAndSetterClasses.WritingTest.Question.WritingTask1;
import com.celpipstore.GetterAndSetterClasses.WritingTest.Question.WritingTask2;
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
        option_selected = bb.getString("option_selected","option_selected");
        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        API = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        task1 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=47&testcode="+test_code;
        task2 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=46&testcode="+test_code;
        connectionChecker();
        buttonSubmitWritingTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswerTask1();
            }
        });
        buttonSubmitWritingTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswerTask2();
            }
        });
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
                    .setIcon(R.drawable.warning)
                    .setMessage("CHECK YOUR INTERNET CONNECTION?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {
//                            Intent intent = new Intent();
//                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
//                            startActivity(intent);
                            recreate();
                        }
                    })
                    .show();
            Toast.makeText(getApplicationContext(), "INTERNET IS NOT WORKING PROPERLY!!", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(getApplicationContext(), "WRITING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
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
                                                        Toast.makeText(getApplicationContext(), "WRITING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if (abc == 1)
                                                    {
                                                        if (API.equals(task1))
                                                        {
                                                            loading.dismiss();
                                                            relativeLayoutwritingtask1.setVisibility(View.VISIBLE);
                                                            showJSONWritingTask1(response);
                                                        }
                                                        else if(API.equals(task2))
                                                        {
                                                            loading.dismiss();
                                                            relativeLayoutwritingtask2.setVisibility(View.VISIBLE);
                                                            showJSONWritingTask2(response);
                                                        }
                                                    }
                                                }
                                                catch (JSONException e) {
                                                    e.printStackTrace();
                                                    loading.dismiss();
                                                    Toast.makeText(getApplicationContext(), "Error Json Exception!!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(),"Error!!"+error.getMessage(), Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getApplicationContext(), "Error Json Exception!!"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        final ProgressDialog loading = ProgressDialog.show(this,"","PLEASE WAIT!!",false,false);
        final String writing_task1_answer = editTextAnswer.getText().toString();
        final String urlForSubmittingOptions = "http://demo.celpip.biz/api/wtPart1Submit?token="+tokenCode+"&q1_response="+writing_task1_answer+"&memberid="+member_id;
        if (writing_task1_answer.isEmpty())
        {
            Toast.makeText(this, "PLEASE TYPE THE ANSWER!!!", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        }
        else
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try
                            {
                                JSONObject obj = new JSONObject(response);
                                String result = obj.getString("result");
                                if (result.equals("success"))
                                {
                                    loading.dismiss();
                                    Toast.makeText(getApplicationContext(), "Answer Submitted!!"+response, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), WRITING.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    loading.dismiss();
                                    Toast.makeText(getApplicationContext(), "Answer Submission Failed!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Answer Submission Failed!!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!error.getMessage().isEmpty() || !error.networkResponse.allHeaders.isEmpty())
                    {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Answer Submission Failed!!"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        final AlertDialog alertbox = new AlertDialog.Builder(getApplicationContext())
                                .setIcon(R.drawable.warning)
                                .setTitle("INTERNET NOT WORKING!!")
                                .setMessage("PLEASE CHECK YOUR INTERNET?")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    // do something when the button is clicked
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                    }
                                })
                                .show();
                    }
                    else
                    {
                        loading.dismiss();
                        final AlertDialog alertbox = new AlertDialog.Builder(getApplicationContext())
                                .setIcon(R.drawable.warning)
                                .setTitle("INTERNET NOT WORKING!!")
                                .setMessage("PLEASE CHECK YOUR INTERNET?")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    // do something when the button is clicked
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                    }
                                })
                                .show();
                    }
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
        }
    }
    private void submitAnswerTask2(){
        final ProgressDialog loading = ProgressDialog.show(this,"","PLEASE WAIT!!",false,false);
        final String writing_task2_answer =  editTextAnswer.getText().toString();
        if (writing_task2_answer.isEmpty())
        {
            Toast.makeText(this, "Please type the answer!!!", Toast.LENGTH_SHORT).show();
            loading.dismiss();
        }
        else
        {
            final String urlForSubmittingOptions = "http://demo.celpip.biz/api/wtPart2Submit?token="+tokenCode+"&q1_response="+writing_task2_answer+"&memberid="+member_id;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try
                            {
                                JSONObject obj = new JSONObject(response);
                                String result = obj.getString("result");
                                if (result.equals("success"))
                                {
                                    loading.dismiss();
                                    Toast.makeText(getApplicationContext(), "Answer Submitted!!"+response, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), WRITING.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    loading.dismiss();
                                    Toast.makeText(getApplicationContext(), "Answer Submission Failed!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Answer Submission Failed!!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!error.getMessage().isEmpty() || !error.networkResponse.allHeaders.isEmpty())
                    {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error Submission Failed!!"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        loading.dismiss();
                    }
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
        }
    }
}
