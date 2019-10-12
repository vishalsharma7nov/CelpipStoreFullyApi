package com.celpipstore.JsonData.VocabularyTest;

import com.celpipstore.GetterAndSetterClasses.VocabularyTest.VocabularyTest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerVocabularyTestList {
    public List<VocabularyTest> vocabularyTests = new ArrayList<>();
    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_NAME            = "name";
    public static final String KEY_TOTAL_QUESTIONS = "total_questions";
    public static final String KEY_ACTIVE          = "active";
    public static final String KEY_COIN_COST       = "coin_cost";
    private JSONArray users = null;
    private String json;
    public JsonDataHandlerVocabularyTestList(String json) {
        this.json = json;
    }
    public List<VocabularyTest>  parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            String[] id              = new String[users.length()];
            String[] name            = new String[users.length()];
            String[] total_questions = new String[users.length()];
            String[] active          = new String[users.length()];
            String[] coin_cost       = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                id[i]              = jo.getString(KEY_ID);
                name[i]            = jo.getString(KEY_NAME);
                total_questions[i] = jo.getString(KEY_TOTAL_QUESTIONS);
                active[i]          = jo.getString(KEY_ACTIVE);
                coin_cost[i]       = jo.getString(KEY_COIN_COST);
                vocabularyTests.add(new VocabularyTest(jo.getString(KEY_ID),jo.getString(KEY_NAME),jo.getString(KEY_TOTAL_QUESTIONS),jo.getString(KEY_ACTIVE),jo.getString(KEY_COIN_COST)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return vocabularyTests;
    }
}