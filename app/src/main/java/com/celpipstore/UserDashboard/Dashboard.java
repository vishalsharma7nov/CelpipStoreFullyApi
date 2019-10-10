package com.celpipstore.UserDashboard;

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
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.ExpandableListAdapter;
import com.celpipstore.DICTATION_TESTS;
import com.celpipstore.FILL_IN_THE_BLANKS;
import com.celpipstore.LISTENING_part1;
import com.celpipstore.LoginAndRegistration.SessionManager;
import com.celpipstore.Tests.LISTENING_part2;
import com.celpipstore.LoginAndRegistration.LoginActivity;
import com.celpipstore.R;
import com.celpipstore.Tests.READING;
import com.celpipstore.RE_ARRANGE;
import com.celpipstore.Tests.SPEAKING;
import com.celpipstore.SPOTTING_THE_ERRORS;
import com.celpipstore.Tests.VOCABULARY_TESTS;
import com.celpipstore.Tests.WRITING;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected ExpandableListAdapter listAdapter;
    protected ExpandableListView expListView;
    protected List<String> listDataHeader;
    protected HashMap<String, List<String>> listDataChild;
    protected TextView textViewTotalCoins;
    protected String username;
    protected String password;
    protected String member_id;
    protected String id,total_coins;
    protected ImageView vocabularyimage,spottingerrorimage,dictationimage,comprehensionimage,rearrangeimage,fillintheblanksimage,listeningimage,readingimage,writingimage,speakingimage;
    protected SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

        }

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        member_id = intent.getStringExtra("member_id");
        textViewTotalCoins = (TextView)findViewById(R.id.textViewTotalCoins);
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("member_id", member_id);
        edit.commit();
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                login();
                handler.postDelayed(this,1000);//10 second delay
            }
        };handler.postDelayed(runnable,1000);

//-----------------------------------------------------------------------------------------//

        vocabularyimage      = (ImageView)findViewById(R.id.vocabularyimage);

        vocabularyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, VOCABULARY_TESTS.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);

                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
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
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }
            }
        });
        spottingerrorimage   = (ImageView)findViewById(R.id.spottingerrorimage);
        spottingerrorimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, SPOTTING_THE_ERRORS.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);

                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
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
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }
            }
        });
        dictationimage       = (ImageView)findViewById(R.id.dictationimage);
        dictationimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, DICTATION_TESTS.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
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
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }
            }
        });
        comprehensionimage   = (ImageView)findViewById(R.id.comprehensionimage);
        comprehensionimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rearrangeimage       = (ImageView)findViewById(R.id.rearrangeimage);
        rearrangeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, RE_ARRANGE.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
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
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }

            }
        });
        fillintheblanksimage = (ImageView)findViewById(R.id.fillintheblanksimage);
        fillintheblanksimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, FILL_IN_THE_BLANKS.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
                            .setMessage("Check Your Internet Connention?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                // do something when the button is clicked
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                                    startActivity(intent);
                                    recreate();
//                        //close();


                                }
                            })
                            .show();
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }
            }
        });
        listeningimage       = (ImageView)findViewById(R.id.listeningimage);
        listeningimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, LISTENING_part2.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
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
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }

            }
        });
        readingimage         = (ImageView)findViewById(R.id.readingimage);
        readingimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, READING.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
                            .setMessage("Check Your Internet Connention?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                // do something when the button is clicked
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                                    startActivity(intent);
                                    recreate();
//                        //close();
                                }
                            })
                            .show();
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }

            }
        });
        speakingimage        = (ImageView)findViewById(R.id.speakingimage);
        speakingimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, SPEAKING.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
                            .setMessage("Check Your Internet Connention?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                // do something when the button is clicked
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                                    startActivity(intent);
                                    recreate();
//                        //close();
                                }
                            })
                            .show();
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }

            }
        });
        writingimage         = (ImageView)findViewById(R.id.writingimage);
        writingimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(Dashboard.this, WRITING.class);
                    intent.putExtra("member_id",member_id);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(Dashboard.this)
                            .setMessage("Check Your Internet Connention?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                // do something when the button is clicked
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                                    startActivity(intent);
                                    recreate();
//                        //close();
                                }
                            })
                            .show();
                    Toast.makeText(Dashboard.this, "Network Not Available", Toast.LENGTH_LONG).show();

                }
            }
        });



//-----------------------------------------------------------------------------------------//

                    //expandable list


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
                    Intent intent = new Intent(Dashboard.this,Dashboard.class);
                    startActivity(intent);

                }
                else if(groupPosition == 1 )
                {
                    Intent intent = new Intent(Dashboard.this,EARN_FEE_COINS.class);
                    startActivity(intent);

                }

                else if(groupPosition == 2 )
                {


                }
                else if(groupPosition == 3 )
                {


                }

                else if(groupPosition == 4 )
                {


                }
                else if(groupPosition == 5 )
                {

                }
                else if(groupPosition == 6 )
                {


                }
                else if(groupPosition == 7 )
                {
                    Intent intent = new Intent(Dashboard.this,COIN_MANAGEMENT.class);
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

        if (groupPosition == 2) {
            if (childPosition == 0) {
                Intent intent = new Intent(Dashboard.this, VOCABULARY_TESTS.class);
                startActivity(intent);
            } else if (childPosition == 1) {
                Intent intent = new Intent(Dashboard.this, SPOTTING_THE_ERRORS.class);
                startActivity(intent);
            } else if (childPosition == 2) {
                Intent intent = new Intent(Dashboard.this, DICTATION_TESTS.class);
                startActivity(intent);
            }
            else if (childPosition ==3)
            {
                Intent intent = new Intent(Dashboard.this,RE_ARRANGE.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(Dashboard.this,FILL_IN_THE_BLANKS.class);
                startActivity(intent);
            }
        }


        if(groupPosition==3)
        {
            if (childPosition==0)
            {
                Intent intent = new Intent(Dashboard.this, LISTENING_part1.class);
                startActivity(intent);
            }
        }
        if (groupPosition==4)
        {
            if(childPosition==0)
            {
                Intent intent = new Intent(Dashboard.this,LISTENING_part2.class);
                startActivity(intent);
            }
            if(childPosition==1)
            {
                Intent intent = new Intent(Dashboard.this,READING.class);
                startActivity(intent);
            }
            if(childPosition==2)
            {
                Intent intent = new Intent(Dashboard.this,SPEAKING.class);
                startActivity(intent);
            }
            if(childPosition==3)
            {
                Intent intent = new Intent(Dashboard.this,WRITING.class);
                startActivity(intent);
            }
        }

        if(groupPosition==5)
        {
            if (childPosition==0)
            {
                String url = "https://online.celpip.biz/member/dashboard";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
            else
            {
                String url = "https://online.celpip.biz/member/dashboard";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        }
        if(groupPosition==6)
        {
           if (childPosition==0) {
               String url = "https://online.celpip.biz/member/dashboard";
               Intent i = new Intent(Intent.ACTION_VIEW);
               i.setData(Uri.parse(url));
               startActivity(i);
           }
           else
           {
               String url = "https://online.celpip.biz/member/dashboard";
               Intent i = new Intent(Intent.ACTION_VIEW);
               i.setData(Uri.parse(url));
               startActivity(i);
           }

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



//---------------------------------------------------------------------------------//


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://online.celpip.biz/api/login?password="+password+"&username="+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));

                            if (abc == 1)
                            {
                                id          = obj.getJSONObject("").getString("id");
                                total_coins = obj.getJSONObject("").getString("coins");
                                textViewTotalCoins.setText("Total Coins = "+total_coins);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent= new Intent(Dashboard.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        //close();
                        sessionManager.logoutUser();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("DASHBOARD");
        listDataHeader.add("EARN FEE COINS");
        listDataHeader.add("COMPETITIVE ENGLISH");
        listDataHeader.add("CELPIP SECTIONS TESTS");
        listDataHeader.add("CELPIP PARTS TESTS");
        listDataHeader.add("SUPPORT");
        listDataHeader.add("SETTINGS");
        listDataHeader.add("COIN MANAGEMENT");



        // Adding child data
        List<String> dashboard       = new ArrayList<String>();
        List<String> earn_fee_coins = new ArrayList<String>();
        List<String> competitive_english  = new ArrayList<String>();
        competitive_english.add("VOCABULARY TESTS");
        competitive_english.add("SPOTTING THE ERRORS");
        competitive_english.add("DICTATION TESTS");
        competitive_english.add("RE-ARRANGE");
        competitive_english.add("FILL IN THE BLANKS");
        List<String> celpip_sections_test   = new ArrayList<String>();
        celpip_sections_test.add("LISTENING");
        List<String> celpip_parts_tests= new ArrayList<String>();
        celpip_parts_tests.add("LISTENING");
        celpip_parts_tests.add("READING");
        celpip_parts_tests.add("SPEAKING");
        celpip_parts_tests.add("WRITING");
        List<String> support       = new ArrayList<String>();
        support.add("VIEW TICKETS");
        support.add("CREATE NEW TICKET");
        List<String> setting      = new ArrayList<String>();
        setting.add("UPDATE PROFILE");
        setting.add("CHANGE PASSWORD");
        List<String> coin_management = new ArrayList<String>();



        listDataChild.put(listDataHeader.get(0), dashboard); // Header, Child data
        listDataChild.put(listDataHeader.get(1), earn_fee_coins);
        listDataChild.put(listDataHeader.get(2), competitive_english);
        listDataChild.put(listDataHeader.get(3), celpip_sections_test);
        listDataChild.put(listDataHeader.get(4), celpip_parts_tests);
        listDataChild.put(listDataHeader.get(5), support);
        listDataChild.put(listDataHeader.get(6), setting);
        listDataChild.put(listDataHeader.get(7), coin_management);



    }
}
