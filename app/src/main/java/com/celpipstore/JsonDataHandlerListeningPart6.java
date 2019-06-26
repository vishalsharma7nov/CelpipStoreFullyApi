package com.celpipstore;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDataHandlerListeningPart6 {

    public static String id;
    public static String test_code;
    public static String conversation_1_audio;

    public static String q1_question;
    public static String q2_question;
    public static String q3_question;
    public static String q4_question;
    public static String q5_question;
    public static String q6_question;


    public static String q1_option1;
    public static String q1_option2;
    public static String q1_option3;
    public static String q1_option4;
    public static String q1_answer;


    public static String q2_option1;
    public static String q2_option2;
    public static String q2_option3;
    public static String q2_option4;
    public static String q2_answer;


    public static String q3_option1;
    public static String q3_option2;
    public static String q3_option3;
    public static String q3_option4;
    public static String q3_answer;


    public static String q4_option1;
    public static String q4_option2;
    public static String q4_option3;
    public static String q4_option4;
    public static String q4_answer;

    public static String q5_option1;
    public static String q5_option2;
    public static String q5_option3;
    public static String q5_option4;
    public static String q5_answer;

    public static String q6_option1;
    public static String q6_option2;
    public static String q6_option3;
    public static String q6_option4;
    public static String q6_answer;

    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";


    private String json;

    public JsonDataHandlerListeningPart6(String json) {
        this.json = json;
    }

    protected void parseJSON() {

        try {
                JSONObject jsonObject = new JSONObject(json);
                id                   = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code            = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                conversation_1_audio = jsonObject.getJSONObject("data").getString("conversation_1_audio");
                q1_question          = jsonObject.getJSONObject("data").getString("q1_question");
                q2_question          = jsonObject.getJSONObject("data").getString("q2_question");
                q3_question          = jsonObject.getJSONObject("data").getString("q3_question");
                q4_question          = jsonObject.getJSONObject("data").getString("q4_question");
                q5_question          = jsonObject.getJSONObject("data").getString("q5_question");
                q6_question          = jsonObject.getJSONObject("data").getString("q6_question");
                q1_option1           = jsonObject.getJSONObject("data").getString("q1_option1");
                q1_option2           = jsonObject.getJSONObject("data").getString("q1_option2");
                q1_option3           = jsonObject.getJSONObject("data").getString("q1_option3");
                q1_option4           = jsonObject.getJSONObject("data").getString("q1_option4");
                q1_answer            = jsonObject.getJSONObject("data").getString("q1_answer");
                q2_option1           = jsonObject.getJSONObject("data").getString("q2_option1");
                q2_option2           = jsonObject.getJSONObject("data").getString("q2_option2");
                q2_option3           = jsonObject.getJSONObject("data").getString("q2_option3");
                q2_option4           = jsonObject.getJSONObject("data").getString("q2_option4");
                q2_answer            = jsonObject.getJSONObject("data").getString("q2_answer");
                q3_option1           = jsonObject.getJSONObject("data").getString("q3_option1");
                q3_option2           = jsonObject.getJSONObject("data").getString("q3_option2");
                q3_option3           = jsonObject.getJSONObject("data").getString("q3_option3");
                q3_option4           = jsonObject.getJSONObject("data").getString("q3_option4");
                q3_answer            = jsonObject.getJSONObject("data").getString("q3_answer");
                q4_option1           = jsonObject.getJSONObject("data").getString("q4_option1");
                q4_option2           = jsonObject.getJSONObject("data").getString("q4_option2");
                q4_option3           = jsonObject.getJSONObject("data").getString("q4_option3");
                q4_option4           = jsonObject.getJSONObject("data").getString("q4_option4");
                q4_answer            = jsonObject.getJSONObject("data").getString("q4_answer");
                q5_option1           = jsonObject.getJSONObject("data").getString("q5_option1");
                q5_option2           = jsonObject.getJSONObject("data").getString("q5_option2");
                q5_option3           = jsonObject.getJSONObject("data").getString("q5_option3");
                q5_option4           = jsonObject.getJSONObject("data").getString("q5_option4");
                q5_answer            = jsonObject.getJSONObject("data").getString("q5_answer");
                q6_option1           = jsonObject.getJSONObject("data").getString("q6_option1");
                q6_option2           = jsonObject.getJSONObject("data").getString("q6_option2");
                q6_option3           = jsonObject.getJSONObject("data").getString("q6_option3");
                q6_option4           = jsonObject.getJSONObject("data").getString("q6_option4");
                q6_answer            = jsonObject.getJSONObject("data").getString("q6_answer");




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
