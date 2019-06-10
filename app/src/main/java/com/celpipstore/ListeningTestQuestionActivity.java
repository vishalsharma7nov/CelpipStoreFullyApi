package com.celpipstore;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ListeningTestQuestionActivity extends AppCompatActivity {

    ListView listView;
    String url;

    JsonDataHandlerListeningPart2 jsonHolderListing;
    MediaPlayer mediaPlayer;
    double startTime = 0;
    double finalTime = 0;

    Handler myHandler = new Handler();;
    int forwardTime = 5000;
    int backwardTime = 5000;
    SeekBar seekbar;
    ImageButton imageButtonPlay,imageButtonPause;
    Button buttonNext;
    LinearLayout linearLayoutFrame1;
    TextView textViewStart,textViewStop,textViewPercent;
    public static int oneTimeOnly = 0;
    Uri myUri;
    ProgressDialog buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_test_question);

        listView = (ListView)findViewById(R.id.listView);
        imageButtonPlay = (ImageButton)findViewById(R.id.buttonPlay);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        imageButtonPause = (ImageButton)findViewById(R.id.buttonPause);
        linearLayoutFrame1 = (LinearLayout)findViewById(R.id.frame1);
        seekbar = (SeekBar)findViewById(R.id.seekbar);
        seekbar.setClickable(false);
        textViewStart = (TextView)findViewById(R.id.textViewStartTime);
        textViewStop = (TextView)findViewById(R.id.textViewStopTime);
        textViewPercent = (TextView)findViewById(R.id.percent);


        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String member_id = bb.getString("member_id", "member_id");

        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        url = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        sendRequest();

        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String audio_player = "https://online.celpip.biz/uploads/part2_listening/"+jsonHolderListing.converstaion_1_audio;
                Uri myUri = Uri.parse(audio_player);
                try
                {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(ListeningTestQuestionActivity.this, myUri);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.seekTo((int) startTime);

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
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mediaPlayer.pause();
                linearLayoutFrame1.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
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
                                                    String url_data = "https://online.celpip.biz/uploads/part2_listening/";
                                                    String audio_file = obj.getJSONObject("data").getString("q1_audio");
                                                    String audio = url_data+audio_file;
                                                    if (abc !=1 )
                                                    {
                                                        loading.dismiss();
                                                        Toast.makeText(ListeningTestQuestionActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if (abc == 1)
                                                    {

                                                        loading.dismiss();
                                                        showJSON(response);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
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

    private void showJSON(String json) {
        JsonDataHandlerListeningPart2 jsonHolderListing = new JsonDataHandlerListeningPart2(json);
        jsonHolderListing.parseJSON();

        ListeningTestQuesionAdapter ca = new ListeningTestQuesionAdapter(this,jsonHolderListing.id,jsonHolderListing.test_code,jsonHolderListing.converstaion_1_audio,jsonHolderListing.q1_audio,jsonHolderListing.q1_option1,jsonHolderListing.q1_option2,jsonHolderListing.q1_option3,jsonHolderListing.q1_option4,jsonHolderListing.q2_audio,jsonHolderListing.q2_option1,jsonHolderListing.q2_option2,jsonHolderListing.q2_option3,jsonHolderListing.q2_option4,jsonHolderListing.q3_audio,jsonHolderListing.q3_option1,jsonHolderListing.q3_option2,jsonHolderListing.q3_option3,jsonHolderListing.q3_option4,jsonHolderListing.q4_audio,jsonHolderListing.q4_option1,jsonHolderListing.q4_option2,jsonHolderListing.q4_option3,jsonHolderListing.q4_option4,jsonHolderListing.q5_audio,jsonHolderListing.q5_option1,jsonHolderListing.q5_option2,jsonHolderListing.q5_option3,jsonHolderListing.q5_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }

}
