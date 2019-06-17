package com.celpipstore;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.concurrent.TimeUnit;

public class ListeningTestQuestionActivity extends AppCompatActivity {

    ListView listView;
    String url,practicetest,part1,part2,part3,part4,part5,part6;
    String image;
    ImageView imageView;

    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;

    JsonDataHandlerPracticeTestListening jsonHolderListingPracticeTest;
    JsonDataHandlerListeningPart1 jsonHolderListingpart1;
    JsonDataHandlerListeningPart2 jsonHolderListingpart2;
    MediaPlayer mediaPlayer;
    double startTime = 0;
    double finalTime = 0;

    Handler myHandler = new Handler();;
    int forwardTime = 5000;
    int backwardTime = 5000;
    SeekBar seekbar;
    ImageButton imageButtonPlay,imageButtonPause;
    Button buttonNext,button,buttonPracticeTestSubmit;
    LinearLayout linearLayoutFrame1,linearLayoutFrame2,linearLayoutFrame3;
    TextView textViewStart,textViewStop,textViewInstruction;
    public static int oneTimeOnly = 0;
    ProgressDialog loadingAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_test_question);

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {

        }
        else
        {
            AlertDialog alertbox = new AlertDialog.Builder(this)
                    .setMessage("Check Your Internet Connention?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1) {

                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                            startActivity(intent);
                            recreate();

                        }
                    })
                    .show();
            Toast.makeText(ListeningTestQuestionActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();

        }

        loadingAudio = new ProgressDialog(ListeningTestQuestionActivity.this);
        listView = (ListView)findViewById(R.id.listView);
        imageButtonPlay = (ImageButton)findViewById(R.id.buttonPlay);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        button     = (Button)findViewById(R.id.button);
        buttonPracticeTestSubmit  = (Button)findViewById(R.id.buttonPracticeTestSubmit);
        imageButtonPause = (ImageButton)findViewById(R.id.buttonPause);

        linearLayoutFrame1 = (LinearLayout)findViewById(R.id.frame1);
        linearLayoutFrame2 = (LinearLayout)findViewById(R.id.frame2);
        linearLayoutFrame3 = (LinearLayout)findViewById(R.id.framePracttcieTest);

        seekbar = (SeekBar)findViewById(R.id.seekbar);
        seekbar.setClickable(false);
        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        textViewStart = (TextView)findViewById(R.id.textViewStartTime);
        textViewStop = (TextView)findViewById(R.id.textViewStopTime);
        textViewInstruction = (TextView)findViewById(R.id.textViewInstruction);
        imageButtonPause.setVisibility(View.GONE);

        radioButton1 = (RadioButton)findViewById(R.id.practiceTestRadio1);
        radioButton2 = (RadioButton)findViewById(R.id.practiceTestRadio2);
        radioButton3 = (RadioButton)findViewById(R.id.practiceTestRadio3);
        radioButton4 = (RadioButton)findViewById(R.id.practiceTestRadio4);


        imageView = (ImageView)findViewById(R.id.imageView);

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String member_id = bb.getString("member_id", "member_id");

        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");

        url = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        practicetest = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=29&testcode="+test_code;
        part1 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=20&testcode="+test_code;
        part2 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=19&testcode="+test_code;
        part3 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=18&testcode="+test_code;
        part4 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=17&testcode="+test_code;
        part5 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=16&testcode="+test_code;
        part6 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=15&testcode="+test_code;

        sendRequest();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayoutFrame2.setVisibility(View.GONE);
                linearLayoutFrame1.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);

            }
        });
        buttonPracticeTestSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ListeningTestQuestionActivity.this,LISTENING_part2.class);
                startActivity(intent1);
            }
        });
        buttonNext.setVisibility(View.GONE);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (url.equals(practicetest))
                {
                    linearLayoutFrame3.setVisibility(View.VISIBLE);
                    linearLayoutFrame1.setVisibility(View.GONE);
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        Toast.makeText(ListeningTestQuestionActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    linearLayoutFrame1.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        Toast.makeText(ListeningTestQuestionActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imageButtonPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    Toast.makeText(ListeningTestQuestionActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mediaPlayer.start();
                    imageButtonPause.setImageResource(R.drawable.ic_pause_black_24dp);
                    Toast.makeText(ListeningTestQuestionActivity.this, "Play", Toast.LENGTH_SHORT).show();
                }


            }
        });
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButtonPlay.setVisibility(View.GONE);
                imageButtonPause.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.VISIBLE);
                if (url.equals(practicetest))
                {
                    new PlayMusic().execute();
                }
                if (url.equals(part1))
                {
                    new PlayMusic().execute();
                }
                if (url.equals(part2))
                {
                    new PlayMusic().execute();
                }
                if (url.equals(part3))
                {
                    new PlayMusic().execute();
                }
                if (url.equals(part4))
                {
                    new PlayMusic().execute();
                }
                if (url.equals(part5))
                {
                    new PlayMusic().execute();
                }
                if (url.equals(part6))
                {
                    new PlayMusic().execute();
                }

            }
        });

    }

    private Runnable UpdateAudioTime = new Runnable() {
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


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        finishAndRemoveTask();
                        //close();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            String tokenCode = obj.getString("tdcode");
                            String a = "http://online.celpip.biz/api/testnewprogress?token="+tokenCode;
                            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("tokenCode", tokenCode);
                            edit.commit();
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(ListeningTestQuestionActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                    final ProgressDialog loading = ProgressDialog.show(ListeningTestQuestionActivity.this,"Loading","Please wait...",false,false);
                                    StringRequest stringRequest = new StringRequest(a,
                                            new com.android.volley.Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    try {
                                                        JSONObject obj = new JSONObject(response);
                                                        int abc = Integer.parseInt(obj.getString("response"));
                                                        if (abc !=1 )
                                                        {
                                                            loading.dismiss();
                                                            Toast.makeText(ListeningTestQuestionActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                                                        }
                                                        else if (abc == 1)
                                                        {
                                                            if (url.equals(part1))
                                                            {
                                                                showJSONPart1(response);
                                                                image = "https://online.celpip.biz/uploads/part1_listening/"+jsonHolderListingpart1.l1_practice_01_img;
                                                                Log.e("===imageURL",image);
                                                                Glide
                                                                        .with(ListeningTestQuestionActivity.this)
                                                                        .load(image)
                                                                        .listener(new RequestListener<Drawable>() {
                                                                            @Override
                                                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                                                Toast.makeText(ListeningTestQuestionActivity.this, "Problem Loading the Image!!!!", Toast.LENGTH_SHORT).show();
                                                                                loading.dismiss();
                                                                                return false;
                                                                            }

                                                                            @Override
                                                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                                                loading.dismiss();
                                                                                return false;
                                                                            }
                                                                        })
                                                                        .into(imageView);
                                                                linearLayoutFrame2.setVisibility(View.VISIBLE);
                                                                linearLayoutFrame1.setVisibility(View.GONE);
                                                                textViewInstruction.setText("Instruction :\n"+jsonHolderListingpart1.l1_practice_01_text);

                                                            }
                                                            else if(url.equals(part2))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart2(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part3))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart2(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part4))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart2(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part5))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart2(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part6))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart2(response);
                                                                loading.dismiss();
                                                            }
                                                            else
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPracticeTest(response);
                                                                radioButton1.setText(jsonHolderListingPracticeTest.option1);
                                                                radioButton2.setText(jsonHolderListingPracticeTest.option2);
                                                                radioButton3.setText(jsonHolderListingPracticeTest.option3);
                                                                radioButton4.setText(jsonHolderListingPracticeTest.option4);
                                                                seekbar.setClickable(false);
                                                                loading.dismiss();
                                                            }
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                        loading.dismiss();
                                                    }
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                                    loading.dismiss();
                                                }
                                            });

                                    RequestQueue requestQueue = Volley.newRequestQueue(ListeningTestQuestionActivity.this);
                                    requestQueue.add(stringRequest);
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONPart1(String json) {
        JsonDataHandlerListeningPart1 jsonHolderListingpart1 = new JsonDataHandlerListeningPart1(json);
        jsonHolderListingpart1.parseJSON();

        ListeningTestPart1QuestionAdapter ca = new ListeningTestPart1QuestionAdapter(this,jsonHolderListingpart1.id,jsonHolderListingpart1.test_code,jsonHolderListingpart1.l1_q1_audio,jsonHolderListingpart1.l1_q1_option1,jsonHolderListingpart1.l1_q1_option2,jsonHolderListingpart1.l1_q1_option3,jsonHolderListingpart1.l1_q1_option4,jsonHolderListingpart1.l1_q2_audio,jsonHolderListingpart1.l1_q2_option1,jsonHolderListingpart1.l1_q2_option2,jsonHolderListingpart1.l1_q2_option3,jsonHolderListingpart1.l1_q2_option4,jsonHolderListingpart1.l1_q3_audio,jsonHolderListingpart1.l1_q3_option1,jsonHolderListingpart1.l1_q3_option2,jsonHolderListingpart1.l1_q3_option3,jsonHolderListingpart1.l1_q3_option4,jsonHolderListingpart1.l1_q4_audio,jsonHolderListingpart1.l1_q4_option1,jsonHolderListingpart1.l1_q4_option2,jsonHolderListingpart1.l1_q4_option3,jsonHolderListingpart1.l1_q4_option4,jsonHolderListingpart1.l1_q5_audio,jsonHolderListingpart1.l1_q5_option1,jsonHolderListingpart1.l1_q5_option2,jsonHolderListingpart1.l1_q5_option3,jsonHolderListingpart1.l1_q5_option4,jsonHolderListingpart1.l1_q6_audio,jsonHolderListingpart1.l1_q6_option1,jsonHolderListingpart1.l1_q6_option2,jsonHolderListingpart1.l1_q6_option3,jsonHolderListingpart1.l1_q6_option4,jsonHolderListingpart1.l1_q7_audio,jsonHolderListingpart1.l1_q7_option1,jsonHolderListingpart1.l1_q7_option2,jsonHolderListingpart1.l1_q7_option3,jsonHolderListingpart1.l1_q7_option4,jsonHolderListingpart1.l1_q8_audio,jsonHolderListingpart1.l1_q8_option1,jsonHolderListingpart1.l1_q8_option2,jsonHolderListingpart1.l1_q8_option3,jsonHolderListingpart1.l1_q8_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }
    private void showJSONPart2(String json) {
        JsonDataHandlerListeningPart2 jsonHolderListingpart2 = new JsonDataHandlerListeningPart2(json);
        jsonHolderListingpart2.parseJSON();

        ListeningTestPart2QuestionAdapter ca = new ListeningTestPart2QuestionAdapter(this,jsonHolderListingpart2.id,jsonHolderListingpart2.test_code,jsonHolderListingpart2.converstaion_1_audio,jsonHolderListingpart2.q1_audio,jsonHolderListingpart2.q1_option1,jsonHolderListingpart2.q1_option2,jsonHolderListingpart2.q1_option3,jsonHolderListingpart2.q1_option4,jsonHolderListingpart2.q2_audio,jsonHolderListingpart2.q2_option1,jsonHolderListingpart2.q2_option2,jsonHolderListingpart2.q2_option3,jsonHolderListingpart2.q2_option4,jsonHolderListingpart2.q3_audio,jsonHolderListingpart2.q3_option1,jsonHolderListingpart2.q3_option2,jsonHolderListingpart2.q3_option3,jsonHolderListingpart2.q3_option4,jsonHolderListingpart2.q4_audio,jsonHolderListingpart2.q4_option1,jsonHolderListingpart2.q4_option2,jsonHolderListingpart2.q4_option3,jsonHolderListingpart2.q4_option4,jsonHolderListingpart2.q5_audio,jsonHolderListingpart2.q5_option1,jsonHolderListingpart2.q5_option2,jsonHolderListingpart2.q5_option3,jsonHolderListingpart2.q5_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }

    private void showJSONPracticeTest(String json) {
        JsonDataHandlerPracticeTestListening jsonHolderListingPracticeTest = new JsonDataHandlerPracticeTestListening(json);
        jsonHolderListingPracticeTest.parseJSON();

        ListeningTestPart2QuestionAdapter ca = new ListeningTestPart2QuestionAdapter(this,jsonHolderListingpart2.id,jsonHolderListingpart2.test_code,jsonHolderListingpart2.converstaion_1_audio,jsonHolderListingpart2.q1_audio,jsonHolderListingpart2.q1_option1,jsonHolderListingpart2.q1_option2,jsonHolderListingpart2.q1_option3,jsonHolderListingpart2.q1_option4,jsonHolderListingpart2.q2_audio,jsonHolderListingpart2.q2_option1,jsonHolderListingpart2.q2_option2,jsonHolderListingpart2.q2_option3,jsonHolderListingpart2.q2_option4,jsonHolderListingpart2.q3_audio,jsonHolderListingpart2.q3_option1,jsonHolderListingpart2.q3_option2,jsonHolderListingpart2.q3_option3,jsonHolderListingpart2.q3_option4,jsonHolderListingpart2.q4_audio,jsonHolderListingpart2.q4_option1,jsonHolderListingpart2.q4_option2,jsonHolderListingpart2.q4_option3,jsonHolderListingpart2.q4_option4,jsonHolderListingpart2.q5_audio,jsonHolderListingpart2.q5_option1,jsonHolderListingpart2.q5_option2,jsonHolderListingpart2.q5_option3,jsonHolderListingpart2.q5_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }

    public void audiopart1()
    {
        String audio_player = "https://online.celpip.biz/uploads/part1_listening/"+jsonHolderListingpart1.l1_converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.GONE);
                imageButtonPause.setVisibility(View.GONE);
            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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
    public void audiopart2()
    {
        String audio_player = "https://online.celpip.biz/uploads/part2_listening/"+jsonHolderListingpart2.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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

    public void audiopart3()
    {
        String audio_player = "https://online.celpip.biz/uploads/part3_listening/"+jsonHolderListingpart2.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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

    public void audiopart4()
    {
        String audio_player = "https://online.celpip.biz/uploads/part4_listening/"+jsonHolderListingpart2.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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

    public void audiopart5()
    {
        String audio_player = "https://online.celpip.biz/uploads/part5_listening/"+jsonHolderListingpart2.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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

    public void audiopart6()
    {
        String audio_player = "https://online.celpip.biz/uploads/part6_listening/"+jsonHolderListingpart2.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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
    public void audiopractice()
    {
        String audio_player = "https://online.celpip.biz/uploads/listening_practiceTask/"+jsonHolderListingPracticeTest.mp3URL;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                mp.setLooping(false);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
                imageButtonPlay.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);

            }
        });
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 0;
        }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateAudioTime,100);
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

    class  PlayMusic extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            loadingAudio.setMessage("Loading Audio");
            loadingAudio.show();
            loadingAudio.setCancelable(false);
            loadingAudio.setIndeterminate(false);
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... unused) {


            //your background process
            if (url.equals(part1))
            {
                audiopart1();
            }
            if (url.equals(part2))
            {
                audiopart2();
            }
            if (url.equals(part3))
            {
                audiopart3();
            }
            if (url.equals(part4))
            {
                audiopart4();
            }
            if (url.equals(part5))
            {
                audiopart5();
            }
            if (url.equals(part6))
            {
                audiopart6();
            }
            if (url.equals(practicetest))
            {
                audiopractice();
            }

            return (null);
        }

        protected void onPostExecute(Void unused) {

            //here you can call your mp.prepare();
            loadingAudio.dismiss();
        }

    }

}
