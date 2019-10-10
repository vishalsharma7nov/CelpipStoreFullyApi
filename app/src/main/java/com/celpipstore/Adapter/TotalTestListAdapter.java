package com.celpipstore.Adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.celpipstore.GetterAndSetterClasses.TotalTest;
import com.celpipstore.GetterAndSetterClasses.TotalTestList;
import com.celpipstore.JsonData.JsonDataHandler;
import com.celpipstore.ListeningTestQuestionActivity;
import com.celpipstore.R;

import java.util.List;


public class TotalTestListAdapter extends BaseAdapter{

    Context c;
    List<TotalTestList> totalTestLists;
    public TotalTestListAdapter(Context c, List<TotalTestList> totalTestLists)
    {
        this.c=c;
        this.totalTestLists= totalTestLists;
    }

    @Override
    public int getCount() {
        return totalTestLists.size();
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
        TextView t2=convertView.findViewById(R.id.listeningTestListTestNumber);
        Button b1 = convertView.findViewById(R.id.listeningTestListStartTest);
        Button b2 = convertView.findViewById(R.id.reviewTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager ConnectionManager=(ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(c, ListeningTestQuestionActivity.class);
                    intent.putExtra("t2",totalTestLists.get(position).getTest_code());
                    c.startActivity(intent);
                }
                else
                {
                    AlertDialog alertbox = new AlertDialog.Builder(c)
                            .setMessage("Check Your Internet Connention?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
         t2.setText("Test"+" "+totalTestLists.get(position).getId());
     return convertView;
    }
}