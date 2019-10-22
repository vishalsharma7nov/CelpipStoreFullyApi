package com.celpipstore.JsonData.ReadingTest;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDataHandlerReadingPart3 {
    public static String id;
    public static String test_code;
    public static String passages_1;
    public static String passages_2;
    public static String passages_3;
    public static String passages_4;
    public static String q1_question;
    public static String q1_answerer;
    public static String q2_question;
    public static String q2_answerer;
    public static String q3_question;
    public static String q3_answerer;
    public static String q4_question;
    public static String q5_question;
    public static String q5_answerer;
    public static String q6_question;
    public static String q6_answerer;
    public static String q7_question;
    public static String q7_answerer;
    public static String q8_question;
    public static String q8_answerer;
    public static String q9_question;
    public static String q9_answerer;
    public static String created_date;
    public static String updated_date;
    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";
    private String json;

    public JsonDataHandlerReadingPart3(String json) {
        this.json = json;
    }

    public void parseJSON() {
        try {
                JSONObject jsonObject = new JSONObject(json);
                id              = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code       = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                passages_1        = jsonObject.getJSONObject("data").getString("passages_1");
                passages_2        = jsonObject.getJSONObject("data").getString("passages_2");
                passages_3        = jsonObject.getJSONObject("data").getString("passages_3");
                passages_4        = jsonObject.getJSONObject("data").getString("passages_4");
                q1_question     = jsonObject.getJSONObject("data").getString("q1_question");
                q1_answerer     = jsonObject.getJSONObject("data").getString("q1_answere");
                q2_question     = jsonObject.getJSONObject("data").getString("q2_question");
                q2_answerer     = jsonObject.getJSONObject("data").getString("q2_answere");
                q3_question     = jsonObject.getJSONObject("data").getString("q3_question");
                q3_answerer     = jsonObject.getJSONObject("data").getString("q3_answere");
                q4_question     = jsonObject.getJSONObject("data").getString("q4_question");
                q5_question     = jsonObject.getJSONObject("data").getString("q5_question");
                q5_answerer     = jsonObject.getJSONObject("data").getString("q5_answere");
                q6_question     = jsonObject.getJSONObject("data").getString("q6_question");
                q6_answerer     = jsonObject.getJSONObject("data").getString("q6_answere");
                q7_question     = jsonObject.getJSONObject("data").getString("q7_question");
                q7_answerer     = jsonObject.getJSONObject("data").getString("q7_answere");
                q8_question     = jsonObject.getJSONObject("data").getString("q8_question");
                q8_answerer     = jsonObject.getJSONObject("data").getString("q8_answere");
                q9_question     = jsonObject.getJSONObject("data").getString("q9_question");
                q9_answerer     = jsonObject.getJSONObject("data").getString("q9_answere");
                created_date    = jsonObject.getJSONObject("data").getString("created_date");
                updated_date    = jsonObject.getJSONObject("data").getString("updated_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
