package com.celpipstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SpeakingTestAdapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] PTEsubtype;
    public static String[] coin_cost;
    public static String[] total_test;


    public SpeakingTestAdapter(Context c, String[] id, String[] PTEsubtype, String[] coin_cost, String[] total_test)
    {
        this.c=c;
        this.id         = id;
        this.PTEsubtype = PTEsubtype;
        this.coin_cost  = coin_cost;
        this.total_test = total_test;

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

        convertView=in.inflate(R.layout.test_adapter,null);


        TextView t1=(TextView)convertView.findViewById(R.id.listeningtestpart2ptesubtype);
        TextView t2=(TextView)convertView.findViewById(R.id.listeningtestpart2coincost);
        TextView t3=(TextView)convertView.findViewById(R.id.listeningtestpart2totaltest);

        Button b1 = (Button)convertView.findViewById(R.id.buttonSelectedTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c,SpeakingTestListActivity.class);
                intent.putExtra("t1", id[position]);
                c.startActivity(intent);
            }
        });


        for (int i = 0;i<coin_cost.length;i++)
        {
            t1.setText(PTEsubtype[position]);
            t2.setText(coin_cost[position]);
            t3.setText(total_test[position]);

        }


        return convertView;
    }
}