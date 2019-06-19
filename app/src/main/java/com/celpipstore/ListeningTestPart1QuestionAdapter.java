package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ListeningTestPart1QuestionAdapter extends BaseAdapter{

    Context c;
    String url = "https://online.celpip.biz/uploads/part1_listening/";
    JsonDataHandlerListeningPart1 jsonHolderListingpart1;

    //media player
    double startTime = 0;
    double finalTime = 0;
    MediaPlayer mediaPlayer;
    TextView textViewStart;
    TextView textViewStop;
    SeekBar seekbar;
    Handler myHandler = new Handler();
    public static int oneTimeOnly = 0;
    String audio_player;

    String userAnswerQuestion1;
    String userAnswerQuestion2;
    String userAnswerQuestion3;
    String userAnswerQuestion4;
    String userAnswerQuestion5;
    String userAnswerQuestion6;
    String userAnswerQuestion7;
    String userAnswerQuestion8;

    ProgressDialog loadingAudio;

    //json data
    public static String id;
    public static String test_code;
    public static String l1_converstaion_1_audio;
    public static String l1_converstaion_2_audio;
    public static String l1_converstaion_3_audio;
    public static String l1_q1_audio;
    public static String l1_q1_option1;
    public static String l1_q1_option2;
    public static String l1_q1_option3;
    public static String l1_q1_option4;
    public static String l1_q1_answer;
    public static String l1_q2_audio;
    public static String l1_q2_option1;
    public static String l1_q2_option2;
    public static String l1_q2_option3;
    public static String l1_q2_option4;
    public static String l1_q2_answer;
    public static String l1_q3_audio;
    public static String l1_q3_option1;
    public static String l1_q3_option2;
    public static String l1_q3_option3;
    public static String l1_q3_option4;
    public static String l1_q3_answer;
    public static String l1_q4_audio;
    public static String l1_q4_option1;
    public static String l1_q4_option2;
    public static String l1_q4_option3;
    public static String l1_q4_option4;
    public static String l1_q4_answer;
    public static String l1_q5_audio;
    public static String l1_q5_option1;
    public static String l1_q5_option2;
    public static String l1_q5_option3;
    public static String l1_q5_option4;
    public static String l1_q5_answer;
    public static String l1_q6_audio;
    public static String l1_q6_option1;
    public static String l1_q6_option2;
    public static String l1_q6_option3;
    public static String l1_q6_option4;
    public static String l1_q6_answer;
    public static String l1_q7_audio;
    public static String l1_q7_option1;
    public static String l1_q7_option2;
    public static String l1_q7_option3;
    public static String l1_q7_option4;
    public static String l1_q7_answer;
    public static String l1_q8_audio;
    public static String l1_q8_option1;
    public static String l1_q8_option2;
    public static String l1_q8_option3;
    public static String l1_q8_option4;
    public static String l1_q8_answer;

    public ListeningTestPart1QuestionAdapter(Context c
            , String id
            , String test_code
            , String l1_q1_audio
            , String l1_q1_option1
            , String l1_q1_option2
            , String l1_q1_option3
            , String l1_q1_option4
            , String l1_q2_audio
            , String l1_q2_option1
            , String l1_q2_option2
            , String l1_q2_option3
            , String l1_q2_option4
            , String l1_q3_audio
            , String l1_q3_option1
            , String l1_q3_option2
            , String l1_q3_option3
            , String l1_q3_option4
            , String l1_q4_audio
            , String l1_q4_option1
            , String l1_q4_option2
            , String l1_q4_option3
            , String l1_q4_option4
            , String l1_q5_audio
            , String l1_q5_option1
            , String l1_q5_option2
            , String l1_q5_option3
            , String l1_q5_option4
            , String l1_q6_audio
            , String l1_q6_option1
            , String l1_q6_option2
            , String l1_q6_option3
            , String l1_q6_option4
            , String l1_q7_audio
            , String l1_q7_option1
            , String l1_q7_option2
            , String l1_q7_option3
            , String l1_q7_option4
            , String l1_q8_audio
            , String l1_q8_option1
            , String l1_q8_option2
            , String l1_q8_option3
            , String l1_q8_option4)
            {
        this.c=c;
        this.id         = ListeningTestPart1QuestionAdapter.id;
        this.test_code  = ListeningTestPart1QuestionAdapter.test_code;
        this.l1_converstaion_1_audio = l1_converstaion_1_audio;
        this.l1_converstaion_2_audio = l1_converstaion_2_audio;
        this.l1_converstaion_3_audio = l1_converstaion_3_audio;
        this.l1_q1_audio   = l1_q1_audio;
        this.l1_q1_option1 = l1_q1_option1;
        this.l1_q1_option2 = l1_q1_option2;
        this.l1_q1_option3 = l1_q1_option3;
        this.l1_q1_option4 = l1_q1_option4;
        this.l1_q2_audio   = l1_q2_audio;
        this.l1_q2_option1 = l1_q2_option1;
        this.l1_q2_option2 = l1_q2_option2;
        this.l1_q2_option3 = l1_q2_option3;
        this.l1_q2_option4 = l1_q2_option4;
        this.l1_q3_audio   = l1_q3_audio;
        this.l1_q3_option1 = l1_q3_option1;
        this.l1_q3_option2 = l1_q3_option2;
        this.l1_q3_option3 = l1_q3_option3;
        this.l1_q3_option4 = l1_q3_option4;
        this.l1_q4_audio   = l1_q4_audio;
        this.l1_q4_option1 = l1_q4_option1;
        this.l1_q4_option2 = l1_q4_option2;
        this.l1_q4_option3 = l1_q4_option3;
        this.l1_q4_option4 = l1_q4_option4;
        this.l1_q5_audio   = l1_q5_audio;
        this.l1_q5_option1 = l1_q5_option1;
        this.l1_q5_option2 = l1_q5_option2;
        this.l1_q5_option3 = l1_q5_option3;
        this.l1_q5_option4 = l1_q5_option4;
        this.l1_q6_audio   = l1_q6_audio;
        this.l1_q6_option1 = l1_q6_option1;
        this.l1_q6_option2 = l1_q6_option2;
        this.l1_q6_option3 = l1_q6_option3;
        this.l1_q6_option4 = l1_q6_option4;
        this.l1_q7_audio   = l1_q7_audio;
        this.l1_q7_option1 = l1_q7_option1;
        this.l1_q7_option2 = l1_q7_option2;
        this.l1_q7_option3 = l1_q7_option3;
        this.l1_q7_option4 = l1_q7_option4;
        this.l1_q8_audio   = l1_q8_audio;
        this.l1_q8_option1 = l1_q8_option1;
        this.l1_q8_option2 = l1_q8_option2;
        this.l1_q8_option3 = l1_q8_option3;
        this.l1_q8_option4 = l1_q8_option4;

    }


    @Override
    public int getCount() {
        return 1;
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

        convertView=in.inflate(R.layout.test_adapter_listeningtestpart1,null);

        final RadioGroup radioGroup1 = (RadioGroup)convertView.findViewById(R.id.frame1);
        final RadioGroup radioGroup2 = (RadioGroup)convertView.findViewById(R.id.frame2);

        final RadioButton t1=(RadioButton) convertView.findViewById(R.id.radioButtonOption1);
        final RadioButton t2=(RadioButton) convertView.findViewById(R.id.radioButtonOption2);
        final RadioButton t3=(RadioButton) convertView.findViewById(R.id.radioButtonOption3);
        final RadioButton t4=(RadioButton) convertView.findViewById(R.id.radioButtonOption4);
        final RadioButton t5=(RadioButton) convertView.findViewById(R.id.radio1);
        final RadioButton t6=(RadioButton) convertView.findViewById(R.id.radio2);
        final RadioButton t7=(RadioButton) convertView.findViewById(R.id.radio3);
        final RadioButton t8=(RadioButton) convertView.findViewById(R.id.radio4);

        final Button b1NextQuestion = convertView.findViewById(R.id.buttonNextQuestion1);
        final Button b2NextQuestion = convertView.findViewById(R.id.buttonNextQuestion2);
        final Button b3NextQuestion = convertView.findViewById(R.id.buttonNextQuestion3);
        final Button b4NextQuestion = convertView.findViewById(R.id.buttonNextQuestion4);
        final Button b5NextQuestion = convertView.findViewById(R.id.buttonNextQuestion5);
        final Button b6NextQuestion = convertView.findViewById(R.id.buttonNextQuestion6);
        final Button b7NextQuestion = convertView.findViewById(R.id.buttonNextQuestion7);
        final Button b8NextQuestion = convertView.findViewById(R.id.buttonNextQuestion8);
        final Button b9NextQuestion = convertView.findViewById(R.id.buttonFinish);
        final Button b10NextQuestion = convertView.findViewById(R.id.buttonAudio2);
        final Button b11NextQuestion = convertView.findViewById(R.id.buttonAudio3);

        final ImageView imageView1  = convertView.findViewById(R.id.imageViewRadio1);
        final ImageView imageView2  = convertView.findViewById(R.id.imageViewRadio2);
        final ImageView imageView3  = convertView.findViewById(R.id.imageViewRadio3);
        final ImageView imageView4  = convertView.findViewById(R.id.imageViewRadio4);

        final String questions_audio[]  = {l1_q1_audio,l1_q2_audio,l1_q3_audio,l1_q4_audio,l1_q5_audio,l1_q6_audio,l1_q7_audio,l1_q8_audio,null};
        final String question_option1[] = {l1_q1_option1,l1_q2_option1,l1_q3_option3,l1_q4_option1,l1_q5_option1,l1_q6_option1,l1_q7_option1,l1_q8_option1,null};
        final String question_option2[] = {l1_q1_option2,l1_q2_option2,l1_q3_option2,l1_q4_option2,l1_q5_option2,l1_q6_option2,l1_q7_option2,l1_q8_option2,null};
        final String question_option3[] = {l1_q1_option3,l1_q2_option3,l1_q3_option3,l1_q4_option3,l1_q5_option3,l1_q6_option3,l1_q7_option3,l1_q8_option3,null};
        final String question_option4[] = {l1_q1_option4,l1_q2_option4,l1_q3_option4,l1_q4_option4,l1_q5_option4,l1_q6_option4,l1_q7_option4,l1_q8_option4,null};

        ImageButton imageButtonPlay = (ImageButton)convertView.findViewById(R.id.buttonPlay);
        final ImageButton imageButtonPause = (ImageButton)convertView.findViewById(R.id.buttonPause);

        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        textViewStart = (TextView) finalConvertView1.findViewById(R.id.textViewStartTime);
        textViewStop  = (TextView) finalConvertView1.findViewById(R.id.textViewStopTime);
        seekbar = (SeekBar)  finalConvertView.findViewById(R.id.seekbar);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlayMusic().execute();
            }
        });



            SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
            final String tokenCode = bb.getString("tokenCode", "tokenCode");
            final String member_id = bb.getString("member_id", "member_id");



            final String image1 = "https://online.celpip.biz/uploads/part1_listening/"+question_option1[0];
            final String image2 = "https://online.celpip.biz/uploads/part1_listening/"+question_option2[0];
            final String image3 = "https://online.celpip.biz/uploads/part1_listening/"+question_option3[0];
            final String image4 = "https://online.celpip.biz/uploads/part1_listening/"+question_option4[0];
            final ProgressDialog loading = ProgressDialog.show(c,"Loading","Please wait...",false,false);

        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        audio_player = url+questions_audio[0];
        Glide
                .with(c)
                .load(image1)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView1);

        Glide
                .with(c)
                .load(image2)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView2);
        Glide
                .with(c)
                .load(image3)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView3);
        Glide
                .with(c)
                .load(image4)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView4);


        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
        t4.setChecked(false);

        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    Toast.makeText(c, "Pause", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b1NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup2.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                b1NextQuestion.setVisibility(View.GONE);
                b2NextQuestion.setVisibility(View.VISIBLE);
                t5.setText(image1);
                t6.setText(image2);
                t7.setText(image3);
                t8.setText(image4);
                if (t5.isChecked())
                {
                    userAnswerQuestion1 = image1;
                }
                if (t6.isChecked())
                {
                    userAnswerQuestion1 = image2;
                }
                if (t7.isChecked())
                {
                    userAnswerQuestion1 = image3;
                }
                if (t8.isChecked())
                {
                    userAnswerQuestion1 = image4;
                }
                audio_player = url + questions_audio[1];
                t1.setText(question_option1[1]);
                t2.setText(question_option2[1]);
                t3.setText(question_option3[1]);
                t4.setText(question_option4[1]);
            }
        });


        b2NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2NextQuestion.setVisibility(View.GONE);
                b10NextQuestion.setVisibility(View.VISIBLE);
                radioGroup2.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[2];
                t1.setText(question_option1[2]);
                t2.setText(question_option2[2]);
                t3.setText(question_option3[2]);
                t4.setText(question_option4[2]);
                if (t1.isChecked())
                {
                    userAnswerQuestion2 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion2 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion2 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion2 = t4.getText().toString();
                }

            }
        });
        b10NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b10NextQuestion.setVisibility(View.GONE);
                imageButtonPause.setVisibility(View.VISIBLE);
                b3NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.GONE);
                audio_player = "https://online.celpip.biz/uploads/part1_listening/"+jsonHolderListingpart1.l1_converstaion_2_audio;
            }
        });
        b3NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b3NextQuestion.setVisibility(View.GONE);
                b4NextQuestion.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[3];
                t1.setText(question_option1[3]);
                t2.setText(question_option2[3]);
                t3.setText(question_option3[3]);
                t4.setText(question_option4[3]);
                if (t1.isChecked())
                {
                    userAnswerQuestion3 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion3 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion3 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion3 = t4.getText().toString();
                }

            }
        });
        b4NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b4NextQuestion.setVisibility(View.GONE);
                b5NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[4];
                t1.setText(question_option1[4]);
                t2.setText(question_option2[4]);
                t3.setText(question_option3[4]);
                t4.setText(question_option4[4]);
                t1.setChecked(false);
                t2.setChecked(false);
                t3.setChecked(false);
                t4.setChecked(false);
                if (t1.isChecked())
                {
                    userAnswerQuestion4 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion4 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion4 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion4 = t4.getText().toString();
                }

            }
        });
        b5NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b5NextQuestion.setVisibility(View.GONE);
                b11NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[5];
                t1.setText(question_option1[5]);
                t2.setText(question_option2[5]);
                t3.setText(question_option3[5]);
                t4.setText(question_option4[5]);

                if (t1.isChecked())
                {
                    userAnswerQuestion5 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion5 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion5 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion5 = t4.getText().toString();
                }



            }
        });

        b11NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b11NextQuestion.setVisibility(View.GONE);
                imageButtonPause.setVisibility(View.VISIBLE);
                b6NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.GONE);
                audio_player = "https://online.celpip.biz/uploads/part1_listening/"+jsonHolderListingpart1.l1_converstaion_3_audio;
            }
        });

        b6NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b6NextQuestion.setVisibility(View.GONE);
                b7NextQuestion.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[6];
                t1.setText(question_option1[6]);
                t2.setText(question_option2[6]);
                t3.setText(question_option3[6]);
                t4.setText(question_option4[6]);
                if (t1.isChecked())
                {
                    userAnswerQuestion6 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion6 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion6 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion6 = t4.getText().toString();
                }


            }
        });
        b7NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b7NextQuestion.setVisibility(View.GONE);
                b8NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[7];
                t1.setText(question_option1[7]);
                t2.setText(question_option2[7]);
                t3.setText(question_option3[7]);
                t4.setText(question_option4[7]);
                if (t1.isChecked())
                {
                    userAnswerQuestion7 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion7 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion7 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion7 = t4.getText().toString();
                }


            }
        });
        b8NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
;
                radioGroup1.setVisibility(View.GONE);
                b8NextQuestion.setVisibility(View.GONE);
                b9NextQuestion.setVisibility(View.VISIBLE);
                t1.setChecked(false);
                t2.setChecked(false);
                t3.setChecked(false);
                t4.setChecked(false);
                if (t1.isChecked())
                {
                    userAnswerQuestion8 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion8 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion8 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion8 = t4.getText().toString();
                }
                String urlForSubmittingOptions = "http://online.celpip.biz/api/lsPart1Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&q7_response="+userAnswerQuestion7+"&q8_response="+userAnswerQuestion8+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, response, Toast.LENGTH_SHORT).show();
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("q1_response", userAnswerQuestion1);
                        params.put("q2_response", userAnswerQuestion2);
                        params.put("q3_response", userAnswerQuestion3);
                        params.put("q4_response", userAnswerQuestion4);
                        params.put("q5_response", userAnswerQuestion5);
                        params.put("q6_response", userAnswerQuestion6);
                        params.put("q7_response", userAnswerQuestion7);
                        params.put("q8_response", userAnswerQuestion8);
                        Log.e("===answer1",userAnswerQuestion1);
                        Log.e("===answer2",userAnswerQuestion2);
                        Log.e("===answer3",userAnswerQuestion3);
                        Log.e("===answer4",userAnswerQuestion4);
                        Log.e("===answer5",userAnswerQuestion5);
                        Log.e("===answer6",userAnswerQuestion6);
                        Log.e("===answer7",userAnswerQuestion7);
                        Log.e("===answer8",userAnswerQuestion8);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c.getApplicationContext());
                requestQueue.add(stringRequest);


            }
        });
        b9NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               radioGroup2.setVisibility(View.GONE);
               radioGroup1.setVisibility(View.GONE);
               Intent intent = new Intent(c , LISTENING_part2.class);
               c.startActivity(intent);

            }
        });

        return convertView;
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            textViewStop.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    finalTime)))
            );

            textViewStart.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
    class  PlayMusic extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            loadingAudio = new ProgressDialog(c);
            loadingAudio.setMessage("Loading Audio");
            loadingAudio.show();
            loadingAudio.setCancelable(false);
            loadingAudio.setIndeterminate(false);
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... unused) {


            audioFile();
            return (null);
        }

        protected void onPostExecute(Void unused) {

            //here you can call your mp.prepare();
            loadingAudio.dismiss();
        }

    }
    public void audioFile()
    {
        Uri myUri = Uri.parse(audio_player);
        try
        {
            seekbar.setClickable(false);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(c, myUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                            }

            }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    seekbar.setProgress(0);
                }
            });
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();
            if (oneTimeOnly == 0) {
                seekbar.setMax((int) finalTime);
                oneTimeOnly = 0;
            }

            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(UpdateSongTime,100);



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}