package com.celpipstore.JsonData.WritingTest;

import com.celpipstore.GetterAndSetterClasses.WritingTest.WritingTask2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerWritingTask2 {
    private List<WritingTask2> writingTask2 = new ArrayList<>();
    public static String id;
    public static String test_code;
    public static String question_title;
    public static String option_1;
    public static String option_2;
    public static String created_date;
    public static String updated_date;
    public static String added_by;
    public static String role;
    public static String status;
    private static String KEY_ID              = "id";
    private static String KEY_TEST_CODE       = "test_code";
    private String json;
    public JsonDataHandlerWritingTask2(String json) {
        this.json = json;
    }

    public List<WritingTask2> parseJSON() {
        try {
                JSONObject jsonObject = new JSONObject(json);
               id                      = jsonObject.getJSONObject("data").getString(KEY_ID);
               test_code               = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
               question_title          = jsonObject.getJSONObject("data").getString("question_title");
               option_1                = jsonObject.getJSONObject("data").getString("option_1");
               option_2                = jsonObject.getJSONObject("data").getString("option_2");
               created_date            = jsonObject.getJSONObject("data").getString("created_date");
               updated_date            = jsonObject.getJSONObject("data").getString("updated_date");
               added_by                = jsonObject.getJSONObject("data").getString("added_by");
               role                    = jsonObject.getJSONObject("data").getString("role");
               status                  = jsonObject.getJSONObject("data").getString("status");
               writingTask2.add(new WritingTask2(jsonObject.getJSONObject("data").getString(KEY_ID),jsonObject.getJSONObject("data").getString(KEY_TEST_CODE),jsonObject.getJSONObject("data").getString("question_title"),jsonObject.getJSONObject("data").getString("option_1"),jsonObject.getJSONObject("data").getString("option_2"),jsonObject.getJSONObject("data").getString("created_date"),jsonObject.getJSONObject("data").getString("updated_date"),jsonObject.getJSONObject("data").getString("added_by"),jsonObject.getJSONObject("data").getString("role"),jsonObject.getJSONObject("data").getString("status")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return writingTask2;
    }
}
