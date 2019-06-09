package com.celpipstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ListeningTestQuestionActivity extends AppCompatActivity {

    ListView listView;
    String url;

    ImageView imageViewQuestion;
    JsonDataHandlerListeningPart2 jsonHolderListing;
    MediaPlayer mediaPlayer;
    double startTime = 0;
    double finalTime = 0;

    Handler myHandler = new Handler();;
    int forwardTime = 5000;
    int backwardTime = 5000;
    SeekBar seekbar;
    ImageButton imageButtonPlay;
    Button buttonNext;
    LinearLayout linearLayoutFrame1;
    TextView textViewStart,textViewStop;
    public static int oneTimeOnly = 0;
    Uri myUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_test_question);

        listView = (ListView)findViewById(R.id.listView);
        imageButtonPlay = (ImageButton)findViewById(R.id.buttonPlay);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        linearLayoutFrame1 = (LinearLayout)findViewById(R.id.frame1);
        seekbar = (SeekBar)findViewById(R.id.seekbar);
        seekbar.setClickable(false);
        textViewStart = (TextView)findViewById(R.id.textViewStartTime);
        textViewStop = (TextView)findViewById(R.id.textViewStopTime);


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
                final ProgressDialog loading = ProgressDialog.show(ListeningTestQuestionActivity.this,"Loading","Please wait...",false,false);
                String audio_player = "https://online.celpip.biz/uploads/part2_listening/"+jsonHolderListing.converstaion_1_audio;
                Uri myUri = Uri.parse(audio_player);
                try
                {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(ListeningTestQuestionActivity.this, myUri);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    loading.dismiss();
                    finalTime = mediaPlayer.getDuration();
                    startTime = mediaPlayer.getCurrentPosition();
                    if (oneTimeOnly == 0) {
                        seekbar.setMax((int) finalTime);
                        oneTimeOnly = 1;
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
                mediaPlayer.stop();
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

        ListeningTestQuesionAdapter ca = new ListeningTestQuesionAdapter(this,jsonHolderListing.id,jsonHolderListing.test_code,jsonHolderListing.converstaion_1_audio,jsonHolderListing.q1_audio,jsonHolderListing.q1_option1,jsonHolderListing.q1_option2,jsonHolderListing.q1_option3,jsonHolderListing.q1_option4);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }

}
