package com.celpipstore.Adapter.ListeningTestAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
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
import com.celpipstore.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ListeningTestPracticeTestAdapter extends BaseAdapter{

    Context c;
    String url = "https://demo.celpip.biz/uploads/part2_listening/";
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
    ProgressDialog loadingAudio;
    String userAnswerQuestion1;
    //json data
    public static String id;
    public static String test_code;
    public static String q1_audio;
    public static String q1_option1;
    public static String q1_option2;
    public static String q1_option3;
    public static String q1_option4;

    public ListeningTestPracticeTestAdapter(Context c,String id,String test_code,String q1_audio,String q1_option1,String q1_option2,String q1_option3,String q1_option4) {
        this.c = c;
        ListeningTestPracticeTestAdapter.id =id;
        ListeningTestPracticeTestAdapter.test_code =test_code;
        ListeningTestPracticeTestAdapter.q1_audio =q1_audio;
        ListeningTestPracticeTestAdapter.q1_option1 =q1_option1;
        ListeningTestPracticeTestAdapter.q1_option2 =q1_option2;
        ListeningTestPracticeTestAdapter.q1_option3 =q1_option3;
        ListeningTestPracticeTestAdapter.q1_option4 =q1_option4;
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
        convertView=in.inflate(R.layout.test_adapter_listeningtestpart2,null);
        final RadioButton t1= convertView.findViewById(R.id.radioButtonOption1);
        final RadioButton t2= convertView.findViewById(R.id.radioButtonOption2);
        final RadioButton t3= convertView.findViewById(R.id.radioButtonOption3);
        final RadioButton t4= convertView.findViewById(R.id.radioButtonOption4);
        final Button b2NextQuestion = convertView.findViewById(R.id.buttonNextQuestion2);
        final Button b3NextQuestion = convertView.findViewById(R.id.buttonNextQuestion3);
        final String[] questions_audio   = {q1_audio   , null};
        final String[] question_option1 = {q1_option1, null};
        final String[] question_option2 = {q1_option2, null};
        final String[] question_option3 = {q1_option3, null};
        final String[] question_option4 = {q1_option4, null};
        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");
        seekbar = convertView.findViewById(R.id.seekbar);
        textViewStart = convertView.findViewById(R.id.textViewStartTime);
        textViewStop  = convertView.findViewById(R.id.textViewStopTime);
        ImageButton imageButtonPlay = convertView.findViewById(R.id.buttonPlay);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlayMusic().execute();
            }
        });
        audio_player = url+questions_audio[0];
        new PlayMusic().execute();
        t1.setText(question_option1[0]);
        t2.setText(question_option2[0]);
        t3.setText(question_option3[0]);
        t4.setText(question_option4[0]);
        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
        t4.setChecked(false);
        b2NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2NextQuestion.setVisibility(View.GONE);
                b3NextQuestion.setVisibility(View.VISIBLE);
                t1.setText(question_option1[0]);
                t2.setText(question_option2[0]);
                t3.setText(question_option3[0]);
                t4.setText(question_option4[0]);
                if (t1.isChecked())
                {
                    userAnswerQuestion1 = t1.getText().toString();
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion1 = t2.getText().toString();
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion1 = t3.getText().toString();
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion1 = t4.getText().toString();
                }
                audio_player = url + questions_audio[1];
                new PlayMusic().execute();
                mediaPlayer.stop();
                String urlForSubmittingOptions = "http://online.celpip.biz/api/practiceTaskSubmit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&memberid="+member_id;
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
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c.getApplicationContext());
                requestQueue.add(stringRequest);
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
        mediaPlayer = MediaPlayer.create(c,myUri);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekbar.setSecondaryProgress(percent * mediaPlayer.getDuration() /100 );
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    seekbar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
                }
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
}