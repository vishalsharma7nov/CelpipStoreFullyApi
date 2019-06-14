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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class ListeningTestQuestionActivity extends AppCompatActivity {

    ListView listView;
    String url,part1,part2;
    String image;
    ImageView imageView;

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
    Button buttonNext,button;
    LinearLayout linearLayoutFrame1,linearLayoutFrame2;
    TextView textViewStart,textViewStop,textViewPercent,textViewInstruction;
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
        imageButtonPause = (ImageButton)findViewById(R.id.buttonPause);

        linearLayoutFrame1 = (LinearLayout)findViewById(R.id.frame1);
        linearLayoutFrame2 = (LinearLayout)findViewById(R.id.frame2);

        seekbar = (SeekBar)findViewById(R.id.seekbar);
        seekbar.setClickable(false);
        textViewStart = (TextView)findViewById(R.id.textViewStartTime);
        textViewStop = (TextView)findViewById(R.id.textViewStopTime);
        textViewInstruction = (TextView)findViewById(R.id.textViewInstruction);
        imageButtonPause.setVisibility(View.GONE);

        imageView = (ImageView)findViewById(R.id.imageView);

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String member_id = bb.getString("member_id", "member_id");

        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        url = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        part1 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=20&testcode="+test_code;
        part2 = "http://online.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=19&testcode="+test_code;
        sendRequest();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutFrame2.setVisibility(View.GONE);
                linearLayoutFrame1.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutFrame1.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                else
                {
                    mediaPlayer.start();
                }
                Toast.makeText(ListeningTestQuestionActivity.this, "Pause", Toast.LENGTH_SHORT).show();
            }
        });
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (url.equals(part1))
                {
                    new PalyMusic().execute();

                }
                if (url.equals(part2))
                {
                    new PalyMusic().execute();
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
                                                                textViewInstruction.setText("Instruction :\n"+jsonHolderListingpart1.l1_practice_01_text);

                                                            }
                                                            else if(url.equals(part2))
                                                            {
                                                                linearLayoutFrame2.setVisibility(View.GONE);
                                                                linearLayoutFrame1.setVisibility(View.VISIBLE);
                                                                showJSONPart2(response);
                                                                loading.dismiss();
                                                            }
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
                mp.start();
                imageButtonPause.setVisibility(View.VISIBLE);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
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
                mp.start();
                imageButtonPause.setVisibility(View.VISIBLE);
                loadingAudio.dismiss();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(false);
                mp.stop();
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
    class  PalyMusic extends AsyncTask<Void, Void, Void> {

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

            return (null);
        }

        protected void onPostExecute(Void unused) {

            //here you can call your mp.prepare();
            loadingAudio.dismiss();
        }

    }

}
