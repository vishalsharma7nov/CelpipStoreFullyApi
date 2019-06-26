package com.celpipstore;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDataHandlerListeningPart1 {

    public static String id;
    public static String test_code;
    public static String l1_practice_01_img;
    public static String l1_practice_01_text;
    public static String l1_converstaion_1_audio;
    public static String l1_converstaion_2_audio;
    public static String l1_converstaion_3_audio;
    public static String l1_q1_audio;
    public static String l1_q1_option1;
    public static String l1_q1_option2;
    public static String l1_q1_option3;
    public static String l1_q1_option4;
    public static String l1_q1_answer;
    public static String l1_q2_audio;
    public static String l1_q2_option1;
    public static String l1_q2_option2;
    public static String l1_q2_option3;
    public static String l1_q2_option4;
    public static String l1_q2_answer;
    public static String l1_q3_audio;
    public static String l1_q3_option1;
    public static String l1_q3_option2;
    public static String l1_q3_option3;
    public static String l1_q3_option4;
    public static String l1_q3_answer;
    public static String l1_q4_audio;
    public static String l1_q4_option1;
    public static String l1_q4_option2;
    public static String l1_q4_option3;
    public static String l1_q4_option4;
    public static String l1_q4_answer;
    public static String l1_q5_audio;
    public static String l1_q5_option1;
    public static String l1_q5_option2;
    public static String l1_q5_option3;
    public static String l1_q5_option4;
    public static String l1_q5_answer;
    public static String l1_q6_audio;
    public static String l1_q6_option1;
    public static String l1_q6_option2;
    public static String l1_q6_option3;
    public static String l1_q6_option4;
    public static String l1_q6_answer;
    public static String l1_q7_audio;
    public static String l1_q7_option1;
    public static String l1_q7_option2;
    public static String l1_q7_option3;
    public static String l1_q7_option4;
    public static String l1_q7_answer;
    public static String l1_q8_audio;
    public static String l1_q8_option1;
    public static String l1_q8_option2;
    public static String l1_q8_option3;
    public static String l1_q8_option4;
    public static String l1_q8_answer;

    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";


    private String json;

    public JsonDataHandlerListeningPart1(String json) {
        this.json = json;
    }

    protected void parseJSON() {

        try {
                JSONObject jsonObject = new JSONObject(json);
                id                      = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code               = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                l1_practice_01_img      = jsonObject.getJSONObject("data").getString("l1_practice_01_img");
                l1_practice_01_text     = jsonObject.getJSONObject("data").getString("l1_practice_01_text");
                l1_converstaion_1_audio = jsonObject.getJSONObject("data").getString("l1_conversation_1_audio");
                l1_converstaion_2_audio = jsonObject.getJSONObject("data").getString("l1_conversation_2_audio");
                l1_converstaion_3_audio = jsonObject.getJSONObject("data").getString("l1_conversation_3_audio");
                l1_q1_audio             = jsonObject.getJSONObject("data").getString("l1_q1_audio");
                l1_q1_option1           = jsonObject.getJSONObject("data").getString("l1_q1_option1");
                l1_q1_option2           = jsonObject.getJSONObject("data").getString("l1_q1_option2");
                l1_q1_option3           = jsonObject.getJSONObject("data").getString("l1_q1_option3");
                l1_q1_option4           = jsonObject.getJSONObject("data").getString("l1_q1_option4");
                l1_q1_answer            = jsonObject.getJSONObject("data").getString("l1_q1_answer");
                l1_q2_audio             = jsonObject.getJSONObject("data").getString("l1_q2_audio");
                l1_q2_option1           = jsonObject.getJSONObject("data").getString("l1_q2_option1");
                l1_q2_option2           = jsonObject.getJSONObject("data").getString("l1_q2_option2");
                l1_q2_option3           = jsonObject.getJSONObject("data").getString("l1_q2_option3");
                l1_q2_option4           = jsonObject.getJSONObject("data").getString("l1_q2_option4");
                l1_q2_answer            = jsonObject.getJSONObject("data").getString("l1_q2_answer");
                l1_q3_audio             = jsonObject.getJSONObject("data").getString("l1_q3_audio");
                l1_q3_option1           = jsonObject.getJSONObject("data").getString("l1_q3_option1");
                l1_q3_option2           = jsonObject.getJSONObject("data").getString("l1_q3_option2");
                l1_q3_option3           = jsonObject.getJSONObject("data").getString("l1_q3_option3");
                l1_q3_option4           = jsonObject.getJSONObject("data").getString("l1_q3_option4");
                l1_q3_answer            = jsonObject.getJSONObject("data").getString("l1_q3_answer");
                l1_q4_audio             = jsonObject.getJSONObject("data").getString("l1_q4_audio");
                l1_q4_option1           = jsonObject.getJSONObject("data").getString("l1_q4_option1");
                l1_q4_option2           = jsonObject.getJSONObject("data").getString("l1_q4_option2");
                l1_q4_option3           = jsonObject.getJSONObject("data").getString("l1_q4_option3");
                l1_q4_option4           = jsonObject.getJSONObject("data").getString("l1_q4_option4");
                l1_q4_answer            = jsonObject.getJSONObject("data").getString("l1_q4_answer");
                l1_q5_audio             = jsonObject.getJSONObject("data").getString("l1_q5_audio");
                l1_q5_option1           = jsonObject.getJSONObject("data").getString("l1_q5_option1");
                l1_q5_option2           = jsonObject.getJSONObject("data").getString("l1_q5_option2");
                l1_q5_option3           = jsonObject.getJSONObject("data").getString("l1_q5_option3");
                l1_q5_option4           = jsonObject.getJSONObject("data").getString("l1_q5_option4");
                l1_q5_answer            = jsonObject.getJSONObject("data").getString("l1_q5_answer");
                l1_q6_audio             = jsonObject.getJSONObject("data").getString("l1_q6_audio");
                l1_q6_option1           = jsonObject.getJSONObject("data").getString("l1_q6_option1");
                l1_q6_option2           = jsonObject.getJSONObject("data").getString("l1_q6_option2");
                l1_q6_option3           = jsonObject.getJSONObject("data").getString("l1_q6_option3");
                l1_q6_option4           = jsonObject.getJSONObject("data").getString("l1_q6_option4");
                l1_q6_answer            = jsonObject.getJSONObject("data").getString("l1_q6_answer");
                l1_q7_audio             = jsonObject.getJSONObject("data").getString("l1_q7_audio");
                l1_q7_option1           = jsonObject.getJSONObject("data").getString("l1_q7_option1");
                l1_q7_option2           = jsonObject.getJSONObject("data").getString("l1_q7_option2");
                l1_q7_option3           = jsonObject.getJSONObject("data").getString("l1_q7_option3");
                l1_q7_option4           = jsonObject.getJSONObject("data").getString("l1_q7_option4");
                l1_q7_answer            = jsonObject.getJSONObject("data").getString("l1_q7_answer");
                l1_q8_audio             = jsonObject.getJSONObject("data").getString("l1_q8_audio");
                l1_q8_option1           = jsonObject.getJSONObject("data").getString("l1_q8_option1");
                l1_q8_option2           = jsonObject.getJSONObject("data").getString("l1_q8_option2");
                l1_q8_option3           = jsonObject.getJSONObject("data").getString("l1_q8_option3");
                l1_q8_option4           = jsonObject.getJSONObject("data").getString("l1_q8_option4");
                l1_q8_answer            = jsonObject.getJSONObject("data").getString("l1_q8_answer");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
