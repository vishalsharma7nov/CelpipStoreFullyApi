package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextEmailId,editTextUsername,editTextCoupons;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextEmailId   = (EditText)findViewById(R.id.editTextEmialId);
        editTextUsername  = (EditText)findViewById(R.id.editTextUsername);
        editTextCoupons   = (EditText)findViewById(R.id.editTextRefer);

        buttonRegister = (Button)findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });

    }

    public void registration() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        final String email = editTextEmailId.getText().toString();
        final String username = editTextUsername.getText().toString();
        final String coupons = editTextCoupons.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://online.celpip.biz/api/register",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));

                            if (abc == 1)
                            {
                                loading.dismiss();
                                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegistrationActivity.this, "Registration Successful!!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
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
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("username", username);
                params.put("reference_no", coupons);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
