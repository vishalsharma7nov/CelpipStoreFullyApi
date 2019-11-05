package com.celpipstore.Adapter.ListeningTestAdapter.Question;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.celpipstore.GetterAndSetterClasses.ListeningTest.Question.ListeningTestPart1;
import com.celpipstore.CelpipTests.ListeningTest.LISTENING_part2;
import com.celpipstore.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ListeningTestPart1QuestionAdapter extends BaseAdapter{
    private Context c;
    private List<ListeningTestPart1> listeningTestPart1;
    private String url = "https://demo.celpip.biz/uploads/part1_listening/";
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
    private String userAnswerQuestion1;
    private String userAnswerQuestion2;
    private String userAnswerQuestion3;
    private String userAnswerQuestion4;
    private String userAnswerQuestion5;
    private String userAnswerQuestion6;
    private String userAnswerQuestion7;
    private String userAnswerQuestion8;
    private ProgressDialog loadingAudio;
    //json data


    public ListeningTestPart1QuestionAdapter(Context c, List<ListeningTestPart1> listeningTestPart1) {
        this.c = c;
        this.listeningTestPart1 = listeningTestPart1;
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
        //========================================================================================//
        /************************************Correct Answer****************************************/
        //========================================================================================//
        final ScrollView scrollView = convertView.findViewById(R.id.userAnswer);
        final TextView c1 = convertView.findViewById(R.id.question1_correct);
        TextView c2 = convertView.findViewById(R.id.question2_correct);
        TextView c3 = convertView.findViewById(R.id.question3_correct);
        TextView c4 = convertView.findViewById(R.id.question4_correct);
        TextView c5 = convertView.findViewById(R.id.question5_correct);
        TextView c6 = convertView.findViewById(R.id.question6_correct);
        TextView c7 = convertView.findViewById(R.id.question7_correct);
        TextView c8 = convertView.findViewById(R.id.question8_correct);
        final TextView u1 = convertView.findViewById(R.id.question1_user);
        final TextView u2 = convertView.findViewById(R.id.question2_user);
        final TextView u3 = convertView.findViewById(R.id.question3_user);
        final TextView u4 = convertView.findViewById(R.id.question4_user);
        final TextView u5 = convertView.findViewById(R.id.question5_user);
        final TextView u6 = convertView.findViewById(R.id.question6_user);
        final TextView u7 = convertView.findViewById(R.id.question7_user);
        final TextView u8 = convertView.findViewById(R.id.question8_user);
        c1.setText("1.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q1_answer());
        c2.setText("2.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q2_answer());
        c3.setText("3.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q3_answer());
        c4.setText("4.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q4_answer());
        c5.setText("5.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q5_answer());
        c6.setText("6.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q6_answer());
        c7.setText("7.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q7_answer());
        c8.setText("8.Correct Answer = "+" option"+listeningTestPart1.get(position).getL1_q8_answer());
        final Button buttonBackToListeningTest = convertView.findViewById(R.id.buttonBackTOListeningTest);
        //========================================================================================//
        /****************************************Question Part*************************************/
        //========================================================================================//
        final RadioGroup radioGroup1 = convertView.findViewById(R.id.frame1);
        final RadioGroup radioGroup2 = convertView.findViewById(R.id.frame2);
        final RadioButton t1= convertView.findViewById(R.id.radioButtonOption1);
        final RadioButton t2= convertView.findViewById(R.id.radioButtonOption2);
        final RadioButton t3= convertView.findViewById(R.id.radioButtonOption3);
        final RadioButton t4= convertView.findViewById(R.id.radioButtonOption4);
        final RadioButton t5= convertView.findViewById(R.id.radio1);
        final RadioButton t6= convertView.findViewById(R.id.radio2);
        final RadioButton t7= convertView.findViewById(R.id.radio3);
        final RadioButton t8= convertView.findViewById(R.id.radio4);
        final Button b1NextQuestion = convertView.findViewById(R.id.buttonNextQuestion1);
        final Button b2NextQuestion = convertView.findViewById(R.id.buttonNextQuestion2);
        final Button b3NextQuestion = convertView.findViewById(R.id.buttonNextQuestion3);
        final Button b4NextQuestion = convertView.findViewById(R.id.buttonNextQuestion4);
        final Button b5NextQuestion = convertView.findViewById(R.id.buttonNextQuestion5);
        final Button b6NextQuestion = convertView.findViewById(R.id.buttonNextQuestion6);
        final Button b7NextQuestion = convertView.findViewById(R.id.buttonNextQuestion7);
        final Button b8NextQuestion = convertView.findViewById(R.id.buttonNextQuestion8);
        final Button b9NextQuestion = convertView.findViewById(R.id.buttonFinish);
        final Button b10NextQuestion = convertView.findViewById(R.id.buttonAudio2);
        final Button b11NextQuestion = convertView.findViewById(R.id.buttonAudio3);
        final ImageView imageView1  = convertView.findViewById(R.id.imageViewRadio1);
        final ImageView imageView2  = convertView.findViewById(R.id.imageViewRadio2);
        final ImageView imageView3  = convertView.findViewById(R.id.imageViewRadio3);
        final ImageView imageView4  = convertView.findViewById(R.id.imageViewRadio4);
        final String[] questions_audio  = {listeningTestPart1.get(position).getL1_q1_audio(),listeningTestPart1.get(position).getL1_q2_audio(),listeningTestPart1.get(position).getL1_q3_audio(),listeningTestPart1.get(position).getL1_q4_audio(),listeningTestPart1.get(position).getL1_q5_audio(),listeningTestPart1.get(position).getL1_q6_audio(),listeningTestPart1.get(position).getL1_q7_audio(),listeningTestPart1.get(position).getL1_q8_audio(), null};
        final String[] question_option1 = {listeningTestPart1.get(position).getL1_q1_option1(),listeningTestPart1.get(position).getL1_q2_option1(),listeningTestPart1.get(position).getL1_q3_option1(),listeningTestPart1.get(position).getL1_q4_option1(),listeningTestPart1.get(position).getL1_q5_option1(),listeningTestPart1.get(position).getL1_q6_option1(),listeningTestPart1.get(position).getL1_q7_option1(), listeningTestPart1.get(position).getL1_q8_option1(), null};
        final String[] question_option2 = {listeningTestPart1.get(position).getL1_q1_option2(),listeningTestPart1.get(position).getL1_q2_option2(),listeningTestPart1.get(position).getL1_q3_option2(),listeningTestPart1.get(position).getL1_q4_option2(),listeningTestPart1.get(position).getL1_q5_option2(),listeningTestPart1.get(position).getL1_q6_option2(),listeningTestPart1.get(position).getL1_q7_option2(), listeningTestPart1.get(position).getL1_q8_option2(), null};
        final String[] question_option3 = {listeningTestPart1.get(position).getL1_q1_option3(),listeningTestPart1.get(position).getL1_q2_option3(),listeningTestPart1.get(position).getL1_q3_option3(),listeningTestPart1.get(position).getL1_q4_option3(),listeningTestPart1.get(position).getL1_q5_option3(),listeningTestPart1.get(position).getL1_q6_option3(),listeningTestPart1.get(position).getL1_q7_option3(), listeningTestPart1.get(position).getL1_q8_option3(), null};
        final String[] question_option4 = {listeningTestPart1.get(position).getL1_q1_option4(),listeningTestPart1.get(position).getL1_q2_option4(),listeningTestPart1.get(position).getL1_q3_option4(),listeningTestPart1.get(position).getL1_q4_option4(),listeningTestPart1.get(position).getL1_q5_option4(),listeningTestPart1.get(position).getL1_q6_option4(),listeningTestPart1.get(position).getL1_q7_option4(), listeningTestPart1.get(position).getL1_q8_option4(), null};
        final ImageButton imageButtonPlay = convertView.findViewById(R.id.buttonPlay);
        final ImageButton imageButtonPause = convertView.findViewById(R.id.buttonPause);
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        textViewStart = finalConvertView1.findViewById(R.id.textViewStartTime);
        textViewStop  = finalConvertView1.findViewById(R.id.textViewStopTime);
        seekbar = finalConvertView.findViewById(R.id.seekbar);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PlayMusic().execute();
            }
        });
        notifyDataSetChanged();
        SharedPreferences bb = c.getSharedPreferences("my_prefs", 0);
        final String tokenCode = bb.getString("tokenCode", "tokenCode");
        final String member_id = bb.getString("member_id", "member_id");
        final String image1 = "https://demo.celpip.biz/uploads/part1_listening/"+question_option1[0];
        final String image2 = "https://demo.celpip.biz/uploads/part1_listening/"+question_option2[0];
        final String image3 = "https://demo.celpip.biz/uploads/part1_listening/"+question_option3[0];
        final String image4 = "https://demo.celpip.biz/uploads/part1_listening/"+question_option4[0];
        final ProgressDialog loading = ProgressDialog.show(c,"Loading","Please wait...",false,false);
        seekbar.setSelected(false);
        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        audio_player = url+questions_audio[0];
        new PlayMusic().execute();
        Glide
                .with(c)
                .load(image1)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView1);

        Glide
                .with(c)
                .load(image2)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView2);
        Glide
                .with(c)
                .load(image3)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView3);
        Glide
                .with(c)
                .load(image4)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        loading.dismiss();
                        return false;
                    }
                })
                .into(imageView4);
        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
        t4.setChecked(false);
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    Toast.makeText(c, "STOPPED!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b1NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup2.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                b1NextQuestion.setVisibility(View.GONE);
                b2NextQuestion.setVisibility(View.VISIBLE);
                t5.setText(image1);
                t6.setText(image2);
                t7.setText(image3);
                t8.setText(image4);
                if (t5.isChecked())
                {
                    userAnswerQuestion1 = "option1";
                }
                if (t6.isChecked())
                {
                    userAnswerQuestion1 = "option2";
                }
                if (t7.isChecked())
                {
                    userAnswerQuestion1 = "option3";
                }
                if (t8.isChecked())
                {
                    userAnswerQuestion1 = "option4";
                }
                audio_player = url + questions_audio[1];
                new PlayMusic().execute();
                t1.setText(question_option1[1]);
                t2.setText(question_option2[1]);
                t3.setText(question_option3[1]);
                t4.setText(question_option4[1]);
                mediaPlayer.stop();

            }
        });
        b2NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2NextQuestion.setVisibility(View.GONE);
                b10NextQuestion.setVisibility(View.VISIBLE);
                radioGroup2.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[2];
                new PlayMusic().execute();
                t1.setText(question_option1[2]);
                t2.setText(question_option2[2]);
                t3.setText(question_option3[2]);
                t4.setText(question_option4[2]);
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
                mediaPlayer.stop();

            }
        });
        b10NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup1.setVisibility(View.GONE);
                audio_player = url+listeningTestPart1.get(position).getL1_converstaion_2_audio();
                b10NextQuestion.setVisibility(View.GONE);
                new PlayMusic().execute();
                imageButtonPause.setVisibility(View.VISIBLE);
                b3NextQuestion.setVisibility(View.VISIBLE);
                mediaPlayer.stop();
            }
        });
        b3NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b3NextQuestion.setVisibility(View.GONE);
                b4NextQuestion.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[3];
                new PlayMusic().execute();
                t1.setText(question_option1[3]);
                t2.setText(question_option2[3]);
                t3.setText(question_option3[3]);
                t4.setText(question_option4[3]);
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
                mediaPlayer.stop();
            }
        });
        b4NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4NextQuestion.setVisibility(View.GONE);
                b5NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[4];
                new PlayMusic().execute();
                t1.setText(question_option1[4]);
                t2.setText(question_option2[4]);
                t3.setText(question_option3[4]);
                t4.setText(question_option4[4]);
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
                mediaPlayer.stop();
            }
        });
        b5NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5NextQuestion.setVisibility(View.GONE);
                b11NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[5];
                new PlayMusic().execute();
                t1.setText(question_option1[5]);
                t2.setText(question_option2[5]);
                t3.setText(question_option3[5]);
                t4.setText(question_option4[5]);
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
                mediaPlayer.stop();

            }
        });

        b11NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b11NextQuestion.setVisibility(View.GONE);
                imageButtonPause.setVisibility(View.VISIBLE);
                b6NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.GONE);

                audio_player = url+listeningTestPart1.get(position).getL1_converstaion_3_audio();
                new PlayMusic().execute();
                mediaPlayer.stop();
            }
        });

        b6NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6NextQuestion.setVisibility(View.GONE);
                b7NextQuestion.setVisibility(View.VISIBLE);
                imageButtonPause.setVisibility(View.GONE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[6];
                new PlayMusic().execute();
                t1.setText(question_option1[6]);
                t2.setText(question_option2[6]);
                t3.setText(question_option3[6]);
                t4.setText(question_option4[6]);
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

                mediaPlayer.stop();
            }
        });
        b7NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b7NextQuestion.setVisibility(View.GONE);
                b8NextQuestion.setVisibility(View.VISIBLE);
                radioGroup1.setVisibility(View.VISIBLE);
                audio_player = url + questions_audio[7];
                new PlayMusic().execute();
                t1.setText(question_option1[7]);
                t2.setText(question_option2[7]);
                t3.setText(question_option3[7]);
                t4.setText(question_option4[7]);
                if (t1.isChecked())
                {
                    userAnswerQuestion7 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion7 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion7 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion7 = "option4";
                }
                mediaPlayer.stop();
            }
        });
        b8NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup1.setVisibility(View.GONE);
                b8NextQuestion.setVisibility(View.GONE);
                b9NextQuestion.setVisibility(View.VISIBLE);
                textViewStart.setVisibility(View.GONE);
                textViewStop.setVisibility(View.GONE);
                imageButtonPlay.setVisibility(View.GONE);
                seekbar.setVisibility(View.GONE);
                if (t1.isChecked())
                {
                    userAnswerQuestion8 = "option1";
                }
                if (t2.isChecked())
                {
                    userAnswerQuestion8 = "option2";
                }
                if (t3.isChecked())
                {
                    userAnswerQuestion8 = "option3";
                }
                if (t4.isChecked())
                {
                    userAnswerQuestion8 = "option4";
                }
                mediaPlayer.stop();
            }
        });
        b9NextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               radioGroup2.setVisibility(View.GONE);
               radioGroup1.setVisibility(View.GONE);
               b8NextQuestion.setVisibility(View.GONE);
               b9NextQuestion.setVisibility(View.GONE);
               scrollView.setVisibility(View.VISIBLE);
                u1.setText("1.Your Answer = "+userAnswerQuestion1);
                u2.setText("2.Your Answer = "+userAnswerQuestion2);
                u3.setText("3.Your Answer = "+userAnswerQuestion3);
                u4.setText("4.Your Answer = "+userAnswerQuestion4);
                u5.setText("5.Your Answer = "+userAnswerQuestion5);
                u6.setText("6.Your Answer = "+userAnswerQuestion6);
                u7.setText("7.Your Answer = "+userAnswerQuestion7);
                u8.setText("8.Your Answer = "+userAnswerQuestion8);
                final String urlForSubmittingOptions = "http://demo.celpip.biz/api/lsPart1Submit?token="+tokenCode+"&q1_response="+userAnswerQuestion1+"&q2_response="+userAnswerQuestion2+"&q3_response="+userAnswerQuestion3+"&q4_response="+userAnswerQuestion4+"&q5_response="+userAnswerQuestion5+"&q6_response="+userAnswerQuestion6+"&q7_response="+userAnswerQuestion7+"&q8_response="+userAnswerQuestion8+"&memberid="+member_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(c, "Answer Submitted", Toast.LENGTH_SHORT).show();
                                Log.e("===part1 answer",urlForSubmittingOptions);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("===part1 answer",urlForSubmittingOptions);
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
                        params.put("q7_response", userAnswerQuestion7);
                        params.put("q8_response", userAnswerQuestion8);
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
            loadingAudio.setMessage("LOADING AUDIO!!");
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