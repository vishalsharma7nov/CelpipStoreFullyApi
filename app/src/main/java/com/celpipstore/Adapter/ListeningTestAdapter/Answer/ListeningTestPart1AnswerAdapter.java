package com.celpipstore.Adapter.ListeningTestAdapter.Answer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.celpipstore.GetterAndSetterClasses.ListeningTest.Answer.ListeningPart1Answer;
import com.celpipstore.R;

import java.util.List;


public class ListeningTestPart1AnswerAdapter extends BaseAdapter{
    private Context c;
    private List<ListeningPart1Answer> listeningPart1Answer;

    public ListeningTestPart1AnswerAdapter(Context c, List<ListeningPart1Answer> listeningPart1Answer) {
        this.c = c;
        this.listeningPart1Answer = listeningPart1Answer;
    }

    @Override
    public int getCount() {
        return listeningPart1Answer.size();
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
        convertView=in.inflate(R.layout.test_adapter_listeningtestpart1answer,null);
        //========================================================================================//
        /*****************************************Answer*******************************************/
        //========================================================================================//
        TextView t1 = convertView.findViewById(R.id.t1);
        TextView t2 = convertView.findViewById(R.id.t2);
        TextView t3 = convertView.findViewById(R.id.t3);
        TextView t4 = convertView.findViewById(R.id.t4);
        TextView t5 = convertView.findViewById(R.id.t5);
        TextView t6 = convertView.findViewById(R.id.t6);
        TextView t7 = convertView.findViewById(R.id.t7);
        TextView t8 = convertView.findViewById(R.id.t8);
        t1.setText(listeningPart1Answer.get(position).getQ_number());
        return convertView;
    }
}