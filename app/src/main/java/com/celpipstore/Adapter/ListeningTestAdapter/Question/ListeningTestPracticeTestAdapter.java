package com.celpipstore.Adapter.ListeningTestAdapter.Question;

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
import com.celpipstore.GetterAndSetterClasses.ListeningTest.Question.ListeningPracticeTest;
import com.celpipstore.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ListeningTestPracticeTestAdapter extends BaseAdapter{

    private Context c;
    private List<ListeningPracticeTest> listeningPracticeTest;
    private String url = "https://demo.celpip.biz/uploads/part2_listening/";
    //media player
    private double startTime = 0;
    private double finalTime = 0;
    private MediaPlayer mediaPlayer;
    private TextView textViewStart;
    private TextView textViewStop;
    private SeekBar seekbar;
    private Handler myHandler = new Handler();
    private static int oneTimeOnly = 0;
    private String audio_player;
    private ProgressDialog loadingAudio;
    private String userAnswerQuestion1;

    public ListeningTestPracticeTestAdapter(Context c, List<ListeningPracticeTest> listeningPracticeTest) {
        this.c = c;
        this.listeningPracticeTest = listeningPracticeTest;
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
        final String[] questions_audio   = {listeningPracticeTest.get(position).getQuestion(), null};
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
        t1.setText(listeningPracticeTest.get(position).getOption1());
        t2.setText(listeningPracticeTest.get(position).getOption2());
        t3.setText(listeningPracticeTest.get(position).getOption3());
        t4.setText(listeningPracticeTest.get(position).getOption4());
        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
        t4.setChecked(false);
        b2NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2NextQuestion.setVisibility(View.GONE);
                b3NextQuestion.setVisibility(View.VISIBLE);
                t1.setText(listeningPracticeTest.get(position).getOption1());
                t2.setText(listeningPracticeTest.get(position).getOption2());
                t3.setText(listeningPracticeTest.get(position).getOption3());
                t4.setText(listeningPracticeTest.get(position).getOption4());
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