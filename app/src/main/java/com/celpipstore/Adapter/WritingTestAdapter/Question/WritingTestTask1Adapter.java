package com.celpipstore.Adapter.WritingTestAdapter.Question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.celpipstore.GetterAndSetterClasses.WritingTest.Question.WritingTask1;
import com.celpipstore.R;

import java.util.List;


public class WritingTestTask1Adapter extends BaseAdapter{

    private Context c;
    private List<WritingTask1> writingTask1;
    public WritingTestTask1Adapter(Context c, List<WritingTask1> writingTask1)
    {
        this.c=c;
        this.writingTask1=writingTask1;
    }

    @Override
    public int getCount() {
        return writingTask1.size();
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
        convertView=in.inflate(R.layout.test_adapter_writingtask1,null);
        //****************************************************************************************//
        /***************************************Question part**************************************/
        //****************************************************************************************//
        TextView textView_question_title = convertView.findViewById(R.id.writingtask1_question_title);
        TextView textView_email_title = convertView.findViewById(R.id.writingtask1_email_title);
        textView_question_title.setText(writingTask1.get(position).getQuestion_title());
        String filted_text =writingTask1.get(position).getEmail_title();
        filted_text = filted_text.replaceAll("<h2>","");
        filted_text = filted_text.replaceAll("\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t","");
        filted_text = filted_text.replaceAll("<\\/h2>\\r\\n*","\n");
        filted_text = filted_text.replaceAll("<ul>","");
        filted_text = filted_text.replaceAll("<li>","");
        filted_text = filted_text.replaceAll("</li>","\n");
        filted_text = filted_text.replaceAll("</ul>","\n");
        textView_email_title.setText(filted_text);
        return convertView;
    }
}