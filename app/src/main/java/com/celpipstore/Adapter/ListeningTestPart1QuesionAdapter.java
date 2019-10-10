package com.celpipstore.Adapter;

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
import android.view.LayoutInflater;
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

import com.bumptech.glide.Glide;
import com.celpipstore.Tests.ListeningTest.LISTENING_part2;
import com.celpipstore.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ListeningTestPart1QuesionAdapter extends BaseAdapter{

    Context c;
    String url = "https://online.celpip.biz/uploads/part1_listening/";
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
    public static String l1_converstaion_1_audio;
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

    public ListeningTestPart1QuesionAdapter(Context c
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
        ListeningTestPart1QuesionAdapter.id = ListeningTestPart1QuesionAdapter.id;
        ListeningTestPart1QuesionAdapter.test_code = ListeningTestPart1QuesionAdapter.test_code;
        l1_converstaion_1_audio = l1_converstaion_1_audio;
        ListeningTestPart1QuesionAdapter.l1_q1_audio = l1_q1_audio;
        ListeningTestPart1QuesionAdapter.l1_q1_option1 = l1_q1_option1;
        ListeningTestPart1QuesionAdapter.l1_q1_option2 = l1_q1_option2;
        ListeningTestPart1QuesionAdapter.l1_q1_option3 = l1_q1_option3;
        ListeningTestPart1QuesionAdapter.l1_q1_option4 = l1_q1_option4;
        ListeningTestPart1QuesionAdapter.l1_q2_audio = l1_q2_audio;
        ListeningTestPart1QuesionAdapter.l1_q2_option1 = l1_q2_option1;
        ListeningTestPart1QuesionAdapter.l1_q2_option2 = l1_q2_option2;
        ListeningTestPart1QuesionAdapter.l1_q2_option3 = l1_q2_option3;
        ListeningTestPart1QuesionAdapter.l1_q2_option4 = l1_q2_option4;
        ListeningTestPart1QuesionAdapter.l1_q3_audio = l1_q3_audio;
        ListeningTestPart1QuesionAdapter.l1_q3_option1 = l1_q3_option1;
        ListeningTestPart1QuesionAdapter.l1_q3_option2 = l1_q3_option2;
        ListeningTestPart1QuesionAdapter.l1_q3_option3 = l1_q3_option3;
        ListeningTestPart1QuesionAdapter.l1_q3_option4 = l1_q3_option4;
        ListeningTestPart1QuesionAdapter.l1_q4_audio = l1_q4_audio;
        ListeningTestPart1QuesionAdapter.l1_q4_option1 = l1_q4_option1;
        ListeningTestPart1QuesionAdapter.l1_q4_option2 = l1_q4_option2;
        ListeningTestPart1QuesionAdapter.l1_q4_option3 = l1_q4_option3;
        ListeningTestPart1QuesionAdapter.l1_q4_option4 = l1_q4_option4;
        ListeningTestPart1QuesionAdapter.l1_q5_audio = l1_q5_audio;
        ListeningTestPart1QuesionAdapter.l1_q5_option1 = l1_q5_option1;
        ListeningTestPart1QuesionAdapter.l1_q5_option2 = l1_q5_option2;
        ListeningTestPart1QuesionAdapter.l1_q5_option3 = l1_q5_option3;
        ListeningTestPart1QuesionAdapter.l1_q5_option4 = l1_q5_option4;
        ListeningTestPart1QuesionAdapter.l1_q6_audio = l1_q6_audio;
        ListeningTestPart1QuesionAdapter.l1_q6_option1 = l1_q6_option1;
        ListeningTestPart1QuesionAdapter.l1_q6_option2 = l1_q6_option2;
        ListeningTestPart1QuesionAdapter.l1_q6_option3 = l1_q6_option3;
        ListeningTestPart1QuesionAdapter.l1_q6_option4 = l1_q6_option4;
        ListeningTestPart1QuesionAdapter.l1_q7_audio = l1_q7_audio;
        ListeningTestPart1QuesionAdapter.l1_q7_option1 = l1_q7_option1;
        ListeningTestPart1QuesionAdapter.l1_q7_option2 = l1_q7_option2;
        ListeningTestPart1QuesionAdapter.l1_q7_option3 = l1_q7_option3;
        ListeningTestPart1QuesionAdapter.l1_q7_option4 = l1_q7_option4;
        ListeningTestPart1QuesionAdapter.l1_q8_audio = l1_q8_audio;
        ListeningTestPart1QuesionAdapter.l1_q8_option1 = l1_q8_option1;
        ListeningTestPart1QuesionAdapter.l1_q8_option2 = l1_q8_option2;
        ListeningTestPart1QuesionAdapter.l1_q8_option3 = l1_q8_option3;
        ListeningTestPart1QuesionAdapter.l1_q8_option4 = l1_q8_option4;

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

        final RadioGroup radioGroup1 = convertView.findViewById(R.id.frame1);
        final RadioGroup radioGroup2 = convertView.findViewById(R.id.frame2);

        final RadioButton t1= convertView.findViewById(R.id.radioButtonOption1);
        final RadioButton t2= convertView.findViewById(R.id.radioButtonOption2);
        final RadioButton t3= convertView.findViewById(R.id.radioButtonOption3);
        final RadioButton t4= convertView.findViewById(R.id.radioButtonOption4);

        final Button b2NextQuestion = convertView.findViewById(R.id.buttonNextQuestion2);
        final Button b3NextQuestion = convertView.findViewById(R.id.buttonNextQuestion3);
        final Button b4NextQuestion = convertView.findViewById(R.id.buttonNextQuestion4);
        final Button b5NextQuestion = convertView.findViewById(R.id.buttonNextQuestion5);
        final Button b6NextQuestion = convertView.findViewById(R.id.buttonNextQuestion6);
        final Button b7NextQuestion = convertView.findViewById(R.id.buttonNextQuestion7);
        final Button b8NextQuestion = convertView.findViewById(R.id.buttonNextQuestion8);
        final Button b9NextQuestion = convertView.findViewById(R.id.buttonFinish);

        final ImageView imageView1  = convertView.findViewById(R.id.imageViewRadio1);
        final ImageView imageView2  = convertView.findViewById(R.id.imageViewRadio2);
        final ImageView imageView3  = convertView.findViewById(R.id.imageViewRadio3);
        final ImageView imageView4  = convertView.findViewById(R.id.imageViewRadio4);

        final String[] questions_audio = {l1_q1_audio, l1_q2_audio, l1_q3_audio, l1_q4_audio, l1_q5_audio, l1_q6_audio, l1_q7_audio, l1_q8_audio, null};
        final String[] question_option1 = {l1_q1_option1, l1_q2_option1, l1_q3_option3, l1_q4_option1, l1_q5_option1, l1_q6_option1, l1_q7_option1, l1_q8_option1, null};
        final String[] question_option2 = {l1_q1_option2, l1_q2_option2, l1_q3_option2, l1_q4_option2, l1_q5_option2, l1_q6_option2, l1_q7_option2, l1_q8_option2, null};
        final String[] question_option3 = {l1_q1_option3, l1_q2_option3, l1_q3_option3, l1_q4_option3, l1_q5_option3, l1_q6_option3, l1_q7_option3, l1_q8_option3, null};
        final String[] question_option4 = {l1_q1_option4, l1_q2_option4, l1_q3_option4, l1_q4_option4, l1_q5_option4, l1_q6_option4, l1_q7_option4, l1_q8_option4, null};

        final ListView lv = convertView.findViewById(R.id.correctanswer);
        final ListView lv1 = convertView.findViewById(R.id.userAnswer);

        ImageButton imageButtonPlay = convertView.findViewById(R.id.buttonPlay);
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myUri = Uri.parse(audio_player);
                try
                {
                    textViewStart = finalConvertView1.findViewById(R.id.textViewStartTime);
                    textViewStop  = finalConvertView1.findViewById(R.id.textViewStopTime);
                    seekbar = finalConvertView.findViewById(R.id.seekbar);
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
        String[] correct_answer = {l1_q1_answer, l1_q2_answer, l1_q3_answer, l1_q4_answer, l1_q5_answer, l1_q6_answer, l1_q7_answer, l1_q8_answer};
            String image1 = "https://online.celpip.biz/uploads/part1_listening/"+question_option1[0];
            String image2 = "https://online.celpip.biz/uploads/part1_listening/"+question_option2[0];
            String image3 = "https://online.celpip.biz/uploads/part1_listening/"+question_option3[0];
            String image4 = "https://online.celpip.biz/uploads/part1_listening/"+question_option4[0];
            audio_player = url+questions_audio[0];
            t1.setText(question_option1[0]);
            t2.setText(question_option2[0]);
            t3.setText(question_option3[0]);
            t4.setText(question_option4[0]);
            Glide
                    .with(c)
                    .load(image1)
                    .into(imageView1);
            Glide
                    .with(c)
                    .load(image2)
                    .into(imageView2);
            Glide
                    .with(c)
                    .load(image3)
                    .into(imageView3);
            Glide
                    .with(c)
                    .load(image4)
                    .into(imageView4);
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
                radioGroup2.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
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
                b6NextQuestion.setVisibility(View.GONE);
                b7NextQuestion.setVisibility(View.VISIBLE);
            }
        });
        b7NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[6];
                t1.setText(question_option1[6]);
                t2.setText(question_option2[6]);
                t3.setText(question_option3[6]);
                t4.setText(question_option4[6]);
                b7NextQuestion.setVisibility(View.GONE);
                b8NextQuestion.setVisibility(View.VISIBLE);
            }
        });
        b8NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[7];
                t1.setText(question_option1[7]);
                t2.setText(question_option2[7]);
                t3.setText(question_option3[7]);
                t4.setText(question_option4[7]);
                b8NextQuestion.setVisibility(View.GONE);
                b9NextQuestion.setVisibility(View.VISIBLE);
            }
        });
        b9NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio_player = url + questions_audio[8];
                t1.setText(question_option1[8]);
                t2.setText(question_option2[8]);
                t3.setText(question_option3[8]);
                t4.setText(question_option4[8]);
//                b9NextQuestion.setVisibility(View.GONE);
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