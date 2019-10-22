package com.celpipstore.CelpipTests.SpeakingTest;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.celpipstore.Adapter.SpeakingTestAdapter.SpeakingTestPart3Adapter;
import com.celpipstore.Adapter.SpeakingTestAdapter.SpeakingTestPart5Adapter;
import com.celpipstore.Adapter.SpeakingTestAdapter.SpeakingTestPracticeTestAdapter;
import com.celpipstore.GetterAndSetterClasses.SpeakingTest.PracticeTestSpeaking;
import com.celpipstore.GetterAndSetterClasses.SpeakingTest.SpeakingTestPart3;
import com.celpipstore.GetterAndSetterClasses.SpeakingTest.SpeakingTestPart5;
import com.celpipstore.JsonData.SpeakingTest.JsonDataHandlerPracticeTestSpeaking;
import com.celpipstore.JsonData.SpeakingTest.JsonDataHandlerSpeakingTest3;
import com.celpipstore.JsonData.SpeakingTest.JsonDataHandlerSpeakingTest5;
import com.celpipstore.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SpeakingTestQuestionActivity extends AppCompatActivity {
    private Button startbtn, stopbtn, playbtn, stopplay,submitAnswer;
    private ListView speakingTestPracticeQuestion,speakingTest3;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    private String tokenCode,API,member_id,practicetest,part1,part2,part3,part4,part5,part6,part7,part8;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    private List<PracticeTestSpeaking> practiceTestSpeaking;
    private List<SpeakingTestPart3> speakingTestPart3;
    private List<SpeakingTestPart5> speakingTestPart5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_test_question);
        startbtn =  findViewById(R.id.btnRecord);
        stopbtn  =   findViewById(R.id.btnStop);
        playbtn  =   findViewById(R.id.btnPlay);
        stopplay =  findViewById(R.id.btnStopPlay);
        speakingTestPracticeQuestion = findViewById(R.id.speakingTestPracticeQuestion);
        speakingTest3 = findViewById(R.id.speakingTest3);
        submitAnswer = findViewById(R.id.submitAnswer);
        audioRecoder();
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        tokenCode = bb.getString("tokenCode", "tokenCode");
        member_id = bb.getString("member_id", "member_id");
        Intent intent = getIntent();
        String test_id = bb.getString("test_id","test_id");
        String test_code = intent.getStringExtra("t2");
        API = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid="+test_id+"&testcode="+test_code;
        practicetest = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=59&testcode="+test_code;
        part1 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=58&testcode="+test_code;
        part2 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=57&testcode="+test_code;
        part3 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=56&testcode="+test_code;
        part4 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=55&testcode="+test_code;
        part5 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=54&testcode="+test_code;
        part6 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=53&testcode="+test_code;
        part7 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=52&testcode="+test_code;
        part8 = "http://demo.celpip.biz/api/accessnewtest?memberid="+member_id+"&testid=51&testcode="+test_code;
        connectionChecker();
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"LOADING","PLEASE WAIT!!",false,false);
        StringRequest stringRequest = new StringRequest(API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("response"));
                            String tokenCode = obj.getString("tdcode");
                            final String a = "http://demo.celpip.biz/api/testnewprogress?token="+tokenCode;
                            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("tokenCode", tokenCode);
                            edit.commit();
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "SPEAKING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                final ProgressDialog loading = ProgressDialog.show(SpeakingTestQuestionActivity.this,"LOADING","PLEASE WAIT!!",false,false);
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
                                                        Toast.makeText(getApplicationContext(), "SPEAKING TEST NOT AVAILABLE RIGHT NOW!!", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if (abc == 1)
                                                    {
                                                        if (API.equals(part1))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart1(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if(API.equals(part2))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart1(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(part3))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart3(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(part4))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart3(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(part5))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart5(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(part6))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart1(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(part7))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart1(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(part8))
                                                        {
                                                            loading.dismiss();
                                                            showJSONPart3(response);
                                                            Log.e("===READINGPART1",a);
                                                        }
                                                        else if (API.equals(practicetest))
                                                        {
                                                            loading.dismiss();
                                                            submitAnswer.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
//                                                                    submitAnswerPractice();
                                                                }
                                                            });
                                                            Log.e("===READINGPART1",a);
                                                            showJSONPracticeTest(response);
                                                        }
                                                    }
                                                }
                                                catch (JSONException e) {
                                                    e.printStackTrace();
                                                    loading.dismiss();
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
                                RequestQueue requestQueue = Volley.newRequestQueue(SpeakingTestQuestionActivity.this);
                                requestQueue.add(stringRequest);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Json Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void showJSONPracticeTest(String json) {
        JsonDataHandlerPracticeTestSpeaking jsonDataHandlerPracticeTestSpeaking= new JsonDataHandlerPracticeTestSpeaking(json);
        practiceTestSpeaking=jsonDataHandlerPracticeTestSpeaking.parseJSON();
        SpeakingTestPracticeTestAdapter speakingTestPracticeTestAdapter = new SpeakingTestPracticeTestAdapter(this,practiceTestSpeaking);
        speakingTestPracticeQuestion.setAdapter(speakingTestPracticeTestAdapter);
        speakingTestPracticeTestAdapter.notifyDataSetChanged();
    }
    private void showJSONPart1(String json) {
        JsonDataHandlerPracticeTestSpeaking jsonDataHandlerPracticeTestSpeaking= new JsonDataHandlerPracticeTestSpeaking(json);
        practiceTestSpeaking=jsonDataHandlerPracticeTestSpeaking.parseJSON();
        SpeakingTestPracticeTestAdapter speakingTestPracticeTestAdapter = new SpeakingTestPracticeTestAdapter(this,practiceTestSpeaking);
        speakingTestPracticeQuestion.setAdapter(speakingTestPracticeTestAdapter);
        speakingTestPracticeTestAdapter.notifyDataSetChanged();
    }
    private void showJSONPart3(String json) {
        JsonDataHandlerSpeakingTest3 jsonDataHandlerSpeakingTest3 = new JsonDataHandlerSpeakingTest3(json);
        speakingTestPart3=jsonDataHandlerSpeakingTest3.parseJSON();
        SpeakingTestPart3Adapter speakingTestPart3Adapter = new SpeakingTestPart3Adapter(this,speakingTestPart3);
        speakingTest3.setAdapter(speakingTestPart3Adapter);
        speakingTestPart3Adapter.notifyDataSetChanged();
    }
    private void showJSONPart5(String json) {
        JsonDataHandlerSpeakingTest5 jsonDataHandlerSpeakingTest5 = new JsonDataHandlerSpeakingTest5(json);
        speakingTestPart5=jsonDataHandlerSpeakingTest5.parseJSON();
        SpeakingTestPart5Adapter speakingTestPart5Adapter = new SpeakingTestPart5Adapter(this,speakingTestPart5);
        speakingTest3.setAdapter(speakingTestPart5Adapter);
        speakingTestPart5Adapter.notifyDataSetChanged();
    }
    private void submitAnswerPractice (){
        final String speaking = videoToBase64(new File(mFileName));
        final String urlForSubmittingOptions = "http://demo.celpip.biz/api/spPracticeTaskSubmitSubmit?token="+tokenCode+"&q1_response="+speaking+"&memberid="+member_id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlForSubmittingOptions,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        Log.e("===Answer",urlForSubmittingOptions);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("===ErrorAnswer",urlForSubmittingOptions+"\n"+error.getLocalizedMessage());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("q1_response", speaking);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
//        Intent intent = new Intent(getApplicationContext(), SPEAKING.class);
//        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }
    private String videoToBase64(File file) {
        String encodedString = mFileName;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            // TODO: handle exception
        }
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
        Log.i("Strng", encodedString);

        return encodedString;
    }
    private void audioRecoder() {
        stopbtn.setEnabled(false);
        playbtn.setEnabled(false);
        stopplay.setEnabled(false);
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/AudioRecording.3gp";
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPermissions()) {
                    stopbtn.setEnabled(true);
                    startbtn.setEnabled(false);
                    playbtn.setEnabled(false);
                    stopplay.setEnabled(false);
                    mRecorder = new MediaRecorder();
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder.setOutputFile(mFileName);
                    String a = videoToBase64(new File(mFileName));
                    Log.e("===Audio",a);
                    try {
                        mRecorder.prepare();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "prepare() failed");
                    }
                    mRecorder.start();
                    Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_LONG).show();
                }
                else
                {
                    RequestPermissions();
                }
            }
        });
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopbtn.setEnabled(false);
                startbtn.setEnabled(true);
                playbtn.setEnabled(true);
                stopplay.setEnabled(true);
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();
            }
        });
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopbtn.setEnabled(false);
                startbtn.setEnabled(true);
                playbtn.setEnabled(false);
                stopplay.setEnabled(true);
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(mFileName);
                    mPlayer.prepare();
                    mPlayer.start();
                    Toast.makeText(getApplicationContext(), "Recording Started Playing", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "prepare() failed");
                }
            }
        });
        stopplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.release();
                mPlayer = null;
                stopbtn.setEnabled(false);
                startbtn.setEnabled(true);
                playbtn.setEnabled(true);
                stopplay.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Playing Audio Stopped", Toast.LENGTH_SHORT).show();
            }
        });
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
            Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
    }
}
