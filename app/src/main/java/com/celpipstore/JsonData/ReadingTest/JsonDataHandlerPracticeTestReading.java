package com.celpipstore.JsonData.ReadingTest;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDataHandlerPracticeTestReading {

    public static String id;
    public static String test_code;
    public static String question;
    public static String question_title;
    public static String option1;
    public static String option2;
    public static String option3;
    public static String option4;
    public static String answer;
    private static String KEY_ID              = "id";
    private static String KEY_TEST_CODE       = "test_code";
    private String json;
    public JsonDataHandlerPracticeTestReading(String json) {
        this.json = json;
    }

    public void parseJSON() {
        try {
                JSONObject jsonObject = new JSONObject(json);
                id                      = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code               = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                question                = jsonObject.getJSONObject("data").getString("question");
                question_title          = jsonObject.getJSONObject("data").getString("question_title");
                option1                 = jsonObject.getJSONObject("data").getString("option1");
                option2                 = jsonObject.getJSONObject("data").getString("option2");
                option3                 = jsonObject.getJSONObject("data").getString("option3");
                option4                 = jsonObject.getJSONObject("data").getString("option4");
                answer                  = jsonObject.getJSONObject("data").getString("answer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
