package com.celpipstore.JsonData.WritingTest;

import com.celpipstore.GetterAndSetterClasses.WritingTest.Question.WritingTask1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerWritingTask1 {
    private List<WritingTask1> writingTask1 = new ArrayList<>();
    public static String id;
    public static String test_code;
    public static String question_title;
    public static String email_title;
    public static String created_date;
    public static String updated_date;
    public static String added_by;
    public static String role;
    public static String status;
    private static String KEY_ID              = "id";
    private static String KEY_TEST_CODE       = "test_code";
    private String json;
    public JsonDataHandlerWritingTask1(String json) {
        this.json = json;
    }

    public List<WritingTask1> parseJSON() {
        try {
                JSONObject jsonObject = new JSONObject(json);
                id                      = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code               = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                question_title          = jsonObject.getJSONObject("data").getString("question_title");
                email_title             = jsonObject.getJSONObject("data").getString("email_title");
                created_date            = jsonObject.getJSONObject("data").getString("created_date");
                updated_date            = jsonObject.getJSONObject("data").getString("updated_date");
                added_by                = jsonObject.getJSONObject("data").getString("added_by");
                role                    = jsonObject.getJSONObject("data").getString("role");
                status                  = jsonObject.getJSONObject("data").getString("status");
                writingTask1.add(new WritingTask1(jsonObject.getJSONObject("data").getString(KEY_ID),jsonObject.getJSONObject("data").getString(KEY_TEST_CODE),jsonObject.getJSONObject("data").getString("question_title"),jsonObject.getJSONObject("data").getString("email_title"),jsonObject.getJSONObject("data").getString("created_date"),jsonObject.getJSONObject("data").getString("updated_date"),jsonObject.getJSONObject("data").getString("added_by"),jsonObject.getJSONObject("data").getString("role"),jsonObject.getJSONObject("data").getString("status")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return writingTask1;
    }
}
