package com.celpipstore;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonDataHandlerListeningPart2 {

    public static String[] id;
    public static String[] test_code;
    public static String[]l1_practice_01_img;
    public static String[] l1_practice_01_text;
    public static String[] l1_converstaion_1_audio;
    public static String[] l1_q1_audio;
    public static String[] l1_q1_option1;
    public static String[] l1_q1_option2;
    public static String[] l1_q1_option3;
    public static String[] l1_q1_option4;
    public static String[] l1_q1_answer;


    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";
    public static final String KEY_TITLE           = "title";
    public static final String KEY_QUESTION_IMAGE  = "question_image";
    public static final String KEY_OPTIONS         = "options";

    private JSONArray users = null;

    private String json;

    public JsonDataHandlerListeningPart2(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id                       = new String[users.length()];
            test_code                = new String[users.length()];
            l1_practice_01_img       = new String[users.length()];
            l1_practice_01_text      = new String[users.length()];
            l1_converstaion_1_audio  = new String[users.length()];
            l1_q1_audio              = new String[users.length()];
            l1_q1_option1            = new String[users.length()];
            l1_q1_option2            = new String[users.length()];
            l1_q1_option3            = new String[users.length()];
            l1_q1_option4            = new String[users.length()];
            l1_q1_answer             = new String[users.length()];


            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id[i]                  = jo.getString(KEY_ID);
                test_code[i]           = jo.getString(KEY_TEST_CODE);
                l1_practice_01_img[i]  = jo.getString("l1_practice_01_img");
                l1_practice_01_text[i] = jo.getString("l1_practice_01_text");




            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
