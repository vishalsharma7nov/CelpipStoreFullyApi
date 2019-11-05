package com.celpipstore.CelpipTests.ListeningTest;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.ListeningTestAdapter.Answer.ListeningTestPart1AnswerAdapter;
import com.celpipstore.GetterAndSetterClasses.ListeningTest.Answer.ListeningPart1Answer;
import com.celpipstore.JsonData.ListeningTest.Answers.JsonDataHandlerListeningPart1Answer;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListeningTestAnswerActivity extends AppCompatActivity {
    protected List<ListeningPart1Answer> listeningPart1Answer;
    protected String API;
    protected ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listesing_test_part1_answer);
        listView = findViewById(R.id.listView);
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String member_id = bb.getString("member_id", "member_id");
        String test_id = bb.getString("test_id","test_id");
        String test_code = bb.getString("test_code","test_code");
        API ="http://demo.celpip.biz/api/viewListenReport?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        sendRequest();
        Log.e("===APIAnswer",API);
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"LOADING","PLEASE WAIT!!",false,false);
        StringRequest stringRequest = new StringRequest(API,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            if (abc !=1 )
                            {
                                loading.dismiss();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json) {
        JsonDataHandlerListeningPart1Answer jsonDataHandlerListeningPart1Answer = new JsonDataHandlerListeningPart1Answer(json);
        listeningPart1Answer=jsonDataHandlerListeningPart1Answer.parseJSON();
        ListeningTestPart1AnswerAdapter ca = new ListeningTestPart1AnswerAdapter(this,listeningPart1Answer);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
