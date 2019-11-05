package com.celpipstore.JsonData.ListeningTest.Question;

import com.celpipstore.GetterAndSetterClasses.ListeningTest.Question.ListeningPracticeTest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerPracticeTestListening {
    private List<ListeningPracticeTest> listeningPracticeTest = new ArrayList<>();
    private static String id;
    private static String test_code;
    private static String PTEtypeid;
    private static String PTEsubtype_id;
    private static String questionid;
    private static String question;
    private static String mp3URL;
    private static String option1;
    private static String option2;
    private static String option3;
    private static String option4;
    private static String answer;
    private static String KEY_ID              = "id";
    private static String KEY_TEST_CODE       = "test_code";
    private String json;
    public JsonDataHandlerPracticeTestListening(String json) {
        this.json = json;
    }

    public List<ListeningPracticeTest> parseJSON() {
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
                listeningPracticeTest.add(new ListeningPracticeTest(jsonObject.getJSONObject("data").getString(KEY_ID),jsonObject.getJSONObject("data").getString(KEY_TEST_CODE),jsonObject.getJSONObject("data").getString("PTEtypeid"),jsonObject.getJSONObject("data").getString("PTEsubtype_id"),jsonObject.getJSONObject("data").getString("questionid"),jsonObject.getJSONObject("data").getString("question"),jsonObject.getJSONObject("data").getString("mp3URL"),jsonObject.getJSONObject("data").getString("option1"),jsonObject.getJSONObject("data").getString("option2"),jsonObject.getJSONObject("data").getString("option3"),jsonObject.getJSONObject("data").getString("option4"),jsonObject.getJSONObject("data").getString("answer")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listeningPracticeTest;
    }
}
