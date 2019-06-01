package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class VocabularyTestQuestionsActivity extends AppCompatActivity {
    ListView listView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_test_questions);
        Intent intent = getIntent();
        String id = intent.getStringExtra("t1");
        url = "http://online.celpip.biz/api/vocabularyQuestion?lavelId="+id;
        listView =(ListView)findViewById(R.id.listViewVocabularyTestQuestionsList);
        sendRequest();



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
        VocabularyTestQuestionAdapter ca = new VocabularyTestQuestionAdapter(this,jsonHolderListing.id,jsonHolderListing.level_id, jsonHolderListing.title,jsonHolderListing.question_image,jsonHolderListing.options1,jsonHolderListing.options2,jsonHolderListing.options3,jsonHolderListing.options4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
