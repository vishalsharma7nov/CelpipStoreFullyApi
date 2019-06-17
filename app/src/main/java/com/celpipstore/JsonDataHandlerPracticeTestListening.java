package com.celpipstore;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDataHandlerPracticeTestListening {

    public static String id;
    public static String test_code;
    public static String PTEtypeid;
    public static String PTEsubtype_id;
    public static String questionid;
    public static String question;
    public static String mp3URL;
    public static String option1;
    public static String option2;
    public static String option3;
    public static String option4;
    public static String answer;


    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";


    private String json;

    public JsonDataHandlerPracticeTestListening(String json) {
        this.json = json;
    }

    protected void parseJSON() {

        try {
                JSONObject jsonObject = new JSONObject(json);
                id                      = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code               = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                PTEtypeid               = jsonObject.getJSONObject("data").getString("PTEtypeid");
                PTEsubtype_id           = jsonObject.getJSONObject("data").getString("PTEsubtype_id");
                questionid              = jsonObject.getJSONObject("data").getString("questionid");
                question                = jsonObject.getJSONObject("data").getString("question");
                mp3URL                  = jsonObject.getJSONObject("data").getString("mp3URL");
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
