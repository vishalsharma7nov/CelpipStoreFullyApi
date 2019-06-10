package com.celpipstore;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ListeningTestQuesionAdapter extends BaseAdapter{

    Context c;
    String url = "https://online.celpip.biz/uploads/part2_listening/";
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


    //json data
    public static String id;
    public static String test_code;
    public static String converstaion_1_audio;
    public static String q1_audio;
    public static String q1_option1;
    public static String q1_option2;
    public static String q1_option3;
    public static String q1_option4;
    public static String q1_answer;
    public static String q2_audio;
    public static String q2_option1;
    public static String q2_option2;
    public static String q2_option3;
    public static String q2_option4;
    public static String q2_answer;
    public static String q3_audio;
    public static String q3_option1;
    public static String q3_option2;
    public static String q3_option3;
    public static String q3_option4;
    public static String q3_answer;
    public static String q4_audio;
    public static String q4_option1;
    public static String q4_option2;
    public static String q4_option3;
    public static String q4_option4;
    public static String q4_answer;
    public static String q5_audio;
    public static String q5_option1;
    public static String q5_option2;
    public static String q5_option3;
    public static String q5_option4;
    public static String q5_answer;

    public ListeningTestQuesionAdapter(Context c, String id, String test_code,String converstaion_1_audio,String q1_audio,String q1_option1,String q1_option2,String q1_option3,String q1_option4,String q2_audio,String q2_option1,String q2_option2,String q2_option3,String q2_option4,String q3_audio,String q3_option1,String q3_option2,String q3_option3,String q3_option4,String q4_audio,String q4_option1,String q4_option2,String q4_option3,String q4_option4,String q5_audio,String q5_option1,String q5_option2,String q5_option3,String q5_option4)
    {
        this.c=c;
        this.id         = id;
        this.test_code  = test_code;
        this.converstaion_1_audio = converstaion_1_audio;
        this.q1_audio   = q1_audio;
        this.q1_option1 = q1_option1;
        this.q1_option2 = q1_option2;
        this.q1_option3 = q1_option3;
        this.q1_option4 = q1_option4;
        this.q2_audio   = q2_audio;
        this.q2_option1 = q2_option1;
        this.q2_option2 = q2_option2;
        this.q2_option3 = q2_option3;
        this.q2_option4 = q2_option4;
        this.q3_audio   = q3_audio;
        this.q3_option1 = q3_option1;
        this.q3_option2 = q3_option2;
        this.q3_option3 = q3_option3;
        this.q3_option4 = q3_option4;
        this.q4_audio   = q4_audio;
        this.q4_option1 = q4_option1;
        this.q4_option2 = q4_option2;
        this.q4_option3 = q4_option3;
        this.q4_option4 = q4_option4;
        this.q5_audio   = q5_audio;
        this.q5_option1 = q5_option1;
        this.q5_option2 = q5_option2;
        this.q5_option3 = q5_option3;
        this.q5_option4 = q5_option4;

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

        convertView=in.inflate(R.layout.test_adapter_listeningtest,null);


        final RadioButton t1=(RadioButton) convertView.findViewById(R.id.radioButtonOption1);
        final RadioButton t2=(RadioButton) convertView.findViewById(R.id.radioButtonOption2);
        final RadioButton t3=(RadioButton) convertView.findViewById(R.id.radioButtonOption3);
        final RadioButton t4=(RadioButton) convertView.findViewById(R.id.radioButtonOption4);

        final Button b2NextQuestion = convertView.findViewById(R.id.buttonNextQuestion2);
        final Button b3NextQuestion = convertView.findViewById(R.id.buttonNextQuestion3);
        final Button b4NextQuestion = convertView.findViewById(R.id.buttonNextQuestion4);
        final Button b5NextQuestion = convertView.findViewById(R.id.buttonNextQuestion5);
        final Button b6NextQuestion = convertView.findViewById(R.id.buttonFinish);

        final String questions_audio[]  = {q1_audio,q2_audio,q3_audio,q4_audio,q5_audio,null};
        final String question_option1[] = {q1_option1,q2_option1,q3_option3,q4_option1,q5_option1,null};
        final String question_option2[] = {q1_option2,q2_option2,q3_option2,q4_option2,q5_option2,null};
        final String question_option3[] = {q1_option3,q2_option3,q3_option3,q4_option3,q5_option3,null};
        final String question_option4[] = {q1_option4,q2_option4,q3_option4,q4_option4,q5_option4,null};

        final ListView lv = convertView.findViewById(R.id.correctanswer);
        final ListView lv1 = convertView.findViewById(R.id.userAnswer);

        ImageButton imageButtonPlay = (ImageButton)convertView.findViewById(R.id.buttonPlay);
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myUri = Uri.parse(audio_player);
                try
                {

                    textViewStart = (TextView) finalConvertView1.findViewById(R.id.textViewStartTime);
                    textViewStop  = (TextView) finalConvertView1.findViewById(R.id.textViewStopTime);
                    int forwardTime = 5000;
                    int backwardTime = 5000;
                    seekbar = (SeekBar)  finalConvertView.findViewById(R.id.seekbar);
                    seekbar.setClickable(false);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(c, myUri);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer player) {

                            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                                    seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                                    seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                                    mediaPlayer.start();
                                }
                            });
                        }
                    });
                    finalTime = mediaPlayer.getDuration();
                    startTime = mediaPlayer.getCurrentPosition();
                    if (oneTimeOnly == 0) {
                        seekbar.setMax((int) finalTime);
                        oneTimeOnly = 0;
                    }

                    seekbar.setProgress((int)startTime);
                    myHandler.postDelayed(UpdateSongTime,100);
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


                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });

            String correct_answer[] = {q1_answer,q2_answer,q3_answer,q4_answer,q5_answer};


            audio_player = url+questions_audio[0];
            t1.setText(question_option1[0]);
            t2.setText(question_option2[0]);
            t3.setText(question_option3[0]);
            t4.setText(question_option4[0]);


        b2NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[1];
                t1.setText(question_option1[1]);
                t2.setText(question_option2[1]);
                t3.setText(question_option3[1]);
                t4.setText(question_option4[1]);
                b2NextQuestion.setVisibility(View.GONE);
                b3NextQuestion.setVisibility(View.VISIBLE);

            }
        });
        b3NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[2];
                t1.setText(question_option1[2]);
                t2.setText(question_option2[2]);
                t3.setText(question_option3[2]);
                t4.setText(question_option4[2]);
                b3NextQuestion.setVisibility(View.GONE);
                b4NextQuestion.setVisibility(View.VISIBLE);

            }
        });
        b4NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[3];
                t1.setText(question_option1[3]);
                t2.setText(question_option2[3]);
                t3.setText(question_option3[3]);
                t4.setText(question_option4[3]);
                b4NextQuestion.setVisibility(View.GONE);
                b5NextQuestion.setVisibility(View.VISIBLE);

            }
        });
        b5NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[4];
                t1.setText(question_option1[4]);
                t2.setText(question_option2[4]);
                t3.setText(question_option3[4]);
                t4.setText(question_option4[4]);
                b5NextQuestion.setVisibility(View.GONE);
                b6NextQuestion.setVisibility(View.VISIBLE);

            }
        });
        b6NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[5];
                t1.setText(question_option1[5]);
                t2.setText(question_option2[5]);
                t3.setText(question_option3[5]);
                t4.setText(question_option4[5]);
                b5NextQuestion.setVisibility(View.GONE);


                Intent intent = new Intent(c , LISTENING_part2.class);
                c.startActivity(intent);

            }
        });

        return convertView;
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            textViewStart.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

}