package com.celpipstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HOME extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button Login,Register;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Login = (Button)findViewById(R.id.loginHome);
        Register = (Button)findViewById(R.id.registerHome);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HOME.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HOME.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
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
                    Intent intent = new Intent(HOME.this,HOME.class);
                    startActivity(intent);

                }
                else if(groupPosition == 1 )
                {

                }

                else if(groupPosition == 2 )
                {

                    Intent intent = new Intent(HOME.this,WHYUS.class);
                    startActivity(intent);
                }


                else if(groupPosition == 3 )
                {
                    Intent intent = new Intent(HOME.this,OurFeature.class);
                    startActivity(intent);

                }
                else if(groupPosition == 4 )
                {
                    Intent intent = new Intent(HOME.this,FAQ.class);
                    startActivity(intent);

                }
                else if(groupPosition == 5 )
                {
                    Intent intent = new Intent(HOME.this,AboutUS.class);
                    startActivity(intent);


                }
                else if(groupPosition == 6 )
                {
                    Intent intent = new Intent(HOME.this,CONTACTUS.class);
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
                    Intent intent = new Intent(HOME.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 1)
                {
                    Intent intent = new Intent(HOME.this,RegistrationActivity.class);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.login)
        {

            Intent intent = new Intent(HOME.this,MainActivity.class);
            startActivity(intent);

        }
        else if(id == R.id.home)
        {

            Intent intent = new Intent(HOME.this,HOME.class);
            startActivity(intent);


        }

        else if (id == R.id.whyus)
        {

            Intent intent = new Intent(HOME.this,WHYUS.class);
            startActivity(intent);

        }

        else if (id == R.id.faq)
        {
            Intent intent = new Intent(HOME.this,FAQ.class);
            startActivity(intent);

        }

        else if (id == R.id.ourfeatures)
        {
            Intent intent = new Intent(HOME.this,OurFeature.class);
            startActivity(intent);

        }
        else if (id == R.id.aboutus)
        {
            Intent intent = new Intent(HOME.this,AboutUS.class);
            startActivity(intent);

        }
        else if (id == R.id.contactus)
        {
            Intent intent = new Intent(HOME.this,CONTACTUS.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
