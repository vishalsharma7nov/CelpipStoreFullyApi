package com.celpipstore.JsonData.SpeakingTest;

import com.celpipstore.GetterAndSetterClasses.SpeakingTest.PracticeTestSpeaking;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerSpeakingTest6 {
    private List<PracticeTestSpeaking> practiceTestSpeaking = new ArrayList<>();
    public static String id;
    public static String test_code;
    public static String q1_question;
    public static String created_date;
    public static String updated_date;
    public static String added_by;
    public static String role;
    private static String KEY_ID              = "id";
    private static String KEY_TEST_CODE       = "test_code";
    private String json;
    public JsonDataHandlerSpeakingTest6(String json) {
        this.json = json;
    }

    public List<PracticeTestSpeaking> parseJSON() {
        try {
                JSONObject jsonObject = new JSONObject(json);
                id                      = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code               = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                q1_question             = jsonObject.getJSONObject("data").getString("q1_question");
                created_date            = jsonObject.getJSONObject("data").getString("created_date");
                updated_date            = jsonObject.getJSONObject("data").getString("updated_date");
                added_by                = jsonObject.getJSONObject("data").getString("added_by");
                role                    = jsonObject.getJSONObject("data").getString("role");
                practiceTestSpeaking.add(new PracticeTestSpeaking(jsonObject.getJSONObject("data").getString(KEY_ID),jsonObject.getJSONObject("data").getString(KEY_TEST_CODE),jsonObject.getJSONObject("data").getString("q1_question"),jsonObject.getJSONObject("data").getString("created_date"),jsonObject.getJSONObject("data").getString("updated_date"),jsonObject.getJSONObject("data").getString("added_by"),jsonObject.getJSONObject("data").getString("role")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return practiceTestSpeaking;
    }
}
