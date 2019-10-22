package com.celpipstore.CelpipTests.WritingTest;

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
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTotalTestListAdapter;
import com.celpipstore.Adapter.WritingTestAdapter.WritingTotalTestListAdapter;
import com.celpipstore.GetterAndSetterClasses.TotalTestList;
import com.celpipstore.JsonData.JsonDataHandlerTestList;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WritingTestListActivity extends AppCompatActivity {
    protected List<TotalTestList> totalTestLists;
    protected String url;
    protected ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_list);
        Intent intent = getIntent();
        String id = intent.getStringExtra("t1");
        url = "http://demo.celpip.biz/api/getTestList?testId="+id;
        listView = findViewById(R.id.listViewReadingTestList);
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
                                Toast.makeText(WritingTestListActivity.this, "Application Maintance in Progress....", Toast.LENGTH_SHORT).show();
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
        JsonDataHandlerTestList jsonHolderListing = new JsonDataHandlerTestList(json);
        totalTestLists=jsonHolderListing.parseJSON();
        WritingTotalTestListAdapter ca = new WritingTotalTestListAdapter(this,totalTestLists);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}