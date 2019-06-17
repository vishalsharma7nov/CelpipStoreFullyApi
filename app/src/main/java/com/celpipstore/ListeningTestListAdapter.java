package com.celpipstore;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ListeningTestListAdapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] test_code;


    public ListeningTestListAdapter(Context c, String[] id, String[] test_code)
    {
        this.c=c;
        this.id         = id;
        this.test_code  = test_code;

    }


    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.test_list_adapter,null);


//        TextView t1=(TextView)convertView.findViewById(R.id.listeningTestListId);
        TextView t2=(TextView)convertView.findViewById(R.id.listeningTestListTestNumber);


        Button b1 = (Button)convertView.findViewById(R.id.listeningTestListStartTest);
        Button b2 = (Button)convertView.findViewById(R.id.reviewTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ConnectivityManager ConnectionManager=(ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    String test_id[] = JsonDataHandler.id;
                    Intent intent = new Intent(c,ListeningTestQuestionActivity.class);
                    intent.putExtra("t2",test_code[position]);
                    c.startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(c)
                            .setMessage("Check Your Internet Connention?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                // do something when the button is clicked
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                                    c.startActivity(intent);

                                }
                            })
                            .show();
                    Toast.makeText(c, "Network Not Available", Toast.LENGTH_LONG).show();

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Review", Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0;i<id.length;i++)
        {

//            t1.setText(id[position]);
            t2.setText("Test"+" "+id[position]);

        }


        return convertView;
    }
}