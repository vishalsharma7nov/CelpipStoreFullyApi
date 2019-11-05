package com.celpipstore.Adapter.ListeningTestAdapter.TotalTestList;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.celpipstore.CelpipTests.ListeningTest.ListeningTestAnswerActivity;
import com.celpipstore.CelpipTests.ListeningTest.ListeningTestQuestionActivity;
import com.celpipstore.GetterAndSetterClasses.TotalTestList.TotalTestList;
import com.celpipstore.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class ListeningTotalTestListAdapter extends BaseAdapter{
    private Context c;
    private List<TotalTestList> totalTestLists;
    public ListeningTotalTestListAdapter(Context c, List<TotalTestList> totalTestLists)
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
               connectionChecker(position);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(c, ListeningTestAnswerActivity.class);
               c.startActivity(intent);
               SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
               SharedPreferences.Editor edit = prefs.edit();
               edit.putString("test_code", totalTestLists.get(position).getTest_code());
               edit.commit();
            }
        });
         t2.setText("Test"+" "+totalTestLists.get(position).getId());
     return convertView;
    }
    private void connectionChecker(int position) {
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
                    .setMessage("CHECK YOUR INTERNET CONNECTION?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                            c.startActivity(intent);
                        }
                    })
                    .show();
            Toast.makeText(c, "NETWORK NOT AVAILABLE!!", Toast.LENGTH_LONG).show();
        }
    }
}