package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class ListeningTestQuestionActivity extends AppCompatActivity {

    ListView listView;
    String url;

    ImageView imageViewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_test_question);
        listView = (ListView)findViewById(R.id.listView);
        imageViewQuestion = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        String test_id = intent.getStringExtra("t1");
        String test_code = intent.getStringExtra("t2");
        Log.e("==code",test_code);
        url = "http://online.celpip.biz/api/accessnewtest?memberid=41&testid=19&testcode="+test_code;

        sendRequest();
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ListeningTestQuestionActivity.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            String tokenCode = obj.getString("tdcode");
                            String a = "http://online.celpip.biz/api/testnewprogress?token="+tokenCode;
                            Log.e("===a",a);
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(ListeningTestQuestionActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                Toast.makeText(ListeningTestQuestionActivity.this, url, Toast.LENGTH_SHORT).show();
                                final ProgressDialog loading = ProgressDialog.show(ListeningTestQuestionActivity.this,"Loading","Please wait...",false,false);
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
                                                        Toast.makeText(ListeningTestQuestionActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if (abc == 1)
                                                    {
                                                        String imageFile = "main_image3.jpg";
                                                        String imageUrl = "https://online.celpip.biz/uploads/part1_listening/"+imageFile;
                                                        Log.e("===image",imageUrl);
                                                        Glide
                                                                .with(ListeningTestQuestionActivity.this)
                                                                .load(imageUrl)
                                                                .centerCrop()
                                                                .into(imageViewQuestion);
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
                                                loading.dismiss();
                                            }
                                        });

                                RequestQueue requestQueue = Volley.newRequestQueue(ListeningTestQuestionActivity.this);
                                requestQueue.add(stringRequest);
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
        JsonDataHandlerListeningPart2 jsonHolderListing = new JsonDataHandlerListeningPart2(json);
        jsonHolderListing.parseJSON();
//        ListeningTestListAdapter ca = new ListeningTestListAdapter(this,jsonHolderListing.id,jsonHolderListing.test_code);
//        listView.setAdapter(ca);
//        ca.notifyDataSetChanged();
    }

}
