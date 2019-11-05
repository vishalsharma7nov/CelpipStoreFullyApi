package com.celpipstore.Adapter.ListeningTestAdapter.Question;

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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
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
import com.celpipstore.CelpipTests.ListeningTest.LISTENING_part2;
import com.celpipstore.GetterAndSetterClasses.ListeningTest.Question.ListeningTestPart3;
import com.celpipstore.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.view.View.GONE;


public class ListeningTestPart3QuestionAdapter extends BaseAdapter{

    private Context c;
    private List<ListeningTestPart3> listeningTestPart3;
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
    private String userAnswerQuestion2;
    private String userAnswerQuestion3;
    private String userAnswerQuestion4;
    private String userAnswerQuestion5;
    private String userAnswerQuestion6;

    public ListeningTestPart3QuestionAdapter(Context c, List<ListeningTestPart3> listeningTestPart3) {
        this.c = c;
        this.listeningTestPart3 = listeningTestPart3;
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
        convertView=in.inflate(R.layout.test_adapter_listeningtestpart3,null);
        final LinearLayout timer = convertView.findViewById(R.id.timer);
        final LinearLayout radiobutton = convertView.findViewById(R.id.radioButton);
        //========================================================================================//
        /************************************Correct Answer****************************************/
        //========================================================================================//
        final ScrollView scrollView = convertView.findViewById(R.id.userAnswer);
        final TextView c1 = convertView.findViewById(R.id.question1_correct);
        final TextView c2 = convertView.findViewById(R.id.question2_correct);
        final TextView c3 = convertView.findViewById(R.id.question3_correct);
        final TextView c4 = convertView.findViewById(R.id.question4_correct);
        final TextView c5 = convertView.findViewById(R.id.question5_correct);
        final TextView c6 = convertView.findViewById(R.id.question6_correct);
        final TextView c7 = convertView.findViewById(R.id.question6_correct);
        final TextView c8 = convertView.findViewById(R.id.question6_correct);
        c7.setVisibility(GONE);
        c8.setVisibility(GONE);
        final TextView u1 = convertView.findViewById(R.id.question1_user);
        final TextView u2 = convertView.findViewById(R.id.question2_user);
        final TextView u3 = convertView.findViewById(R.id.question3_user);
        final TextView u4 = convertView.findViewById(R.id.question4_user);
        final TextView u5 = convertView.findViewById(R.id.question5_user);
        final TextView u6 = convertView.findViewById(R.id.question6_user);
        final TextView u7 = convertView.findViewById(R.id.question6_user);
        final TextView u8 = convertView.findViewById(R.id.question6_user);
        u7.setVisibility(GONE);
        u8.setVisibility(GONE);
        c1.setText("Correct Answer = "+" option"+listeningTestPart3.get(position).getQ1_answer());
        c2.setText("Correct Answer = "+" option"+listeningTestPart3.get(position).getQ2_answer());
        c3.setText("Correct Answer = "+" option"+listeningTestPart3.get(position).getQ3_answer());
        c4.setText("Correct Answer = "+" option"+listeningTestPart3.get(position).getQ4_answer());
        c5.setText("Correct Answer = "+" option"+listeningTestPart3.get(position).getQ5_answer());
        c6.setText("Correct Answer = "+" option"+listeningTestPart3.get(position).getQ6_answer());
        final Button buttonBackToListeningTest = convertView.findViewById(R.id.buttonBackTOListeningTest);
        //========================================================================================//
        /****************************************Question Part*************************************/
        //========================================================================================//
        final RadioButton t1= convertView.findViewById(R.id.radioButtonOption1);
        final RadioButton t2= convertView.findViewById(R.id.radioButtonOption2);
        final RadioButton t3= convertView.findViewById(R.id.radioButtonOption3);
        final RadioButton t4= convertView.findViewById(R.id.radioButtonOption4);
        final Button b2NextQuestion = convertView.findViewById(R.id.buttonNextQuestion2);
        final Button b3NextQuestion = convertView.findViewById(R.id.buttonNextQuestion3);
        final Button b4NextQuestion = convertView.findViewById(R.id.buttonNextQuestion4);
        final Button b5NextQuestion = convertView.findViewById(R.id.buttonNextQuestion5);
        final Button b6NextQuestion = convertView.findViewById(R.id.buttonNextQuestion6);
        final Button Submit         = convertView.findViewById(R.id.buttonFinish);
        final String[] questions_audio =  {listeningTestPart3.get(position).getQ1_audio(),listeningTestPart3.get(position).getQ2_audio(),listeningTestPart3.get(position).getQ3_audio(),listeningTestPart3.get(position).getQ4_audio(),listeningTestPart3.get(position).getQ5_audio(),listeningTestPart3.get(position).getQ6_audio(), null};
        final String[] question_option1 = {listeningTestPart3.get(position).getQ1_option1(),listeningTestPart3.get(position).getQ2_option1(),listeningTestPart3.get(position).getQ3_option1(),listeningTestPart3.get(position).getQ4_option1(),listeningTestPart3.get(position).getQ5_option1(),listeningTestPart3.get(position).getQ6_option1(), null};
        final String[] question_option2 = {listeningTestPart3.get(position).getQ1_option2(),listeningTestPart3.get(position).getQ2_option2(),listeningTestPart3.get(position).getQ3_option2(),listeningTestPart3.get(position).getQ4_option2(),listeningTestPart3.get(position).getQ5_option2(),listeningTestPart3.get(position).getQ6_option2(), null};
        final String[] question_option3 = {listeningTestPart3.get(position).getQ1_option3(),listeningTestPart3.get(position).getQ2_option3(),listeningTestPart3.get(position).getQ3_option3(),listeningTestPart3.get(position).getQ4_option3(),listeningTestPart3.get(position).getQ5_option3(),listeningTestPart3.get(position).getQ6_option3(), null};
        final String[] question_option4 = {listeningTestPart3.get(position).getQ1_option4(),listeningTestPart3.get(position).getQ2_option4(),listeningTestPart3.get(position).getQ3_option4(),listeningTestPart3.get(position).getQ4_option4(),listeningTestPart3.get(position).getQ5_option4(),listeningTestPart3.get(position).getQ6_option4(), null};
//        Log.e("===AUDIOFILES", String.valueOf(questions_audio.length));
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
                    userAnswerQuestion1 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion1 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion1 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion1 = "option4";
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
                    userAnswerQuestion2 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion2 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion2 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion2 = "option4";
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
                    userAnswerQuestion3 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion3 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion3 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion3 = "option4";
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
                    userAnswerQuestion4 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion4 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion4 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion4 = "option4";
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
                b6NextQuestion.setVisibility(View.VISIBLE);
                if (t1.isChecked())
                {
                    userAnswerQuestion5 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion5 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion5 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion5 = "option4";
                }
                audio_player = url + questions_audio[5];
                new PlayMusic().execute();
                mediaPlayer.stop();
                t1.setText(question_option1[5]);
                t2.setText(question_option2[5]);
                t3.setText(question_option3[5]);
                t4.setText(question_option4[5]);
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u1.setText("Your Answer = "+userAnswerQuestion1);
                u2.setText("Your Answer = "+userAnswerQuestion2);
                u3.setText("Your Answer = "+userAnswerQuestion3);
                u4.setText("Your Answer = "+userAnswerQuestion4);
                u5.setText("Your Answer = "+userAnswerQuestion5);
                u6.setText("Your Answer = "+userAnswerQuestion6);
                b6NextQuestion.setVisibility(GONE);
                scrollView.setVisibility(View.VISIBLE);
                radiobutton.setVisibility(GONE);
                timer.setVisibility(GONE);
                mediaPlayer.stop();
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/lsPart3Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, "Answer Submitted", Toast.LENGTH_SHORT).show();
                                Log.e("===Answer Submitting",urlForSubmittingOptions);
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
                        params.put("q6_response", userAnswerQuestion6);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c.getApplicationContext());
                requestQueue.add(stringRequest);
                buttonBackToListeningTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(c , LISTENING_part2.class);
                        c.startActivity(intent);
                    }
                });
                if (t1.isChecked())
                {
                    userAnswerQuestion6 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion6 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion6 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion6 = "option4";
                }
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