package com.celpipstore.Adapter.WritingTestAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.celpipstore.CelpipTests.WritingTest.WritingTestQuestionActivity;
import com.celpipstore.GetterAndSetterClasses.WritingTest.WritingTask1;
import com.celpipstore.GetterAndSetterClasses.WritingTest.WritingTask2;
import com.celpipstore.R;

import java.util.List;


public class WritingTestTask2Adapter extends BaseAdapter{
    private Context c;
    private List<WritingTask2> writingTask2;
    private RadioButton writingtask2_option1;
    private RadioButton writingtask2_option2;
    public String answer;
    public WritingTestTask2Adapter(Context c, List<WritingTask2> writingTask2)
    {
        this.c=c;
        this.writingTask2=writingTask2;
    }

    @Override
    public int getCount() {
        return writingTask2.size();
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
        convertView=in.inflate(R.layout.test_adapter_writingtask2,null);
        //****************************************************************************************//
        /***************************************Question part**************************************/
        //****************************************************************************************//
        TextView textView_question_title = convertView.findViewById(R.id.writingtask2_question_title);
        textView_question_title.setText(writingTask2.get(position).getQuestion_title());
        writingtask2_option1 = convertView.findViewById(R.id.writingtask2_option1);
        writingtask2_option2 = convertView.findViewById(R.id.writingtask2_option2);
        writingtask2_option1.setText(writingTask2.get(position).getOption_1());
        writingtask2_option2.setText(writingTask2.get(position).getOption_2());
        String filted_text =writingTask2.get(position).getQuestion_title();
        filted_text = filted_text.replaceAll("<h3>","");
        filted_text = filted_text.replaceAll("</h3>","");
        filted_text = filted_text.replaceAll("\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t","");
        filted_text = filted_text.replaceAll("<\\/h2>\\r\\n*","\n");
        filted_text = filted_text.replaceAll("<p>","");
        filted_text = filted_text.replaceAll("</p>","");
        filted_text = filted_text.replaceAll("</li>","\n");
        filted_text = filted_text.replaceAll("</ul>","\n");
        textView_question_title.setText(filted_text);
        String filted_option1= writingTask2.get(position).getOption_1();
        filted_option1 = filted_option1.replaceAll("<h2>","");
        filted_option1 = filted_option1.replaceAll("\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t","");
        filted_option1 = filted_option1.replaceAll("<\\/h2>\\r\\n*","\n");
        filted_option1 = filted_option1.replaceAll("<ul>","");
        filted_option1 = filted_option1.replaceAll("<li>","");
        filted_option1 = filted_option1.replaceAll("</li>","\n");
        filted_option1 = filted_option1.replaceAll("</ul>","\n");
        writingtask2_option1.setText(filted_option1);
        String filted_option2 = writingTask2.get(position).getOption_2();
        filted_option2 = filted_option2.replaceAll("<h2>","");
        filted_option2 = filted_option2.replaceAll("\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t","");
        filted_option2 = filted_option2.replaceAll("<\\/h2>\\r\\n*","\n");
        filted_option2 = filted_option2.replaceAll("<ul>","");
        filted_option2 = filted_option2.replaceAll("<li>","");
        filted_option2 = filted_option2.replaceAll("</li>","\n");
        filted_option2 = filted_option2.replaceAll("</ul>","\n");
        writingtask2_option2.setText(filted_option2);
        answer();
//        Log.e("===answer",answer());
        return convertView;
    }
    public String answer(){
        if (writingtask2_option1.isChecked())
        {
            answer = "option A";
        }
        else if (writingtask2_option2.isChecked())
        {
            answer = "option B";
        }
        return answer;
    }
}