package com.celpipstore.Adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.celpipstore.CelpipTests.ListeningTest.ListeningTestListActivity;
import com.celpipstore.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class TotalTestAdapter extends BaseAdapter{

    protected Context c;
    protected List<TotalTest> totalTests;

    public TotalTestAdapter(Context c, List<TotalTest> totalTests)
    {
        this.c=c;
        this.totalTests = totalTests;
    }

    @Override
    public int getCount() {
        return totalTests.size();
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
        convertView=in.inflate(R.layout.test_adapter,null);
        TextView t1= convertView.findViewById(R.id.listeningtestpart2ptesubtype);
        TextView t2= convertView.findViewById(R.id.listeningtestpart2coincost);
        TextView t3= convertView.findViewById(R.id.listeningtestpart2totaltest);
        Button b1 = convertView.findViewById(R.id.buttonSelectedTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager ConnectionManager=(ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()==true )
                {
                    Intent intent = new Intent(c, ListeningTestListActivity.class);
                    intent.putExtra("t1", totalTests.get(position).getId());
                    c.startActivity(intent);
                    SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putString("test_id", totalTests.get(position).getId());
                    edit.commit();
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
        t1.setText(totalTests.get(position).getPTEsubtype());
        t2.setText(totalTests.get(position).getCoin_cost());
        t3.setText(totalTests.get(position).getTotal_test());
        return convertView;
    }
}