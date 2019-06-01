package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
    Button button;
    TextView textViewQuestion,textViewOption1,textViewOption2,textViewOption3,textViewOption4;
    String url;
    int position =0;

    List<String> ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_test_questions);

        ans = new ArrayList<>();
        textViewQuestion = (TextView)findViewById(R.id.question);
        textViewOption1 = findViewById(R.id.option1);
        textViewOption2 = findViewById(R.id.option2);
        textViewOption3 = findViewById(R.id.option3);
        textViewOption4 = findViewById(R.id.option4);

        Intent intent = getIntent();
        String id = intent.getStringExtra("t1");
        url = "http://online.celpip.biz/api/vocabularyQuestion?lavelId="+id;
        Log.d("url", url);
//        listView =(ListView)findViewById(R.id.listViewVocabularyTestQuestionsList);
        sendRequest();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = ++position;
                setData(p);
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
//        VocabularyTestQuestionAdapter ca = new VocabularyTestQuestionAdapter(this,jsonHolderListing.id,jsonHolderListing.level_id, jsonHolderListing.title,jsonHolderListing.question_image,jsonHolderListing.options1,jsonHolderListing.options2,jsonHolderListing.options3,jsonHolderListing.options4);
//        listView.setAdapter(ca);
//        ca.notifyDataSetChanged();
    }

    private void setData(int position) {
        textViewQuestion.setText(JsonDataHandlerVocabularyTestQuestionList.title[position]);
        textViewOption1.setText(JsonDataHandlerVocabularyTestQuestionList.options1[position]);
        textViewOption2.setText(JsonDataHandlerVocabularyTestQuestionList.options2[position+1]);
        textViewOption3.setText(JsonDataHandlerVocabularyTestQuestionList.options3[position+2]);
        textViewOption4.setText(JsonDataHandlerVocabularyTestQuestionList.options4[position+3]);
    }
}
