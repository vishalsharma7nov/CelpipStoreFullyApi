package com.celpipstore;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText username,password;
    Button login;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
//            Toast.makeText(DASHBOARD.this, "Network Available", Toast.LENGTH_LONG).show();
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
            Toast.makeText(MainActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();

        }


//        prefManager = new PrefManager(this);
//        if (!prefManager.isFirstTimeLaunch()) {
//            launchHomeScreen();
//            finish();
//        }

        //---------------------------------------------------------------------------------//

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                if(groupPosition == 0 )
                {
                    Intent intent = new Intent(MainActivity.this,HOME.class);
                    startActivity(intent);

                }
                else if(groupPosition == 1 )
                {

                }

                else if(groupPosition == 2 )
                {

                    Intent intent = new Intent(MainActivity.this,WHYUS.class);
                    startActivity(intent);
                }


                else if(groupPosition == 3 )
                {
                    Intent intent = new Intent(MainActivity.this,OurFeature.class);
                    startActivity(intent);

                }
                else if(groupPosition == 4 )
                {
                    Intent intent = new Intent(MainActivity.this,FAQ.class);
                    startActivity(intent);

                }
                else if(groupPosition == 5 )
                {
                    Intent intent = new Intent(MainActivity.this,AboutUS.class);
                    startActivity(intent);


                }
                else if(groupPosition == 6 )
                {
                    Intent intent = new Intent(MainActivity.this,CONTACTUS.class);
                    startActivity(intent);

                }

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if(childPosition == 0)
                {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 1)
                {
                    Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                    startActivity(intent);
                }


                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });



//------------------------------------------------------------------------------//

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
//    private void launchHomeScreen() {
//        prefManager.setFirstTimeLaunch(true);
//        startActivity(new Intent(MainActivity.this, Dashboard.class));
//        finish();
//    }

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
                                String id          = obj.getJSONObject("").getString("id");
//                                Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();

//                                prefManager.setFirstTimeLaunch(false);

                                Intent intent = new Intent(MainActivity.this,Dashboard.class);
                                intent.putExtra("username",Username);
                                intent.putExtra("password",Password);
                                intent.putExtra("member_id",id);
                                startActivity(intent);
                                Log.e("===exceptions",response);
                                loading.dismiss();
                                Toast.makeText(MainActivity.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                loading.dismiss();
                                Toast.makeText(MainActivity.this, "hello"+response, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("===exceptions",e.getMessage());
                        }


                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error"+error.getMessage(), Toast.LENGTH_LONG).show();
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
                        //close();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
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

            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);

        }
        else if(id == R.id.home)
        {

            Intent intent = new Intent(MainActivity.this,HOME.class);
            startActivity(intent);


        }

        else if (id == R.id.whyus)
        {

            Intent intent = new Intent(MainActivity.this,WHYUS.class);
            startActivity(intent);

        }
        else if (id == R.id.faq)
        {
            Intent intent = new Intent(MainActivity.this,FAQ.class);
            startActivity(intent);

        }

        else if (id == R.id.ourfeatures)
        {
            Intent intent = new Intent(MainActivity.this,OurFeature.class);
            startActivity(intent);

        }
        else if (id == R.id.aboutus)
        {
            Intent intent = new Intent(MainActivity.this,AboutUS.class);
            startActivity(intent);

        }
        else if (id == R.id.contactus)
        {
            Intent intent = new Intent(MainActivity.this,CONTACTUS.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//        ----------------------------------------------------------------------------------------  //


//        ----------------------------------------------------------------------------------------  //
public void prepareListData() {
    listDataHeader = new ArrayList<String>();
    listDataChild = new HashMap<String, List<String>>();

    // Adding child data
    listDataHeader.add("HOME");
    listDataHeader.add("LOGIN AND REGISTER");
    listDataHeader.add("WHY US");
    listDataHeader.add("OUR FEATURES");
    listDataHeader.add("FAQ");
    listDataHeader.add("ABOUT US");
    listDataHeader.add("CONTACT US");



    // Adding child data

    List<String> HOME       = new ArrayList<String>();
    List<String> LOGIN      = new ArrayList<String>();
    LOGIN.add("LOGIN");
    LOGIN.add("REGISTER");
    List<String> WHYUS  = new ArrayList<String>();
    List<String> OUR_FEATURES= new ArrayList<String>();
    List<String> FAQ        = new ArrayList<String>();
    List<String> ABOUT_US      = new ArrayList<String>();
    List<String> CONTACT_US = new ArrayList<String>();



    listDataChild.put(listDataHeader.get(0), HOME); // Header, Child data
    listDataChild.put(listDataHeader.get(1), LOGIN);
    listDataChild.put(listDataHeader.get(2), WHYUS);
    listDataChild.put(listDataHeader.get(3), OUR_FEATURES);
    listDataChild.put(listDataHeader.get(4), FAQ);
    listDataChild.put(listDataHeader.get(5), ABOUT_US);
    listDataChild.put(listDataHeader.get(6), CONTACT_US);


}

}
