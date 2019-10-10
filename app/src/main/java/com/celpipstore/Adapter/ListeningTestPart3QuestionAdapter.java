package com.celpipstore.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
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
import com.celpipstore.Tests.ListeningTest.LISTENING_part2;
import com.celpipstore.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ListeningTestPart3QuestionAdapter extends BaseAdapter{

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
    ProgressDialog loadingAudio;

    String userAnswerQuestion1;
    String userAnswerQuestion2;
    String userAnswerQuestion3;
    String userAnswerQuestion4;
    String userAnswerQuestion5;

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

    public ListeningTestPart3QuestionAdapter(Context c, String id, String test_code, String converstaion_1_audio, String q1_audio, String q1_option1, String q1_option2, String q1_option3, String q1_option4, String q2_audio, String q2_option1, String q2_option2, String q2_option3, String q2_option4, String q3_audio, String q3_option1, String q3_option2, String q3_option3, String q3_option4, String q4_audio, String q4_option1, String q4_option2, String q4_option3, String q4_option4, String q5_audio, String q5_option1, String q5_option2, String q5_option3, String q5_option4)
    {
        this.c=c;
        ListeningTestPart3QuestionAdapter.id = id;
        ListeningTestPart3QuestionAdapter.test_code = test_code;
        ListeningTestPart3QuestionAdapter.converstaion_1_audio = converstaion_1_audio;
        ListeningTestPart3QuestionAdapter.q1_audio = q1_audio;
        ListeningTestPart3QuestionAdapter.q1_option1 = q1_option1;
        ListeningTestPart3QuestionAdapter.q1_option2 = q1_option2;
        ListeningTestPart3QuestionAdapter.q1_option3 = q1_option3;
        ListeningTestPart3QuestionAdapter.q1_option4 = q1_option4;
        ListeningTestPart3QuestionAdapter.q2_audio = q2_audio;
        ListeningTestPart3QuestionAdapter.q2_option1 = q2_option1;
        ListeningTestPart3QuestionAdapter.q2_option2 = q2_option2;
        ListeningTestPart3QuestionAdapter.q2_option3 = q2_option3;
        ListeningTestPart3QuestionAdapter.q2_option4 = q2_option4;
        ListeningTestPart3QuestionAdapter.q3_audio = q3_audio;
        ListeningTestPart3QuestionAdapter.q3_option1 = q3_option1;
        ListeningTestPart3QuestionAdapter.q3_option2 = q3_option2;
        ListeningTestPart3QuestionAdapter.q3_option3 = q3_option3;
        ListeningTestPart3QuestionAdapter.q3_option4 = q3_option4;
        ListeningTestPart3QuestionAdapter.q4_audio = q4_audio;
        ListeningTestPart3QuestionAdapter.q4_option1 = q4_option1;
        ListeningTestPart3QuestionAdapter.q4_option2 = q4_option2;
        ListeningTestPart3QuestionAdapter.q4_option3 = q4_option3;
        ListeningTestPart3QuestionAdapter.q4_option4 = q4_option4;
        ListeningTestPart3QuestionAdapter.q5_audio = q5_audio;
        ListeningTestPart3QuestionAdapter.q5_option1 = q5_option1;
        ListeningTestPart3QuestionAdapter.q5_option2 = q5_option2;
        ListeningTestPart3QuestionAdapter.q5_option3 = q5_option3;
        ListeningTestPart3QuestionAdapter.q5_option4 = q5_option4;

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
        final Button b4NextQuestion = convertView.findViewById(R.id.buttonNextQuestion4);
        final Button b5NextQuestion = convertView.findViewById(R.id.buttonNextQuestion5);
        final Button b6NextQuestion = convertView.findViewById(R.id.buttonFinish);

        final String[] questions_audio = {q1_audio, q2_audio, q3_audio, q4_audio, q5_audio, null};
        final String[] question_option1 = {q1_option1, q2_option1, q3_option3, q4_option1, q5_option1, null};
        final String[] question_option2 = {q1_option2, q2_option2, q3_option2, q4_option2, q5_option2, null};
        final String[] question_option3 = {q1_option3, q2_option3, q3_option3, q4_option3, q5_option3, null};
        final String[] question_option4 = {q1_option4, q2_option4, q3_option4, q4_option4, q5_option4, null};

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
                audio_player = url+questions_audio[0];
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
                t1.setText(question_option1[1]);
                t2.setText(question_option2[1]);
                t3.setText(question_option3[1]);
                t4.setText(question_option4[1]);

            }
        });
        b3NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b3NextQuestion.setVisibility(View.GONE);
                b4NextQuestion.setVisibility(View.VISIBLE);
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
                audio_player = url + questions_audio[2];
                new PlayMusic().execute();
                mediaPlayer.stop();
                t1.setText(question_option1[2]);
                t2.setText(question_option2[2]);
                t3.setText(question_option3[2]);
                t4.setText(question_option4[2]);

            }
        });
        b4NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b4NextQuestion.setVisibility(View.GONE);
                b5NextQuestion.setVisibility(View.VISIBLE);
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

                audio_player = url + questions_audio[3];
                new PlayMusic().execute();
                mediaPlayer.stop();
                t1.setText(question_option1[3]);
                t2.setText(question_option2[3]);
                t3.setText(question_option3[3]);
                t4.setText(question_option4[3]);

            }
        });
        b5NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b5NextQuestion.setVisibility(View.GONE);
                b6NextQuestion.setVisibility(View.VISIBLE);
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

                audio_player = url + questions_audio[4];
                new PlayMusic().execute();
                mediaPlayer.stop();
                t1.setText(question_option1[4]);
                t2.setText(question_option2[4]);
                t3.setText(question_option3[4]);
                t4.setText(question_option4[4]);

            }
        });
        b6NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b5NextQuestion.setVisibility(View.GONE);
                mediaPlayer.stop();
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
                String urlForSubmittingOptions = "http://online.celpip.biz/api/lsPart3Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&memberid="+member_id;
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
                        Log.e("===answer1",userAnswerQuestion1);
                        Log.e("===answer2",userAnswerQuestion2);
                        Log.e("===answer3",userAnswerQuestion3);
                        Log.e("===answer4",userAnswerQuestion4);
                        Log.e("===answer5",userAnswerQuestion5);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c.getApplicationContext());
                requestQueue.add(stringRequest);

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