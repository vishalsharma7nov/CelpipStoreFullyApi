package com.celpipstore.Adapter.VocabularyTestAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.celpipstore.GetterAndSetterClasses.VocabularyTest.VocabularyTest;
import com.celpipstore.R;
import com.celpipstore.Tests.VocabularyTest.VocabularyTestQuestionsActivity;

import java.util.List;


public class VocabularyTestAdapter extends BaseAdapter{

    private Context c;
    private List<VocabularyTest> vocabularyTests;

    public VocabularyTestAdapter(Context c, List<VocabularyTest> vocabularyTests)
    {
        this.c=c;
        this.vocabularyTests=vocabularyTests;
    }

    @Override
    public int getCount() {
        return vocabularyTests.size();
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
        convertView=in.inflate(R.layout.test_adapter_vocabulary,null);
        TextView t1= convertView.findViewById(R.id.name);
        TextView t2= convertView.findViewById(R.id.total_questions);
        TextView t3= convertView.findViewById(R.id.coin_cost);
        TextView t4= convertView.findViewById(R.id.textViewId);
        Button b1 =convertView.findViewById(R.id.buttonSelectedTest);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, VocabularyTestQuestionsActivity.class);
                intent.putExtra("t1", vocabularyTests.get(position).getId());
                c.startActivity(intent);
            }
        });
        t1.setText(vocabularyTests.get(position).getName());
        t2.setText(vocabularyTests.get(position).getTotal_questions());
        t3.setText(vocabularyTests.get(position).getCoin_cost());
        t4.setText(vocabularyTests.get(position).getId());
        return convertView;
    }
}