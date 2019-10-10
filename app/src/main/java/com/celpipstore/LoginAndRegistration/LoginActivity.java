package com.celpipstore.LoginAndRegistration;

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
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.ExpandableListAdapter;
import com.celpipstore.HOME;
import com.celpipstore.R;
import com.celpipstore.StaticActivity.AboutUS;
import com.celpipstore.StaticActivity.CONTACTUS;
import com.celpipstore.StaticActivity.FAQ;
import com.celpipstore.StaticActivity.OurFeature;
import com.celpipstore.StaticActivity.WHYUS;
import com.celpipstore.UserDashboard.Dashboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected EditText username,password;
    protected Button login;
    protected ExpandableListAdapter listAdapter;
    protected ExpandableListView expListView;
    protected List<String> listDataHeader;
    protected HashMap<String, List<String>> listDataChild;
    protected SessionManager session;
    protected String mUsername,mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Toast.makeText(LoginActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
        //---------------------------------------------------------------------------------//
        expListView = findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if(groupPosition == 0 )
                {
                    Intent intent = new Intent(LoginActivity.this, HOME.class);
                    startActivity(intent);
                }
                else if(groupPosition == 1 )
                {
                }
                else if(groupPosition == 2 )
                {
                    Intent intent = new Intent(LoginActivity.this, WHYUS.class);
                    startActivity(intent);
                }
                else if(groupPosition == 3 )
                {
                    Intent intent = new Intent(LoginActivity.this, OurFeature.class);
                    startActivity(intent);
                }
                else if(groupPosition == 4 )
                {
                    Intent intent = new Intent(LoginActivity.this, FAQ.class);
                    startActivity(intent);
                }
                else if(groupPosition == 5 )
                {
                    Intent intent = new Intent(LoginActivity.this, AboutUS.class);
                    startActivity(intent);
                }
                else if(groupPosition == 6 )
                {
                    Intent intent = new Intent(LoginActivity.this, CONTACTUS.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if(childPosition == 0)
                {
                    Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 1)
                {
                    Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
//------------------------------------------------------------------------------//
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                saveLoginInformation();
            }
        });
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        mUsername = user.get(SessionManager.KEY_USERNAME);
        mPassword = user.get(SessionManager.KEY_PASSWORD);
    }

    private void saveLoginInformation() {
        String Username = username.getText().toString();
        String Password = username.getText().toString();
        if (Username.isEmpty()) {
            username.setError("Name required");
            username.requestFocus();
            return;
        }
        else if(Password.isEmpty())
        {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }
        else
        {
        }
    }

    public void login() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        final String Username = username.getText().toString();
        final String Password = password.getText().toString();
        final String url = "http://online.celpip.biz/api/login?password="+Password+"&username="+Username;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            if (abc == 1)
                            {
                                String member_id = obj.getJSONObject("").getString("id");
                                String coins = obj.getJSONObject("").getString("coins");
                                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                intent.putExtra("username",Username);
                                intent.putExtra("password",Password);
                                intent.putExtra("member_id",member_id);
                                session.createLoginSession(Username, Password);
                                startActivity(intent);
                                SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                SharedPreferences.Editor edit = prefs.edit();
                                edit.putString("username", username.getText().toString());
                                edit.putString("password", password.getText().toString());
                                edit.putString("member_id", member_id);
                                edit.putString("coins", coins);
                                edit.commit();
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, "hello"+response, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Application Under Maintenance!!"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("===exceptions",e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Application Under Maintenance!!"+error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("===exceptions",url);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", Username);
                params.put("password", Password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            moveTaskToBack(true);

        } else {
            super.onBackPressed();
            moveTaskToBack(true);
        }
    }
    protected void exitByBackKey() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to quit the App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.login)
        {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.home)
        {
            Intent intent = new Intent(LoginActivity.this,HOME.class);
            startActivity(intent);
        }
        else if (id == R.id.whyus)
        {
            Intent intent = new Intent(LoginActivity.this,WHYUS.class);
            startActivity(intent);
        }
        else if (id == R.id.faq)
        {
            Intent intent = new Intent(LoginActivity.this,FAQ.class);
            startActivity(intent);
        }
        else if (id == R.id.ourfeatures)
        {
            Intent intent = new Intent(LoginActivity.this,OurFeature.class);
            startActivity(intent);
        }
        else if (id == R.id.aboutus)
        {
            Intent intent = new Intent(LoginActivity.this,AboutUS.class);
            startActivity(intent);
        }
        else if (id == R.id.contactus)
        {
            Intent intent = new Intent(LoginActivity.this,CONTACTUS.class);
            startActivity(intent);
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void prepareListData() {
           listDataHeader = new ArrayList<>();
           listDataChild = new HashMap<>();
           // Adding child data
           listDataHeader.add("HOME");
           listDataHeader.add("LOGIN AND REGISTER");
           listDataHeader.add("WHY US");
           listDataHeader.add("OUR FEATURES");
           listDataHeader.add("FAQ");
           listDataHeader.add("ABOUT US");
           listDataHeader.add("CONTACT US");
           // Adding child data
           List<String> HOME       = new ArrayList<>();
           List<String> LOGIN      = new ArrayList<>();
           LOGIN.add("LOGIN");
           LOGIN.add("REGISTER");
           List<String> WHYUS  = new ArrayList<>();
           List<String> OUR_FEATURES= new ArrayList<>();
           List<String> FAQ        = new ArrayList<>();
           List<String> ABOUT_US      = new ArrayList<>();
           List<String> CONTACT_US = new ArrayList<>();
           listDataChild.put(listDataHeader.get(0), HOME);
           listDataChild.put(listDataHeader.get(1), LOGIN);
           listDataChild.put(listDataHeader.get(2), WHYUS);
           listDataChild.put(listDataHeader.get(3), OUR_FEATURES);
           listDataChild.put(listDataHeader.get(4), FAQ);
           listDataChild.put(listDataHeader.get(5), ABOUT_US);
           listDataChild.put(listDataHeader.get(6), CONTACT_US);
    }
}
