package com.celpipstore.Tests.ListeningTest;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.TotalTestListAdapter;
import com.celpipstore.GetterAndSetterClasses.TotalTestList;
import com.celpipstore.JsonData.JsonDataHandlerTestList;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListeningTestListActivity extends AppCompatActivity {
    protected String url;
    protected ListView listView;
    protected List<TotalTestList> totalTestLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_test_list);
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
            Toast.makeText(ListeningTestListActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
        Intent intent = getIntent();
        String id = intent.getStringExtra("t1");
        url = "http://online.celpip.biz/api/getTestList?testId="+id;
        listView = findViewById(R.id.listViewListeningTestListPart2);
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
                                loading.dismiss();
                                Toast.makeText(ListeningTestListActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                            }
                        }
                        catch (JSONException e)
                        {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Application Under Maintenance!!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
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
    private void showJSON(String json) {
        JsonDataHandlerTestList jsonHolderListing = new JsonDataHandlerTestList(json);
        totalTestLists=jsonHolderListing.parseJSON();
        TotalTestListAdapter ca = new TotalTestListAdapter(this,totalTestLists);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}