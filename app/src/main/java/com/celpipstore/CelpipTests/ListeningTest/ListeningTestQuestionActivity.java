package com.celpipstore.CelpipTests.ListeningTest;

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
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPart1QuestionAdapter;
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPart2QuestionAdapter;
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPart3QuestionAdapter;
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPart4QuestionAdapter;
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPart5QuestionAdapter;
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPart6QuestionAdapter;
import com.celpipstore.Adapter.ListeningTestAdapter.ListeningTestPracticeTestAdapter;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerListeningPart1;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerListeningPart2;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerListeningPart3;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerListeningPart4;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerListeningPart5;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerListeningPart6;
import com.celpipstore.JsonData.ListeningTest.JsonDataHandlerPracticeTestListening;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ListeningTestQuestionActivity extends AppCompatActivity {

    private ListView listView;
    private String url,practicetest,part1,part2,part3,part4,part5,part6;
    private String image;
    private ImageView imageView;
    private VideoView videoView;
    private ProgressBar progressBar;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private SeekBar seekbar;
    private ImageButton imageButtonPlay,imageButtonPause;
    private Button buttonNext,button,buttonPracticeTestSubmit,buttonQuestionsPart5;
    private LinearLayout linearLayoutFrame1,linearLayoutFrame2,linearLayoutFrame3,linearLayoutFrame4;
    private TextView textViewStart,textViewStop,textViewInstruction;
    private static int oneTimeOnly = 0;
    private ProgressDialog loadingAudio;
    private String tokenCode,userAnswerQuestion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_test_question);
        MediaController mediaController = new MediaController(this);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);
        progressBar = findViewById(R.id.progrss);
        loadingAudio = new ProgressDialog(ListeningTestQuestionActivity.this);
        listView = findViewById(R.id.listView);
        imageButtonPlay = findViewById(R.id.buttonPlay);
        buttonNext = findViewById(R.id.buttonNext);
        button     = findViewById(R.id.button);
        buttonPracticeTestSubmit  = findViewById(R.id.buttonPracticeTestSubmit);
        buttonQuestionsPart5  = findViewById(R.id.buttonQuestions);
        imageButtonPause = findViewById(R.id.buttonPause);
        linearLayoutFrame1 = findViewById(R.id.frame1);
        linearLayoutFrame2 = findViewById(R.id.frame2);
        linearLayoutFrame3 = findViewById(R.id.framePracttcieTest);
        linearLayoutFrame4 = findViewById(R.id.frame3);
        seekbar = findViewById(R.id.seekbar);
        seekbar.setClickable(false);
        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        textViewStart = findViewById(R.id.textViewStartTime);
        textViewStop = findViewById(R.id.textViewStopTime);
        textViewInstruction = findViewById(R.id.textViewInstruction);
        imageButtonPause.setVisibility(View.GONE);
        radioButton1 = findViewById(R.id.practiceTestRadio1);
        radioButton2 = findViewById(R.id.practiceTestRadio2);
        radioButton3 = findViewById(R.id.practiceTestRadio3);
        radioButton4 = findViewById(R.id.practiceTestRadio4);
        imageView = findViewById(R.id.imageView);
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        tokenCode = bb.getString("tokenCode", "tokenCode");
        String member_id = bb.getString("member_id", "member_id");
        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        url = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        practicetest = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=29&testcode="+test_code;
        part1 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=20&testcode="+test_code;
        part2 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=19&testcode="+test_code;
        part3 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=18&testcode="+test_code;
        part4 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=17&testcode="+test_code;
        part5 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=16&testcode="+test_code;
        part6 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=15&testcode="+test_code;
        Log.e("===APIQUESTIONS",url);
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
                submitAnswerPracticeTest();
            }
        });
        buttonNext.setVisibility(View.GONE);
        buttonQuestionsPart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutFrame4.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (url.equals(practicetest))
                {
                    linearLayoutFrame1.setVisibility(View.GONE);
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
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
                    buttonNext.setVisibility(View.GONE);
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
                    linearLayoutFrame1.setVisibility(View.GONE);
                    linearLayoutFrame2.setVisibility(View.GONE);
                    linearLayoutFrame4.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    new PlayMusic().execute();
                }
                if (url.equals(part6))
                {
                    new PlayMusic().execute();
                }
            }
        });
        connectionChecker();
    }

    private void connectionChecker() {
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            sendRequest();
        }
        else
        {
            AlertDialog alertbox = new AlertDialog.Builder(this)
                    .setMessage("CHECK YOUR INTERNET CONNECTION?")
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
            Toast.makeText(ListeningTestQuestionActivity.this, "NETWORK ISSUE!!", Toast.LENGTH_LONG).show();
        }
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
            finalTime = mediaPlayer.getDuration();
            textViewStop.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    finalTime)))
            );
            seekbar.setProgress((int)startTime);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
            }
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
                .setMessage("DO YOU WANT TO QUIT?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        if (mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"LOADING","PLEASE WAIT!!",false,false);
        StringRequest stringRequest = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            String tokenCode = obj.getString("tdcode");
                            String a = "http://demo.celpip.biz/api/testnewprogress?token="+tokenCode;
                            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("tokenCode", tokenCode);
                            edit.commit();
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(ListeningTestQuestionActivity.this, "NO LISTENING TEST AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                    final ProgressDialog loading = ProgressDialog.show(ListeningTestQuestionActivity.this,"LOADING","PLEASE WAIT!!",false,false);
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
                                                            Toast.makeText(ListeningTestQuestionActivity.this, "LISTENING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                                                        }
                                                        else if (abc == 1)
                                                        {
                                                            if (url.equals(part1))
                                                            {
                                                                showJSONPart1(response);
                                                                image = "https://demo.celpip.biz/uploads/part1_listening/"+ JsonDataHandlerListeningPart1.l1_practice_01_img;
                                                                Glide
                                                                        .with(ListeningTestQuestionActivity.this)
                                                                        .load(image)
                                                                        .listener(new RequestListener<Drawable>() {
                                                                            @Override
                                                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                                                Toast.makeText(ListeningTestQuestionActivity.this, "SOME PROBLEM IN IMAGE!!!!", Toast.LENGTH_SHORT).show();
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
                                                                textViewInstruction.setText("Instruction :\n"+ JsonDataHandlerListeningPart1.l1_practice_01_text);
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
                                                                showJSONPart3(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part4))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart4(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part5))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart5(response);
                                                                loading.dismiss();
                                                            }
                                                            else if (url.equals(part6))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart6(response);
                                                                loading.dismiss();
                                                            }
                                                            else
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPracticeTest(response);
                                                                loading.dismiss();
                                                                radioButton1.setText(JsonDataHandlerPracticeTestListening.option1);
                                                                radioButton2.setText(JsonDataHandlerPracticeTestListening.option2);
                                                                radioButton3.setText(JsonDataHandlerPracticeTestListening.option3);
                                                                radioButton4.setText(JsonDataHandlerPracticeTestListening.option4);
                                                                seekbar.setClickable(false);
                                                                if (radioButton1.isChecked())
                                                                {
                                                                    userAnswerQuestion1 = radioButton1.getText().toString();
                                                                }
                                                                if (radioButton2.isChecked())
                                                                {
                                                                    userAnswerQuestion1 = radioButton2.getText().toString();
                                                                }
                                                                if (radioButton3.isChecked())
                                                                {
                                                                    userAnswerQuestion1 = radioButton3.getText().toString();
                                                                }
                                                                if (radioButton4.isChecked())
                                                                {
                                                                    userAnswerQuestion1 = radioButton4.getText().toString();
                                                                }
                                                            }
                                                        }
                                                    }
                                                    catch (JSONException e) {
                                                        e.printStackTrace();
                                                        loading.dismiss();
                                                        recreate();
                                                        Toast.makeText(getApplicationContext(), "Json Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(getApplicationContext(),"Response "+error.getMessage(), Toast.LENGTH_LONG).show();
                                                    loading.dismiss();
                                                }
                                            });

                                    RequestQueue requestQueue = Volley.newRequestQueue(ListeningTestQuestionActivity.this);
                                    requestQueue.add(stringRequest);
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), " Json Exception "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(),"response "+ error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONPart1(String json) {
        JsonDataHandlerListeningPart1 jsonHolderListingpart1 = new JsonDataHandlerListeningPart1(json);
        jsonHolderListingpart1.parseJSON();
        ListeningTestPart1QuestionAdapter ca = new ListeningTestPart1QuestionAdapter(this, JsonDataHandlerListeningPart1.id, JsonDataHandlerListeningPart1.test_code, JsonDataHandlerListeningPart1.l1_q1_audio, JsonDataHandlerListeningPart1.l1_q1_option1, JsonDataHandlerListeningPart1.l1_q1_option2, JsonDataHandlerListeningPart1.l1_q1_option3, JsonDataHandlerListeningPart1.l1_q1_option4, JsonDataHandlerListeningPart1.l1_q2_audio, JsonDataHandlerListeningPart1.l1_q2_option1, JsonDataHandlerListeningPart1.l1_q2_option2, JsonDataHandlerListeningPart1.l1_q2_option3, JsonDataHandlerListeningPart1.l1_q2_option4, JsonDataHandlerListeningPart1.l1_q3_audio, JsonDataHandlerListeningPart1.l1_q3_option1, JsonDataHandlerListeningPart1.l1_q3_option2, JsonDataHandlerListeningPart1.l1_q3_option3, JsonDataHandlerListeningPart1.l1_q3_option4, JsonDataHandlerListeningPart1.l1_q4_audio, JsonDataHandlerListeningPart1.l1_q4_option1, JsonDataHandlerListeningPart1.l1_q4_option2, JsonDataHandlerListeningPart1.l1_q4_option3, JsonDataHandlerListeningPart1.l1_q4_option4, JsonDataHandlerListeningPart1.l1_q5_audio, JsonDataHandlerListeningPart1.l1_q5_option1, JsonDataHandlerListeningPart1.l1_q5_option2, JsonDataHandlerListeningPart1.l1_q5_option3, JsonDataHandlerListeningPart1.l1_q5_option4, JsonDataHandlerListeningPart1.l1_q6_audio, JsonDataHandlerListeningPart1.l1_q6_option1, JsonDataHandlerListeningPart1.l1_q6_option2, JsonDataHandlerListeningPart1.l1_q6_option3, JsonDataHandlerListeningPart1.l1_q6_option4, JsonDataHandlerListeningPart1.l1_q7_audio, JsonDataHandlerListeningPart1.l1_q7_option1, JsonDataHandlerListeningPart1.l1_q7_option2, JsonDataHandlerListeningPart1.l1_q7_option3, JsonDataHandlerListeningPart1.l1_q7_option4, JsonDataHandlerListeningPart1.l1_q8_audio, JsonDataHandlerListeningPart1.l1_q8_option1, JsonDataHandlerListeningPart1.l1_q8_option2, JsonDataHandlerListeningPart1.l1_q8_option3, JsonDataHandlerListeningPart1.l1_q8_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart2(String json) {
        JsonDataHandlerListeningPart2 jsonHolderListingpart2 = new JsonDataHandlerListeningPart2(json);
        jsonHolderListingpart2.parseJSON();
        ListeningTestPart2QuestionAdapter ca = new ListeningTestPart2QuestionAdapter(this, JsonDataHandlerListeningPart2.id, JsonDataHandlerListeningPart2.test_code, JsonDataHandlerListeningPart2.converstaion_1_audio, JsonDataHandlerListeningPart2.q1_audio, JsonDataHandlerListeningPart2.q1_option1, JsonDataHandlerListeningPart2.q1_option2, JsonDataHandlerListeningPart2.q1_option3, JsonDataHandlerListeningPart2.q1_option4, JsonDataHandlerListeningPart2.q2_audio, JsonDataHandlerListeningPart2.q2_option1, JsonDataHandlerListeningPart2.q2_option2, JsonDataHandlerListeningPart2.q2_option3, JsonDataHandlerListeningPart2.q2_option4, JsonDataHandlerListeningPart2.q3_audio, JsonDataHandlerListeningPart2.q3_option1, JsonDataHandlerListeningPart2.q3_option2, JsonDataHandlerListeningPart2.q3_option3, JsonDataHandlerListeningPart2.q3_option4, JsonDataHandlerListeningPart2.q4_audio, JsonDataHandlerListeningPart2.q4_option1, JsonDataHandlerListeningPart2.q4_option2, JsonDataHandlerListeningPart2.q4_option3, JsonDataHandlerListeningPart2.q4_option4, JsonDataHandlerListeningPart2.q5_audio, JsonDataHandlerListeningPart2.q5_option1, JsonDataHandlerListeningPart2.q5_option2, JsonDataHandlerListeningPart2.q5_option3, JsonDataHandlerListeningPart2.q5_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart3(String json) {
        JsonDataHandlerListeningPart3 jsonHolderListingpart3 = new JsonDataHandlerListeningPart3(json);
        jsonHolderListingpart3.parseJSON();
        ListeningTestPart3QuestionAdapter ca = new ListeningTestPart3QuestionAdapter(this, JsonDataHandlerListeningPart3.id, JsonDataHandlerListeningPart3.test_code, JsonDataHandlerListeningPart3.converstaion_1_audio, JsonDataHandlerListeningPart3.q1_audio, JsonDataHandlerListeningPart3.q1_option1, JsonDataHandlerListeningPart3.q1_option2, JsonDataHandlerListeningPart3.q1_option3, JsonDataHandlerListeningPart3.q1_option4, JsonDataHandlerListeningPart3.q2_audio, JsonDataHandlerListeningPart3.q2_option1, JsonDataHandlerListeningPart3.q2_option2, JsonDataHandlerListeningPart3.q2_option3, JsonDataHandlerListeningPart3.q2_option4, JsonDataHandlerListeningPart3.q3_audio, JsonDataHandlerListeningPart3.q3_option1, JsonDataHandlerListeningPart3.q3_option2, JsonDataHandlerListeningPart3.q3_option3, JsonDataHandlerListeningPart3.q3_option4, JsonDataHandlerListeningPart3.q4_audio, JsonDataHandlerListeningPart3.q4_option1, JsonDataHandlerListeningPart3.q4_option2, JsonDataHandlerListeningPart3.q4_option3, JsonDataHandlerListeningPart3.q4_option4, JsonDataHandlerListeningPart3.q5_audio, JsonDataHandlerListeningPart3.q5_option1, JsonDataHandlerListeningPart3.q5_option2, JsonDataHandlerListeningPart3.q5_option3, JsonDataHandlerListeningPart3.q5_option4,JsonDataHandlerListeningPart3.q6_audio,JsonDataHandlerListeningPart3.q6_option1,JsonDataHandlerListeningPart3.q6_option2,JsonDataHandlerListeningPart3.q6_option3,JsonDataHandlerListeningPart3.q6_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart4(String json) {
        JsonDataHandlerListeningPart4 jsonHolderListingpart4 = new JsonDataHandlerListeningPart4(json);
        jsonHolderListingpart4.parseJSON();
        ListeningTestPart4QuestionAdapter ca = new ListeningTestPart4QuestionAdapter(this, JsonDataHandlerListeningPart4.id, JsonDataHandlerListeningPart4.test_code, JsonDataHandlerListeningPart4.converstaion_1_audio, JsonDataHandlerListeningPart4.q1_question, JsonDataHandlerListeningPart4.q2_question, JsonDataHandlerListeningPart4.q3_question, JsonDataHandlerListeningPart4.q4_question, JsonDataHandlerListeningPart4.q5_question, JsonDataHandlerListeningPart4.q1_option1, JsonDataHandlerListeningPart4.q1_option2, JsonDataHandlerListeningPart4.q1_option3, JsonDataHandlerListeningPart4.q1_option4, JsonDataHandlerListeningPart4.q2_option1, JsonDataHandlerListeningPart4.q2_option2, JsonDataHandlerListeningPart4.q2_option3, JsonDataHandlerListeningPart4.q2_option4, JsonDataHandlerListeningPart4.q3_option1, JsonDataHandlerListeningPart4.q3_option2, JsonDataHandlerListeningPart4.q3_option3, JsonDataHandlerListeningPart4.q3_option4, JsonDataHandlerListeningPart4.q4_option1, JsonDataHandlerListeningPart4.q4_option2, JsonDataHandlerListeningPart4.q4_option3, JsonDataHandlerListeningPart4.q4_option4, JsonDataHandlerListeningPart4.q5_option1, JsonDataHandlerListeningPart4.q5_option2, JsonDataHandlerListeningPart4.q5_option3, JsonDataHandlerListeningPart4.q5_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart5(String json) {
        JsonDataHandlerListeningPart5 jsonHolderListingpart5 = new JsonDataHandlerListeningPart5(json);
        jsonHolderListingpart5.parseJSON();
        ListeningTestPart5QuestionAdapter ca = new ListeningTestPart5QuestionAdapter(this, JsonDataHandlerListeningPart5.id, JsonDataHandlerListeningPart5.test_code, JsonDataHandlerListeningPart5.conversation_1_video, JsonDataHandlerListeningPart5.q1_question, JsonDataHandlerListeningPart5.q2_question, JsonDataHandlerListeningPart5.q3_question, JsonDataHandlerListeningPart5.q4_question, JsonDataHandlerListeningPart5.q5_question, JsonDataHandlerListeningPart5.q6_question, JsonDataHandlerListeningPart5.q7_question, JsonDataHandlerListeningPart5.q8_question, JsonDataHandlerListeningPart5.q1_option1, JsonDataHandlerListeningPart5.q1_option2, JsonDataHandlerListeningPart5.q1_option3, JsonDataHandlerListeningPart5.q1_option4, JsonDataHandlerListeningPart5.q2_option1, JsonDataHandlerListeningPart5.q2_option2, JsonDataHandlerListeningPart5.q2_option3, JsonDataHandlerListeningPart5.q2_option4, JsonDataHandlerListeningPart5.q3_option1, JsonDataHandlerListeningPart5.q3_option2, JsonDataHandlerListeningPart5.q3_option3, JsonDataHandlerListeningPart5.q3_option4, JsonDataHandlerListeningPart5.q4_option1, JsonDataHandlerListeningPart5.q4_option2, JsonDataHandlerListeningPart5.q4_option3, JsonDataHandlerListeningPart5.q4_option4, JsonDataHandlerListeningPart5.q5_option1, JsonDataHandlerListeningPart5.q5_option2, JsonDataHandlerListeningPart5.q5_option3, JsonDataHandlerListeningPart5.q5_option4, JsonDataHandlerListeningPart5.q6_option1, JsonDataHandlerListeningPart5.q6_option2, JsonDataHandlerListeningPart5.q6_option3, JsonDataHandlerListeningPart5.q6_option4, JsonDataHandlerListeningPart5.q7_option1, JsonDataHandlerListeningPart5.q7_option2, JsonDataHandlerListeningPart5.q7_option3, JsonDataHandlerListeningPart5.q7_option4, JsonDataHandlerListeningPart5.q8_option1, JsonDataHandlerListeningPart5.q8_option2, JsonDataHandlerListeningPart5.q8_option3, JsonDataHandlerListeningPart5.q8_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPart6(String json) {
        JsonDataHandlerListeningPart6 jsonHolderListingpart6 = new JsonDataHandlerListeningPart6(json);
        jsonHolderListingpart6.parseJSON();
        ListeningTestPart6QuestionAdapter ca = new ListeningTestPart6QuestionAdapter(this, JsonDataHandlerListeningPart6.id, JsonDataHandlerListeningPart6.test_code, JsonDataHandlerListeningPart6.conversation_1_audio, JsonDataHandlerListeningPart6.q1_question, JsonDataHandlerListeningPart6.q2_question, JsonDataHandlerListeningPart6.q3_question, JsonDataHandlerListeningPart6.q4_question, JsonDataHandlerListeningPart6.q5_question, JsonDataHandlerListeningPart6.q6_question, JsonDataHandlerListeningPart6.q1_option1, JsonDataHandlerListeningPart6.q1_option2, JsonDataHandlerListeningPart6.q1_option3, JsonDataHandlerListeningPart6.q1_option4, JsonDataHandlerListeningPart6.q2_option1, JsonDataHandlerListeningPart6.q2_option2, JsonDataHandlerListeningPart6.q2_option3, JsonDataHandlerListeningPart6.q2_option4, JsonDataHandlerListeningPart6.q3_option1, JsonDataHandlerListeningPart6.q3_option2, JsonDataHandlerListeningPart6.q3_option3, JsonDataHandlerListeningPart6.q3_option4, JsonDataHandlerListeningPart6.q4_option1, JsonDataHandlerListeningPart6.q4_option2, JsonDataHandlerListeningPart6.q4_option3, JsonDataHandlerListeningPart6.q4_option4, JsonDataHandlerListeningPart6.q5_option1, JsonDataHandlerListeningPart6.q5_option2, JsonDataHandlerListeningPart6.q5_option3, JsonDataHandlerListeningPart6.q5_option4, JsonDataHandlerListeningPart6.q6_option1, JsonDataHandlerListeningPart6.q6_option2, JsonDataHandlerListeningPart6.q6_option3, JsonDataHandlerListeningPart6.q6_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
    private void showJSONPracticeTest(String json) {
        JsonDataHandlerPracticeTestListening jsonHolderListingPracticeTest = new JsonDataHandlerPracticeTestListening(json);
        jsonHolderListingPracticeTest.parseJSON();
        ListeningTestPracticeTestAdapter ca = new ListeningTestPracticeTestAdapter(this, JsonDataHandlerPracticeTestListening.id,JsonDataHandlerPracticeTestListening.test_code,JsonDataHandlerPracticeTestListening.mp3URL,JsonDataHandlerPracticeTestListening.option1,JsonDataHandlerPracticeTestListening.option2,JsonDataHandlerPracticeTestListening.option3,JsonDataHandlerPracticeTestListening.option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }

    public void audiopart1()
    {
        String audio_player = "https://demo.celpip.biz/uploads/part1_listening/"+ JsonDataHandlerListeningPart1.l1_converstaion_1_audio;
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
    }
    public void audiopart2()
    {
        String audio_player = "https://demo.celpip.biz/uploads/part2_listening/"+ JsonDataHandlerListeningPart2.converstaion_1_audio;
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
    }
    public void audiopart3()
    {
        String audio_player = "https://demo.celpip.biz/uploads/part3_listening/"+ JsonDataHandlerListeningPart3.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
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
    }
    public void audiopart4()
    {
        String audio_player = "https://demo.celpip.biz/uploads/part4_listening/"+ JsonDataHandlerListeningPart4.converstaion_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
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
    }
    public void audiopart5()
    {
        String audio_player = "https://demo.celpip.biz/uploads/part5_listening/"+ JsonDataHandlerListeningPart5.conversation_1_video;
        Log.e("===link",audio_player);
        Uri uri=Uri.parse(audio_player);
        videoView.setVideoPath("https://demo.celpip.biz/uploads/part5_listening/"+ JsonDataHandlerListeningPart5.conversation_1_video);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                buttonQuestionsPart5.setVisibility(View.VISIBLE);
//                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                progressBar.setVisibility(View.GONE);
                buttonQuestionsPart5.setVisibility(View.VISIBLE);
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }
    public void audiopart6()
    {
        String audio_player = "https://demo.celpip.biz/uploads/part6_listening/"+ JsonDataHandlerListeningPart6.conversation_1_audio;
        mediaPlayer = MediaPlayer.create(ListeningTestQuestionActivity.this,Uri.parse(audio_player));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
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
    }
    public void audiopractice()
    {
        String audio_player = "https://demo.celpip.biz/uploads/listening_practiceTask/"+ JsonDataHandlerPracticeTestListening.mp3URL;
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
                linearLayoutFrame1.setVisibility(View.GONE);
                linearLayoutFrame3.setVisibility(View.VISIBLE);
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
    }

    private class  PlayMusic extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            loadingAudio.setMessage("LOADING AUDIO!!");
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
    private void submitAnswerPracticeTest(){
        radioButton1.setText(JsonDataHandlerPracticeTestListening.option1);
        radioButton2.setText(JsonDataHandlerPracticeTestListening.option2);
        radioButton3.setText(JsonDataHandlerPracticeTestListening.option3);
        radioButton4.setText(JsonDataHandlerPracticeTestListening.option4);
        seekbar.setClickable(false);
        if (radioButton1.isChecked())
        {
            userAnswerQuestion1 = "option1";
        }
        if (radioButton2.isChecked())
        {
            userAnswerQuestion1 = "option2";
        }
        if (radioButton3.isChecked())
        {
            userAnswerQuestion1 = "option3";
        }
        if (radioButton4.isChecked())
        {
            userAnswerQuestion1 = "option4";
        }
        final String urlForSubmittingOptions = "http://demo.celpip.biz/api/practiceTaskSubmit?token="+tokenCode+"&q1_response="+userAnswerQuestion1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "SUBMITTED", Toast.LENGTH_SHORT).show();
                        Log.e("===Answer Submitting",urlForSubmittingOptions);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR IN SUBMITTING THE ANSWER!!"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("q1_response", userAnswerQuestion1);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Intent intent = new Intent(getApplicationContext() , LISTENING_part2.class);
        startActivity(intent);
    }
}
